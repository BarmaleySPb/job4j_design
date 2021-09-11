package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class EchoServer {

    public static void main(String[] args) throws IOException {

        Map<String, String> commands = Map.of(
                "/?msg=Exit", "Server down.",
                "/?msg=Hello", "Hello.",
                "/?msg=", "What?"
        );

        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        String[] command = str.split(" ");
                        if (str.contains("/?msg=") && commands.get(command[1]) != null) {
                            out.write(commands.get(command[1]).getBytes());
                            if (command[1].contains("Exit")) {
                                server.close();
                            }
                        } else {
                            out.write(("What?").getBytes());
                        }
                        break;
                    }
                    out.flush();
                }
            }
        }
    }
}