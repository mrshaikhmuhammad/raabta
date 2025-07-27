import java.net.*;
import java.io.*;
import java.util.*;

public class C1 {
    public static void main(String[] args) {
        try {
    
            Scanner sc = new Scanner(System.in);
            
            DatagramSocket clientSocket = new DatagramSocket();

            byte[] sendData = new byte[1024];
            byte[] recieveData = new byte[1024];

            System.out.println("Enter Sentence: ");
            sendData = sc.next().getBytes();

            int serverPort = 8888;
            InetAddress IPAddress = InetAddress.getByName("localhost");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
            clientSocket.send(sendPacket);


            DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
            clientSocket.receive(recievePacket);

            System.out.println("Recieved: " + new String(recievePacket.getData()));

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
