package itismeucci.tpsit;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    
    ServerSocket server;
    Socket client;
    String str;
    BufferedReader in;
    PrintWriter out;

    public ServerThread(Socket client) throws IOException {

        this.client = client;
        server = new ServerSocket();
        str = "";
        in = null;
        out = null;
    }

    public void run() {

        try {
            communicate();
        } catch (Exception e) {

        }
    }
    
    
    public void communicate() {

        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        

        NumberGenerator x = new NumberGenerator();
        int num = x.generateNumber();
        System.out.println(num);
        for (;;) {

            String str;
            try {
                while (Integer.parseInt(str = in.readLine()) != num) {

                    System.out.println("Client: " + str);
                    out.println("Numero Sbagliato");
                }

                out.println("Hai indovinato!");
                closeConnection();

            } catch (Exception e) {
                out.println("Stringa invalida. E' necessario inviare un numero");
            }
        }
    }
    
    public void closeConnection() {

        System.out.println("Closing connection...");
        try {
            in.close();
            out.close();
            client.close();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

}
