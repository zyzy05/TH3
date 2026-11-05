// RMI - Byte : ptu co so lan xuat hien it nhat mang
import RMI.ByteService;
import java.rmi.registry.*;
import java.util.*;

public class EJlUgCdT {
    public static void main(String[] args) {
        String studentCode = "B23DCCN951";
        String questionCode = "EJlUgCdT";

        try {
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            ByteService service = (ByteService) registry.lookup("RMIByteService");
            byte[] data = service.requestData(studentCode, questionCode);

            Map<Byte, Integer> frequency = new HashMap<>();
            for (byte value : data)
                frequency.put(value, frequency.getOrDefault(value, 0) + 1);

            byte minValue = data[0];
            for (byte value : data)
                if (frequency.get(value) < frequency.get(minValue))
                    minValue = value;

            byte[] result = {minValue, frequency.get(minValue).byteValue()};
            service.submitData(studentCode, questionCode, result);

            System.out.println(Arrays.toString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
