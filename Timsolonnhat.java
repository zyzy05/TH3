
import java.util.*;
import javax.xml.ws.BindingProvider;

public class Main {

    public static String findLargestNumber(List<Integer> numbers) {

        List<String> list = new ArrayList<>();

        for (Integer n : numbers) {
            list.add(String.valueOf(n));
        }

        list.sort((a, b) -> (b + a).compareTo(a + b));

        if (list.isEmpty() || list.get(0).equals("0")) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        for (String s : list) {
            result.append(s);
        }

        return result.toString();
    }

    public static void main(String[] args) {

        String studentCode = "YOUR_STUDENT_CODE";
        String qCode = "iq1cfLLS";

        try {
            // Khởi tạo SOAP Service
            DataService service = new DataService();

            // Lấy SOAP Port
            DataServiceSoap port =
                    service.getDataServiceSoap();

            // Thiết lập endpoint
            ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://36.50.135.242:2221/DataService"
            );

            // a. Lấy dữ liệu
            List<Integer> numbers =
                    port.getData(studentCode, qCode);

            System.out.println("Input: " + numbers);

            // b. Tạo số lớn nhất
            String result = findLargestNumber(numbers);

            System.out.println("Result: " + result);

            // c. Gửi kết quả
            port.submitDataString(
                studentCode,
                qCode,
                result
            );

            System.out.println("Submit successful!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
