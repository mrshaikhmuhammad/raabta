package net;

import java.net.*;

public class Client{
    private String senderName;
    private Address address;
    private DatagramSocket clientSocket;

    public Client(Address address){
        this.senderName = address.getName();
        this.address = address;

        try{
            clientSocket = new DatagramSocket();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setAddress(Address address){
        this.address = address;
    }


    public void send(String text) {
        try{    
            byte[] message= ("SM:" + senderName + ":" + text).getBytes();
            DatagramPacket packet = new DatagramPacket(message, message.length, address.getAddress(), address.getPort());
            clientSocket.send(packet);
            System.out.println("SM:" + senderName + ":" + text + " to " + address );
        } catch (Exception e) {
            System.out.println("System:Client /m Exception");
            e.printStackTrace();
        }
    }

    public void inform() {
        try{    
            byte[] message= ("AC:" + senderName).getBytes();
            DatagramPacket packet = new DatagramPacket(message, message.length, address.getAddress(), address.getPort());
            clientSocket.send(packet);
            System.out.println("AC:" + senderName + " to " + address );
        } catch (Exception e) {
            System.out.println("System:Client /m Exception");
            e.printStackTrace();
        }
    }
}