/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import javazoom.jl.player.Player;

/**
 *
 * @author antdr
 */
public class Control {

    Thread t;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    BufferedReader br;
    Path p;

    public Control(Socket s, DataInputStream dis, DataOutputStream dos, BufferedReader br, Path p) {
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.br = br;
        this.p = p;
    }

    public void download() {
        String song = p.getFileName().toString();
        File file = new File(song);
        if (!file.exists()) {
            try {
                dos.writeUTF(p.toString());
                song = dis.readUTF();
                System.out.println("Receving file: " + song);
                System.out.println("Saving as file: " + song);
                long fileSize = dis.readLong();
                System.out.println("Receiving " + fileSize + " bytes of data");
                System.out.println("File Size: " + (fileSize / (1024 * 1024)) + " MB");
                byte b[] = new byte[(int) fileSize];
                System.out.println("Receving file..");
                FileOutputStream fos = new FileOutputStream(new File(p.getFileName().toString()), true);
                int bytesRead;
                while (fileSize > 0 && (bytesRead = dis.read(b, 0, (int) Math.min(b.length, fileSize))) != -1) {
                    fos.write(b, 0, bytesRead);
                    fileSize -= bytesRead;
                }
//                do {
//                    bytesRead = dis.read(b, 0, b.length);
//                    fos.write(b, 0, b.length);
//                } while (!(bytesRead < sz));
                System.out.println("Comleted");
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (player != null) {
            t.stop();
        }
    }

    public void play() {
        try {
            String name = p.getFileName().toString();
            FileInputStream fis = new FileInputStream(name);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + path);
            System.out.println(e);
        }
        t.start();
    }

    public void pause() {
        t.suspend();
    }

    public void resume() {
        t.resume();
    }

    public boolean isComplete() {
        return player.isComplete();
    }

    public long getPost() {
        return player.getPosition();
    }
    private String path;
    private Player player;
    public int location = 0;

}
