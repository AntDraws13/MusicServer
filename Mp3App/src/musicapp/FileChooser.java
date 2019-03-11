/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicapp;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author antdr
 */
public class FileChooser extends javax.swing.JFrame {

    private ArrayList<String> songs;
    DataInputStream dis;
    DataOutputStream dos;
    Socket s;
    BufferedReader br;

    public FileChooser(Socket s, DataInputStream dis, DataOutputStream dos, BufferedReader br, ArrayList<String> songs) throws IOException {
        initComponents();
        initComponents2();
        this.dis = dis;
        this.dos = dos;
        this.songs = songs;
        this.s = s;
        this.br = br;
        add();
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        actionLabel = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        plause = new javax.swing.JButton();
        stopBut = new javax.swing.JButton();
        songL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jList1.setModel(demoList);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel4.setLayout(new java.awt.BorderLayout());

        actionLabel.setText("...");

        plause.setText("Play");
        plause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plauseActionPerformed(evt);
            }
        });
        jPanel5.add(plause);

        stopBut.setText("stop");
        stopBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButActionPerformed(evt);
            }
        });
        jPanel5.add(stopBut);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(songL)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(actionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(270, Short.MAX_VALUE)
                .addComponent(songL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponents2() throws IOException {
        plause.setIcon(check);
        stopBut.setIcon(stopped);
    }

    private void plauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plauseActionPerformed
        if (btnSetting == 0) {
            start();
        } else if (btnSetting == 1) {
            pause();
        } else if (btnSetting == 2) {
            resume();
        }
    }//GEN-LAST:event_plauseActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        initSong();
    }//GEN-LAST:event_jList1MouseClicked

    private void stopButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButActionPerformed
        reset();
    }//GEN-LAST:event_stopButActionPerformed

    private void add() {
        try {
            for (String s : songs) {
                paths[pathIndex] = s;
                Path p = Paths.get(s);
                demoList.addElement(p.getFileName());
                pathIndex++;
            }
        } catch (Exception e) {
            actionLabel.setText("No file has been choosen");
        }
    }

    private void del() {
        int index = jList1.getSelectedIndex();
        if (index > -1) {
            String name = jList1.getSelectedValue();
            actionLabel.setText(name + " has been deleted");
            demoList.remove(index);
        } else {
            actionLabel.setText("No item selected");
        }
    }

    private void start() {
        if (!doing) {
            try {
                cont.download();
                cont.play();
                plause.setIcon(paused);
                plause.setText("Pause");
                actionLabel.setText(songname + " has started");
                timeShow();
                btnSetting = 1;
                ListAction = true;
                doing = true;
            } catch (Exception e) {
                actionLabel.setText("Song has not been selected");
            }
        }
    }

    private void timeShow() {
        new Thread() {
            public void run() {
                try {
                    while (!cont.isComplete()) {
                        minutes = (cont.getPost() / 1000) / 60;
                        seconds = (cont.getPost() / 1000) % 60;
                        songtime = String.format("%02d:%02d", minutes, seconds);
                        songL.setText("Time: " + songtime);
                        Thread.sleep(500);
                    }
                    reset();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();
    }

    private void pause() {
        plause.setIcon(playing);
        plause.setText("Resume");
        actionLabel.setText(songname + " has been paused");
        cont.pause();
        btnSetting = 2;
    }

    private void resume() {
        cont.resume();
        plause.setText("Pause");
        plause.setIcon(paused);
        actionLabel.setText(songname + " has been resumed");
        btnSetting = 1;
    }

    private void initSong() {
        songIni = jList1.getSelectedIndex();
        System.out.println(paths[songIni]);
        if (!doing) {
            if (!ListAction) {
                btnSetting = 0;
                Path p = Paths.get(paths[songIni]);
                songname = p.getFileName().toString();
                this.cont = new Control(s, dis, dos, br, p);
                System.out.println("initialized: " + songname);
            }
        }
    }

    private void reset() {
        if (cont != null) {
            ListAction = false;
            doing = false;
            this.cont.close();
            btnSetting = 0;
            plause.setText("Play");
            plause.setIcon(check);
            songL.setText(null);
            actionLabel.setText(songname + " has been stopped");
        } else {
            actionLabel.setText("There is no song to stop");
        }
    }

    //Variables declaration for usage
    DefaultListModel demoList = new DefaultListModel();
    int pathIndex = 0, songIni = 0, btnSetting = 0;
    boolean ListAction = false, doing = false;
    long seconds, minutes;
    String songname, songtime;
    Control cont;
    String[] paths = new String[200];
    BufferedImage CheckOriginal = ImageIO.read(getClass().getResource("/img/checked.png"));
    Image checked = CheckOriginal.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    Icon check = new ImageIcon(checked);
    BufferedImage MinusOriginal = ImageIO.read(getClass().getResource("/img/minus.png"));
    Image minus = MinusOriginal.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    Icon delete = new ImageIcon(minus);
    BufferedImage PauseOriginal = ImageIO.read(getClass().getResource("/img/pause.png"));
    Image pause = PauseOriginal.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    Icon paused = new ImageIcon(pause);
    BufferedImage PlayOriginal = ImageIO.read(getClass().getResource("/img/play-button.png"));
    Image play = PlayOriginal.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    Icon playing = new ImageIcon(play);
    BufferedImage PlusOriginal = ImageIO.read(getClass().getResource("/img/plus.png"));
    Image plus = PlusOriginal.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    Icon added = new ImageIcon(plus);
    BufferedImage StopOriginal = ImageIO.read(getClass().getResource("/img/stop.png"));
    Image stop = StopOriginal.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    Icon stopped = new ImageIcon(stop);
    //End of usage variable declaration

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actionLabel;
    public javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton plause;
    private javax.swing.JLabel songL;
    private javax.swing.JButton stopBut;
    // End of variables declaration//GEN-END:variables
}
