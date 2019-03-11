/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicapp;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author antdr
 */
public class MusicApp {

    private static ArrayList<String> songs;
    private static String json;
    private static Scanner sc = new Scanner(System.in);
    private static DataInputStream dis;
    private static DataOutputStream dos;
    private static BufferedReader br;
    public static void main(String[] args) throws IOException {
        try {
            String address = "";
            System.out.println("Enter Server Address: ");
            address = sc.nextLine();
            Socket s = new Socket(address, 5056);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
            Gson gson = new Gson();
            json = dis.readUTF();
            songs = gson.fromJson(json, ArrayList.class);
            FileChooser mp3 = new FileChooser(s,dis,dos,br,songs);
            mp3.setVisible(true);
            while(mp3.isActive());
        } catch (Exception e) {
            System.out.println("algo");
            e.printStackTrace();
            br.close();
            dos.close();
            dis.close();
        }
    }
}
