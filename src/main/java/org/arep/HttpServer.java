package org.arep;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {


    private static final int PORT = 35000;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }


            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            
            boolean firtsLine = true;
            String uriStr = "";
            
            while ((inputLine = in.readLine()) != null) {
                if (firtsLine) {
                    uriStr = inputLine.split(" ")[1];
                    firtsLine = false;
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            if (uriStr.startsWith("/Pelicula?s=")) {
                outputLine = HttpConnection.httpClientAPI(uriStr.split("=")[1]);
                System.out.println("dmbhfiuerbfcoirncoir" + uriStr);
            } else if (uriStr.startsWith("/Pelicula")) {
                outputLine = HttpConnection.httpClientHtml();
            }else {
                outputLine = HttpConnection.httpError();
            }

            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }
}
