// RMI - Object : Phan tang xu ly SLA ticket
import RMI.ObjectService;
import RMI.TicketSla;
import java.rmi.registry.*;

public class tczrpQ9I {
    public static void main(String[] args) {
        String studentCode = "B23DCCN951";
        String questionCode = "tczrpQ9I";

        try {
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            ObjectService service = (ObjectService) registry.lookup("RMIObjectService");
            java.io.Serializable data = service.requestObject(studentCode, questionCode);

            if (data == null) {
                System.out.println("Server tra ve null. Kiem tra MSSV va ma cau hoi.");
                return;
            }

            if (!(data instanceof TicketSla)) {
                System.out.println("Sai kieu du lieu: " + data.getClass().getName());
                return;
            }

            TicketSla ticket = (TicketSla) data;
            int hours = ticket.getOpenedHoursAgo();
            String priority = ticket.getPriority();

            boolean breached = switch (priority) {
                case "CRITICAL" -> hours > 2;
                case "HIGH" -> hours > 8;
                case "MEDIUM" -> hours > 24;
                case "LOW" -> hours > 72;
                default -> false;
            };

            ticket.setBreached(breached);
            ticket.setAction(!breached ? "MONITOR"
                    : priority.equals("CRITICAL") || hours > 96
                    ? "ESCALATE_L2" : "ESCALATE_L1");

            service.submitObject(studentCode, questionCode, ticket);
            System.out.println("breached=" + breached + "; action=" + ticket.getAction());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
