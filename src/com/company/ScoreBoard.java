package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.Graphics;

public class ScoreBoard {
    private final Image leaderboard_background = new ImageIcon("Textures\\highScoreback.jpg").getImage();
    private final Image back_icon = new ImageIcon ("Textures\\back_btn.png").getImage();
  //  private final Menu menu = new Menu() ;
    ArrayList<Players> All_players = new ArrayList <Players>();
    String[] names_arr = new String[5];
    int[] scores_arr = new int[5];

    private final Players player_obj =new Players();
    FileWriter fileWriter = null;
    BufferedWriter bufferWriter = null;
    PrintWriter printWriter = null;



    public void draw  (Graphics g) {
        g.drawImage(leaderboard_background, 0, 0, null);
        g.drawImage(back_icon, 10, 5, null);
        g.setColor(Color.yellow);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 45));
        g.drawString("   Name        Score", 90, 210);
        g.setColor(Color.yellow);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));

        //score display variables

        for (int i = 0; i < 5; i++) {
            //System.out.println(All_players.get(i).current_username +" awab" + All_players.get(i).getCurrent_score());
            g.drawString(String.valueOf(i + 1), 130, i * 50 + 260);
            g.drawString("-", 150, i * 50 + 260);
            g.drawString(All_players.get(i).getCurrent_username(), 170, i * 50 + 260);
            g.drawString(String.valueOf(All_players.get(i).getCurrent_score()), 360, i * 50 + 260);
        }


    }

    public void add_player(String n, int s){
        player_obj.current_username =n;
        player_obj.setCurrent_score(s);

        try {
            fileWriter = new FileWriter("players.txt", true);
            bufferWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferWriter);
            printWriter.println(player_obj.current_username);
            printWriter.println(player_obj.getCurrent_score());
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {

            try {
                if(bufferWriter != null)
                    bufferWriter.close();
            } catch (IOException e) {

            }
            try {
                if(fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {

            }
        }



    }
 public void read_players_fromFile()throws IOException {

        FileReader fr =new FileReader("players.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        String name_tmp="";
        String score_tmp="";


        while((line = br.readLine())!= null){
            Players obj_tmp = new Players();
            name_tmp= line;
            line = br.readLine();
            System.out.println(name_tmp);
            obj_tmp.setCurrent_username(name_tmp);
            score_tmp =line;
            System.out.println(score_tmp);
            obj_tmp.setCurrent_score(Integer.parseInt(score_tmp));
            All_players.add(obj_tmp);
        }

        br.close();
        fr.close();



        All_players.sort(new Comparator<Players>() {

            @Override
            public int compare(Players o1, Players o2) {
                return Integer.compare(o2.getCurrent_score(), o1.getCurrent_score());
            }
        });
        for (int i = 0; i < 5; i++) {
            names_arr[i] = All_players.get(i).getCurrent_username();
            scores_arr[i] = All_players.get(i).getCurrent_score();
        }

    }



}
