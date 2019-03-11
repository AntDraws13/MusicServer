/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 * @author antdr
 */
// ClientHandler class 
class ClientHandler extends Thread {

    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    ArrayList<String> songs = new ArrayList<String>();
    String json;
    Gson gson;

    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        fillPath();
        gson = new Gson();
        json = gson.toJson(songs);
    }

    private String getFileExtension(Path file) {
        if (file == null) {
            return "";
        }
        String name = file.getFileName().toString();
        int i = name.lastIndexOf('.');
        String ext = i > 0 ? name.substring(i + 1) : "";
        return ext;
    }

    private void fillPath() {
        try (Stream<Path> filePathStream = Files.walk(Paths.get("C:/Music/"))) {
            filePathStream.forEach(filePath -> {
                if (Files.isRegularFile(filePath) && getFileExtension(filePath).equals("mp3")) {
                    songs.add(filePath.toString());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            dos.writeUTF(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                String song = "";
                song = dis.readUTF();
                try {
                    System.out.println("Requested song: " + Paths.get(song).getFileName().toString());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                if (!song.equals("stop")) {
                    File file = new File(song);
                    dos.writeUTF(file.getName());
                    dos.flush();
                    FileInputStream fin = new FileInputStream(file);
                    int size = (int) file.length();
                    byte b[] = new byte[size];
                    int read;
                    dos.writeUTF(Long.toString(size));
                    dos.flush();
                    System.out.println("Size: " + size);
                    System.out.println("Buf size: " + s.getReceiveBufferSize());
                    while ((read = fin.read(b)) != -1) {
                        dos.write(b, 0, read);
                        dos.flush();
                    }
                    fin.close();
                    while (dis.available() > 0);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            this.dis.close();
            this.dos.close();
            this.s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
