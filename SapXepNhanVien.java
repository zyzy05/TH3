
import java.util.*;
import javax.xml.ws.BindingProvider;

public class Main {

    // Sắp xếp nhân viên theo thâm niên
    public static List<EmployeeY> sortEmployees(
            List<EmployeeY> employees) {

        // Tạo danh sách mới để không thay đổi danh sách ban đầu
        List<EmployeeY> result = new ArrayList<>(employees);

        // Ngày bắt đầu sớm nhất đứng trước
        // List.sort() sử dụng thuật toán sắp xếp ổn định
        result.sort(
            Comparator.comparing(EmployeeY::getStartDate)
        );

        return result;
    }

    public static void main(String[] args) {

        String studentCode = "B23DCCN951";
        String qCode = "Pcr4HZhO";

        try {

            // Khởi tạo ObjectService
            ObjectService service = new ObjectService();

            // Lấy SOAP Port
            ObjectServiceSoap port =
                    service.getObjectServiceSoap();

            // Thiết lập địa chỉ server
            ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://36.50.135.242:2221/ObjectService"
            );

            // a. Lấy danh sách EmployeeY từ server
            List<EmployeeY> employees =
                    port.requestListEmployeeY(
                        studentCode,
                        qCode
                    );

            System.out.println("Danh sach nhan vien ban dau:");

            for (EmployeeY e : employees) {
                System.out.println(
                    e.getName() + " - " + e.getStartDate()
                );
            }

            // b. Sắp xếp theo thâm niên giảm dần
            List<EmployeeY> result =
                    sortEmployees(employees);

            System.out.println("\nDanh sach sau khi sap xep:");

            for (EmployeeY e : result) {
                System.out.println(
                    e.getName() + " - " + e.getStartDate()
                );
            }

            // c. Gửi danh sách đã sắp xếp lên server
            port.submitListEmployeeY(
                studentCode,
                qCode,
                result
            );

            System.out.println("\nSubmit successful!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
