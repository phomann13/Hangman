import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*; 
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.applet.*;


public class GUI extends JFrame{
    public static String[] word;
    public static int lives = 6;
    public static ArrayList<String> display_word;
    public static boolean ran = false;
    public Graphics publicGraphics;
    private Game game;
    public GUI(Game game){
        this.game = game;
        setTitle("Hangman");
        setSize(1000, 1000);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color c1 = new Color(245, 245, 220);
        setBackground(c1);
        //display_word = setdisplay(display_word);
        game.rungame();
        
        }
        
        
        
        
    
    public void paint(Graphics g){
        int lives = game.get_Lives();
        g.drawLine(200,800,500,800);
        g.drawLine(350,800, 350, 400);
        g.drawLine(350,400, 600, 400);
        g.drawLine(600,400, 600, 500);
        if (game.get_Lives() >= 1){
        g.drawOval(575, 500, 50, 50); //head
        }
        if (lives >= 2){
        g.drawLine(600,550, 600, 650); //neck
        }
        if (lives >= 3){
        g.drawLine(600,575, 635, 600); //right arm
        }
        if (lives >= 4){
        g.drawLine(600,650, 635, 675); //right leg
    }
    if (lives >= 5){
        g.drawLine(600,575, 565, 600); //left arm
    }
    if (lives >= 6){
        g.drawLine(600,650, 565, 675); //left leg
    }
    System.out.println(ran);
    if (ran == true){
        publicGraphics = g;
        draw_word();
    }
    }

    public void draw_word(){
        Font font = publicGraphics.getFont().deriveFont(75.0f);
        publicGraphics.setFont(font);
        System.out.print("THE DISPLAY WORD CURRENT:LYT" + display_word);
        StringBuffer str = new StringBuffer();
        for(String s : display_word){
            str.append(s);
        }
        
        publicGraphics.drawString(str.toString(), 100, 900);
    }
   
    public static void main(String args[]){
        //initialize();
        //rungame();
        System.out.println("RUNNING GIU");
    }
}
    