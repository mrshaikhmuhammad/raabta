package net;

import java.net.*;
import java.util.*;
import javax.websocket.*;

public class Server extends Thread{
    int port;
    ArrayList<Address> contacts;
    Session session;
    String senderName;

    public Server(String senderName, Address address, Session session, ArrayList<Address> contacts){
        this.port = address.getPort();
        this.contacts = contacts;
        this.session = session;
        this.senderName = senderName;

        System.out.println("Server is using " + address);
    }

    @Override
    public void run() {
        byte[] data = new byte[1024];
        DatagramPacket packet;

        Client client;
        boolean contains;
        String message, name;

        try (
            DatagramSocket socket = new DatagramSocket(port);
        ) {
            while (true) {
                try {

                    // Recieve Packet
                    packet = new DatagramPacket(data, data.length);
                    socket.receive(packet);
                    

                    //Store Message
                    message = new String(packet.getData(), 0, packet.getLength());
                    System.out.println(message + " receved");
                    try{
                        name = message.substring(message.indexOf(":")+1, message.lastIndexOf(":"));
                    } catch(Exception e){
                        name = message.substring(message.indexOf(":")+1);
                    }


                    //ADD CONTACT (AC), if not available
                    contains = false;
                    for(Address i: contacts){
                        if(i.getName().equals(name) && i.getIP().equals(packet.getAddress().getHostAddress())){
                            contains = true;
                            break;
                        }
                    }
                    if(!contains){
                        contacts.add(new Address(name, packet.getAddress().getHostAddress()));
                        session.getBasicRemote().sendText("AC:"+name);
                        System.out.println("AC:"+name + " to system");
                            
                        client = new Client(new Address(senderName, packet.getAddress().getHostAddress()));
                        client.inform();
                    }

                    session.getBasicRemote().sendText(message);
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