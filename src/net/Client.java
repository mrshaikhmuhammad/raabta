package net;

import java.io.*;
import java.net.*;

public class Client extends Thread{
    private String senderName;
    private Address address;
    private DatagramSocket clientSocket;

    public Client(String senderName, Address address){
        this.senderName = senderName;
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

    @Override
    public void run() {
        try (
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));            
        ) {
            String message;
            while (true) {
                message = senderName + " /m " + sc.readLine();
                byte[] data = message.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, address.getAddress(), address.getPort());
                clientSocket.send(packet);
            }
        } catch (Exception e) {
            System.out.println("System:Client /m Exception");
            e.printStackTrace();
        }
    }
}