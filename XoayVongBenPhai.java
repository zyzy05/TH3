
import java.util.*;
import javax.xml.ws.BindingProvider;

import soapclient.CharacterService;
import soapclient.SoapCharacterService;

public class XoayVongBenPhai {

    // Xoay vong danh sach sang phai
    public static List<Integer> rotateRight(List<Integer> data) {

        List<Integer> result = new ArrayList<>(data);

        if (result.isEmpty()) {
            return result;
        }

        // Lay gia tri cua phan tu dau tien
        int ascii = result.get(0);

        // Tinh so lan xoay thuc te
        int k = ascii % result.size();

        // Xoay vong sang phai
        Collections.rotate(result, k);

        return result;
    }

    public static void main(String[] args) {

        String studentCode = "B23DCCN951";
        String qCode = "ezh7bd4c";

        try {

            // 1. Khoi tao SOAP Service
            CharacterService service = new CharacterService();

            // 2. Lay SOAP Port
            SoapCharacterService port =
                    service.getSoapCharacterServicePort();

            // 3. Thiet lap endpoint
            ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://36.50.135.242:2221/CharacterService"
            );

            // 4. Nhan danh sach ky tu tu server
            List<Integer> data =
                    port.requestCharacter(studentCode, qCode);

            System.out.println("Du lieu ban dau:");
            System.out.println(data);

            // 5. Xoay vong sang phai
            List<Integer> result = rotateRight(data);

            System.out.println("Du lieu sau khi xoay:");
            System.out.println(result);

            // 6. Gui ket qua ve server
            port.submitCharacterCharArray(
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
