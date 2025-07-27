import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        String name = "waseem";
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));

        Socket socket = new Socket("192.168.223.84",8888);

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
