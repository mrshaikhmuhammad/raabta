import java.util.ArrayList;

import net.*;
import javax.websocket.*;
import javax.websocket.server.*;

@ServerEndpoint("/backend")
public class Main {
    private static String name = "Muhammad";
    private static Address system = new Address();
    private static ArrayList<Address> contacts = new ArrayList<>();

    private static Server server;
    private static Client client = new Client(new Address(name,"192.168.0.0"));
    private static Broadcast broadcast = new Broadcast(name);

    @OnOpen
    public void onOpen(Session session){
        server = new Server(name, system, session, contacts);
        server.start();
        broadcast.online();
    }

    @OnMessage
    public void onMessage(String message, Session session){
        client.send(message);
    }

    @OnClose
    public void onClose(Session session){
        broadcast.offline();
    } 
}
