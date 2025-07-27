package tcp;

import java.io.*;
import java.net.*;

class Server{
    public static void main(String[] args) {
        try(
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket connection = serverSocket.accept();
            BufferedReader inClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream outClient = new DataOutputStream(connection.getOutputStream());

        ){
            String input = inClient.readLine();
            String output = input.toUpperCase() + "\n";

            outClient.writeBytes(output);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}