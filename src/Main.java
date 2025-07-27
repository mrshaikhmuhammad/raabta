import net.Server;
import net.Address;
import net.Broadcast;
import net.Client;

import java.util.*;

public class Main {
    private static String name = "muhammad";
    private static Address system = new Address();
    private static ArrayList<Address> contacts = new ArrayList<>();
    public static void main(String[] args) {
        contacts.add(new Address("192.168.223.48"));

        Server server = new Server(system, contacts);
        Broadcast broadcast = new Broadcast(name);
        
        server.start();
        broadcast.start();

        System.out.println("Online People: ");
        for(int i=0; i<contacts.size(); i++){
            System.out.println(i + " | " + contacts.get(i));
        }
        System.out.println("Choice: ");
        int choice = 0;


        Client client = new Client(name, contacts.get(choice));
        client.start();
        
    }
}