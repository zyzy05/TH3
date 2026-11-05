
import java.util.*;
import javax.xml.ws.BindingProvider;

import soapclient.DataService;
import soapclient.SoapDataService;

public class Main {

    public static void main(String[] args) {

        // Thong tin sinh vien va ma cau hoi
        String studentCode = "B23DCCN951";
        String qCode = "i5MZsDbk";

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

            // 5. Dem so phan tu chan va le
            int even = 0;
            int odd = 0;

            for (Integer number : numbers) {

                if (number % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
            }

            System.out.println("So phan tu chan: " + even);
            System.out.println("So phan tu le: " + odd);

            // 6. Tao danh sach ket qua
            List<String> result = new ArrayList<>();

            result.add("EVEN=" + even);
            result.add("ODD=" + odd);

            System.out.println("Ket qua gui len server:");
            System.out.println(result);

            // 7. Gui ket qua ve server
            port.submitDataStringArray(
                studentCode,
                qCode,
                result
            );

            System.out.println("Submit successful!");

        } catch (Exception e) {

            System.out.println("Co loi xay ra:");
            e.printStackTrace();
        }
    }
}
