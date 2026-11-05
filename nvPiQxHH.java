// RMI - Character : ma hoa ky tu dac biet
import RMI.CharacterService;
import java.net.URI;
import java.rmi.registry.*;

public class nvPiQxHH {
    public static void main(String[] args) {
        String studentCode = "B23DCCN951";
        String questionCode = "nvPiQxHH";

        try {
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            CharacterService service = (CharacterService) registry.lookup("RMICharacterService");
            String data = service.requestCharacter(studentCode, questionCode);

            String result = new URI(null, null, data, null).toASCIIString();

            service.submitCharacter(studentCode, questionCode, result);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
