package net;
import java.net.*;
import java.util.StringTokenizer;

public class Address {
    private String ip;
    private int port;

    public Address(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            ip = address.getHostAddress();
            StringTokenizer st = new StringTokenizer(ip, ".");   
            for(int i=0; i<3; i++)
                st.nextToken();
            port = 1024+Integer.parseInt(st.nextToken());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public Address(String ip){
        this.ip = ip;

        StringTokenizer st = new StringTokenizer(ip, ".");   
        for(int i=0; i<3; i++)
            st.nextToken();
        this.port = 1024+Integer.parseInt(st.nextToken());
    }

    public int getPort(){
        return port;
    }
    public String getIP(){
        return ip;
    }

    public void setPort(int port){
        this.port = port;
    }
    public void setIP(String ip){
        this.ip = ip;
    }

    public InetAddress getAddress() throws Exception{
        return InetAddress.getByName(ip);
    }

    
    @Override
    public String toString() {
        return ip + ":" + port;
    }
}
