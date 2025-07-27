import java.io.*;
import java.net.*;



public class Server {
    public static void main(String[] args) throws Exception{
        String name = "shaikh";
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        ServerSocket connection = new ServerSocket(8888);
        Socket socket = connection.accept();

        Runnable sendLogic = new Runnable(){
            @Override
            public void run(){
                try{
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    String message;
                    while(true){
                        message = name + " /m  " + sc.readLine() + ".";
                        outputStream.writeUTF(message);
                    }
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        };

        Runnable recieveLogic = new Runnable(){
            @Override
            public void run(){
                try{
                    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                    String message;
                    while(true){
                        message = inputStream.readUTF();
                        System.out.println(message);
                    }
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }        
            }
        };
    
        Thread send = new Thread(sendLogic);
        Thread recieve = new Thread(recieveLogic);

        send.start();
        recieve.start();
    }
}