
import java.util.*;
import javax.xml.ws.BindingProvider;

import soapclient.CharacterService;
import soapclient.SoapCharacterService;

public class DemSoLuongNguyenAm {

    // Dem so luong nguyen am trong mot tu
    public static int countVowels(String word) {

        int count = 0;
        String vowels = "aeiouAEIOU";

        for (int i = 0; i < word.length(); i++) {
            if (vowels.indexOf(word.charAt(i)) != -1) {
                count++;
            }
        }

        return count;
    }

    // Phan nhom tu theo so luong nguyen am
    public static List<String> groupWords(List<String> words) {

        // TreeMap sap xep so luong nguyen am tang dan
        Map<Integer, List<String>> groups = new TreeMap<>();

        for (String word : words) {

            int vowelCount = countVowels(word);

            groups.putIfAbsent(vowelCount, new ArrayList<>());

            groups.get(vowelCount).add(word);
        }

        // Tao danh sach ket qua
        List<String> result = new ArrayList<>();

        for (List<String> group : groups.values()) {

            // Sap xep tu theo thu tu tu dien
            Collections.sort(group);

            // Ghep cac tu bang dau phay
            String groupString = String.join(", ", group);

            result.add(groupString);
        }

        return result;
    }

    public static void main(String[] args) {

        // Thay bang thong tin duoc cap
        String studentCode = "B23DCCN951";
        String qCode = "YOUR_QCODE";

        try {

            // 1. Khoi tao SOAP Service
            CharacterService service = new CharacterService();

            // 2. Lay SOAP Port
            SoapCharacterService port =
                    service.getSoapCharacterServicePort();

            // 3. Thiet lap dia chi server
            ((BindingProvider) port).getRequestContext().put(
                BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://36.50.135.242:2221/CharacterService"
            );

            // 4. Lay danh sach chuoi tu server
            List<String> words =
                    port.requestStringArray(studentCode, qCode);

            System.out.println("Du lieu nhan duoc:");
            System.out.println(words);

            // 5. Phan nhom theo so luong nguyen am
            List<String> result = groupWords(words);

            System.out.println("Ket qua phan nhom:");

            for (String group : result) {
                System.out.println(group);
            }

            // 6. Gui ket qua ve server
            port.submitCharacterStringArray(
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
