package net;

import java.net.*;
import java.util.*;

public class Server extends Thread{
    int port;
    ArrayList<Address> contacts;

    public Server(Address address, ArrayList<Address> contacts){
        this.port = address.getPort();
        this.contacts = contacts;
    }

    @Override
    public void run() {
        byte[] data = new byte[1024];
        DatagramPacket packet;

        try (
            DatagramSocket socket = new DatagramSocket(port);
        ) {
            while (true) {
                try {
                    packet = new DatagramPacket(data, data.length);
                    socket.receive(packet);
                    if(!contacts.contains(new Address(packet.getAddress().getHostAddress()))){
                        contacts.add(new Address(packet.getAddress().getHostAddress()));
                    }
                    System.out.println(new String(packet.getData(), 0, packet.getLength()));
                } catch (Exception e) {
                    System.out.println("System:Server /m Exception");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("System.Server /m Unable to establish connection");
            e.printStackTrace();
        }
    }
}