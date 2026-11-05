// RMI - Byte : ptu xuat hien nhieu nhat trong mang
import RMI.ByteService;
import java.rmi.registry.*;
import java.util.*;

class _4HQeRsnA {
    public static void main(String[] args) {
        String studentCode = "B23DCCN951";
        String questionCode = "4HQeRsnA";

        try {
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            ByteService service = (ByteService) registry.lookup("RMIByteService");
            byte[] data = service.requestData(studentCode, questionCode);

            Map<Byte, Integer> frequency = new HashMap<>();
            for (byte value : data)
                frequency.put(value, frequency.getOrDefault(value, 0) + 1);

            byte maxValue = data[0];
            for (byte value : data)
                if (frequency.get(value) > frequency.get(maxValue))
                    maxValue = value;

            byte[] result = {maxValue, frequency.get(maxValue).byteValue()};
            service.submitData(studentCode, questionCode, result);

            System.out.println(Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
