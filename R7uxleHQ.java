// RMI - Data : Tim cac phan tu lon hon ca hai phan tu ben canh
import RMI.DataService;
import java.rmi.registry.*;
import java.util.*;

public class R7uxleHQ {
    public static void main(String[] args) {
        String studentCode = "B23DCCN951";
        String questionCode = "R7uxleHQ";

        try {
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            DataService service = (DataService) registry.lookup("RMIDataService");
            int[] data = (int[]) service.requestData(studentCode, questionCode);

            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                boolean left = i == 0 || data[i] > data[i - 1];
                boolean right = i == data.length - 1 || data[i] > data[i + 1];
                if (left && right) result.add(i + 1);
            }

            service.submitData(studentCode, questionCode, result);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
