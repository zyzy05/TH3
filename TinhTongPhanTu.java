
import java.util.*;
import javax.xml.ws.BindingProvider;

import soapclient.DataService;
import soapclient.SoapDataService;

public class TinhTongPhanTu {

    public static void main(String[] args) {

        // Thong tin sinh vien
        String studentCode = "B23DCCN951";
        String qCode = "obv59MLJ";

        try {

            // 1. Khoi tao SOAP Service
            DataService service = new DataService();

            // 2. Lay SOAP Port
            SoapDataService port =
                    service.getSoapDataServicePort();

            // 3. Thiet lap dia chi server
            ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://36.50.135.242:2221/DataService"
            );

            // 4. Lay danh sach so nguyen tu server
            List<Integer> numbers =
                    port.getData(studentCode, qCode);

            System.out.println("Danh sach nhan duoc:");
            System.out.println(numbers);

            // 5. Tinh tong cac phan tu
            int sum = 0;

            for (Integer number : numbers) {
                sum += number;
            }

            System.out.println("Tong cac phan tu: " + sum);

            // 6. Gui ket qua ve server
            port.submitDataInt(
                studentCode,
                qCode,
                sum
            );

            System.out.println("Da gui ket qua thanh cong!");

        } catch (Exception e) {

            System.out.println("Co loi xay ra:");
            e.printStackTrace();
        }
    }
}
