package tcp;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception{
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost",8888);

        BufferedReader inServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        DataOutputStream outServer = new DataOutputStream(clientSocket.getOutputStream());

        System.out.println("Enter Sentence: " );
        String input = sc.readLine();


        outServer.writeBytes(input + "\n");

        String output = inServer.readLine();

        System.out.println("From Server: " + output);

        clientSocket.close();
    }
}
