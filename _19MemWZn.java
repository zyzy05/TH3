// RMI - Character : Chuyen so nguyen sang so La Ma
import RMI.CharacterService;
import java.rmi.registry.*;

public class _19MemWZn {
    public static void main(String[] args) {
        String studentCode = "B23DCCN951";
        String questionCode = "19MemWZn";

        try {
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            CharacterService service = (CharacterService) registry.lookup("RMICharacterService");

            String data = service.requestCharacter(studentCode, questionCode);
            System.out.println("Du lieu nhan duoc: " + data);

            if (data == null || data.trim().isEmpty()) {
                System.out.println("Server khong tra ve du lieu. Kiem tra MSSV va ma cau hoi.");
                return;
            }

            int number = Integer.parseInt(data.trim());
            int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < values.length; i++) {
                while (number >= values[i]) {
                    result.append(symbols[i]);
                    number -= values[i];
                }
            }

            service.submitCharacter(studentCode, questionCode, result.toString());
            System.out.println("Ket qua: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
