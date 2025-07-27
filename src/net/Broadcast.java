package net;
import java.net.*;

public class Broadcast{
    private String name;
    private Address system = new Address();
    private Address[] contacts = new Address[254];

    public Broadcast(String name){
        this.name = name;
    }

    public void start(){
        int index = system.getIP().lastIndexOf(".");
        String network = system.getIP().substring(0, ++index);

        for(int i=0; i<254; i++){
            contacts[i] = new Address(network + (i+1));
        }

        try (
            DatagramSocket clientSocket = new DatagramSocket();
        ) {
            byte[] data = (name + " /i " + "online").getBytes();
            for(Address address: contacts) {;
                DatagramPacket packet = new DatagramPacket(data, data.length, address.getAddress(), address.getPort());
                clientSocket.send(packet);
            }
        } catch (Exception e) {
            System.out.println("System:Client /m Exception");
            e.printStackTrace();
        }
    }
}
