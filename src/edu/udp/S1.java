import java.net.*;
import java.io.*;

public class S1{
    public static void main(String[] args) {
        try{
            //Create Port
            int serverPort=8888;
            DatagramSocket serverSocket = new DatagramSocket(serverPort);

            byte[] sendData = new byte[1024];
            byte[] recieveData = new byte[1024];

            DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
            serverSocket.receive(recievePacket);
            System.out.println("Data Recieved");

            String input = new String(recievePacket.getData());
            String output = input.toUpperCase();
            sendData = output.getBytes();
            System.out.println("Data Processed");

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, recievePacket.getAddress(), recievePacket.getPort());
            serverSocket.send(sendPacket);
            System.out.println("Data Sent");
            
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}