package net;
import java.net.*;

public class Broadcast{
    private static String name;
    private static Address system = new Address();
    private static Address[] contacts = new Address[254];

    static{
        int index = system.getIP().lastIndexOf(".");
        String network = system.getIP().substring(0, ++index);

        for(int i=0; i<254; i++){
            contacts[i] = new Address(null,network + (i+1));
        }
    }

    public Broadcast(String name){
        Broadcast.name = name;
    }

    private void start(String code){
        try (
            DatagramSocket clientSocket = new DatagramSocket();
        ) {
            byte[] data = (code + name).getBytes();
            System.out.println("Broadcast " + code + name);
            for(Address address: contacts) {;
                DatagramPacket packet = new DatagramPacket(data, data.length, address.getAddress(), address.getPort());
                clientSocket.send(packet);
            }
        } catch (Exception e) {
            System.out.println("System:Client /m Exception");
            e.printStackTrace();
        }
    }

    public void online(){
        start("AC:");
    }
    public void offline(){
        start("DC:");
    }
}
