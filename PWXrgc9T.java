
import RMI.DataService;
import java.rmi.registry.*;
import java.util.*;

public class PWXrgc9T {
    public static void main(String[] args) {
        String studentCode = "B23DCCN951";
        String questionCode = "PWXrgc9T";

        try {
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            DataService service = (DataService) registry.lookup("RMIDataService");
            String data = (String) service.requestData(studentCode, questionCode);

            String[] parts = data.trim().split(",");
            double[] numbers = new double[parts.length];
            double sum = 0;

            for (int i = 0; i < parts.length; i++) {
                numbers[i] = Double.parseDouble(parts[i].trim());
                sum += numbers[i];
            }

            double average = sum / numbers.length;
            double variance = 0;

            for (double number : numbers)
                variance += Math.pow(number - average, 2);

            double stddev = Math.sqrt(variance / numbers.length);

            Arrays.sort(numbers);
            double p95 = numbers[(int) Math.ceil(numbers.length * 0.95) - 1];

            String result = String.format(Locale.US, "average=%.2f;stddev=%.2f;p95=%.2f",
                    average, stddev, p95);

            service.submitData(studentCode, questionCode, result);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
