import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*; 
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import javax.swing.*;  



public class GUI extends JFrame{
    public static String[] word;
    public static int lives = 6;
    public GUI(){
        
        setTitle("Hangman");
        setSize(1000, 1000);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color c1 = new Color(245, 245, 220);
        setBackground(c1);
        initialize();
        
        
        
    }
    public void paint(Graphics g){
        
        g.drawLine(200,800,500,800);
        g.drawLine(350,800, 350, 400);
        g.drawLine(350,400, 600, 400);
        g.drawLine(600,400, 600, 500);
        if (get_Lives() >= 1){
        g.drawOval(575, 500, 50, 50); //head
        }
        if (get_Lives() >= 2){
        g.drawLine(600,550, 600, 650); //neck
        }
        if (get_Lives() >= 3){
        g.drawLine(600,575, 635, 600); //right arm
        }
        if (get_Lives() >= 4){
        g.drawLine(600,650, 635, 675); //right leg
    }
    if (get_Lives() >= 5){
        g.drawLine(600,575, 565, 600); //left arm
    }
    if (get_Lives() >= 6){
        g.drawLine(600,650, 565, 675); //left leg
    }
        draw_word(g);
    }

    public void draw_word(Graphics g){
        g.drawLine(100, 900, 150, 900);
        g.drawLine(200, 900, 250, 900);
        g.drawLine(300, 900, 350, 900);
        g.drawLine(400, 900, 450, 900);
    }
    public static void main(String args[]){
           GUI gui = new GUI();
           rungame();
           System.out.println("RUNNING GIU");
    }
    public  int get_Lives(){
        return lives;
    }
    public static void rungame(){
        System.out.println("WORD TO GFUESS:" + Arrays.toString(word));
        ArrayList<String> guesses = new ArrayList<>();
        ArrayList<String> wordl = new ArrayList<>(Arrays.asList(word));
        ArrayList<String> correct_word = new ArrayList<>(Arrays.asList(word));
        ArrayList<String> display_word = setdisplay(wordl);
        
        while (lives > 0){
        GUI gui = new GUI();
        gui.repaint();
        String guess = get_guess();
        System.out.println("GUESSES:" + guesses);
        System.out.println("DISPLAY:" + display_word);
        guesses.add(guess);
        if (wordl.contains(guess) == true){
            JOptionPane.showMessageDialog (null, "Correct Guess!", "Title", JOptionPane.INFORMATION_MESSAGE);
            while (wordl.contains(guess) == true){
                int index = wordl.indexOf(guess);
                display_word.set(index, guess);
                wordl.set(index, null);
                System.out.println("Checking equality of: " + wordl + display_word);
                if (correct_word.equals(display_word) == true){
    
                    JOptionPane.showMessageDialog (null, "YOU WIN!", "Title", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    System.out.println("NOT EQUAL");
                }
            }
        } else {
            lives--;
            JOptionPane.showMessageDialog (null, "Incorrect Guess!", "Title", JOptionPane.WARNING_MESSAGE);
            
        }
        }
        JOptionPane.showMessageDialog (null, "YOU LOSE!", "Title", JOptionPane.WARNING_MESSAGE);
            
    
    
    }
    
    public static ArrayList<String>  setdisplay(ArrayList<String> word){
        ArrayList<String> display = new ArrayList<>();
        for (String e : word){
            display.add(null);
        }
        return display;
    }
    
    public static String get_guess(){
        
    JFrame f1;
    
    while(true){
    f1 = new JFrame();
    String guess = JOptionPane.showInputDialog(f1,"Enter Single Character guess");
    if (guess.length() > 1){
        
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JOptionPane.showMessageDialog(f1,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
        f1.dispose();
    } else {
    f1.dispose();
    
    return guess;
    }
    System.out.println("Running");
    }
    }
    
    public static void initialize(){
        boolean internet = false; //do you have an internet connection?
        int length = 4; //joptionpane for word length between two given ranges
        ArrayList<String> word_list = new ArrayList<>();
        if (internet == false){
        word_list = generate_word_list();
        String s_word = game_word(length, word_list);
        word = new String[s_word.length()];
         word  = s_word.split("");
        }
    }
    
    public static String game_word(int length, ArrayList<String> words){
        Random rand = new Random();
        int a_len = words.size();
        int count = 0;
        while(true){
            count++;
            int rnum = rand.nextInt(a_len);
            if (words.get(rnum).length() == length){
                return words.get(rnum);
            }
            if (count == a_len){
                break;
            }
        }
        throw new java.lang.Error("Word not Found");
    }
    public static ArrayList<String> generate_word_list(){
        Scanner reader = null;
        ArrayList<String> words = new ArrayList<>();
        String filename = "words.txt";
        File list = new File(filename);
    
        try {
                reader = new Scanner(list);
            } catch (FileNotFoundException e) {
                System.out.println("file \"" + filename + "\" not found");
                System.exit(0);
            }
    
            while(reader.hasNextLine()) {
                String word = reader.nextLine();
                words.add(word);
            }
            return words;
    
    }
    }
    
