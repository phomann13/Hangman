import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import javax.swing.*;  

public class Game {
    public static String[] word;
    public static int lives = 6;
    public ArrayList<String> display_word;
    ModelObserver observer;
public static void main(String[] args) {
    
}
public Game(ArrayList<String> display_word, ModelObserver modelObserver){
    this.display_word = display_word;
    observer = modelObserver;
    initialize();
    System.out.println("No Errors");
}
public  int get_Lives(){
    return lives;
}


public final void rungame(){
    System.out.println("WORD TO GUESS:" + Arrays.toString(word));
    ArrayList<String> guesses = new ArrayList<>();
    ArrayList<String> wordl = new ArrayList<>(Arrays.asList(word));
    ArrayList<String> correct_word = new ArrayList<>(Arrays.asList(word));
    ArrayList<String> display_word = setdisplay(wordl);
    System.out.print("After calling setDsipaly" + display_word);
    
    while (lives > 0){
    String guess = get_guess();
    System.out.println("GUESSES:" + guesses);
    System.out.println("DISPLAY:" + display_word);
    guesses.add(guess);
    if (wordl.contains(guess) == true){
        JOptionPane.showMessageDialog (null, "Correct Guess!", "Title", JOptionPane.INFORMATION_MESSAGE);
        while (wordl.contains(guess) == true){
            int index = wordl.indexOf(guess);
            display_word.set(index, guess);
            observer.modelChanged();
            System.out.println("After setting: " + display_word);
            wordl.set(index, null);
            //repaint();
            System.out.println("Checking equality of: " + correct_word + display_word);
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
        display.add("_");
    }
    
    
    System.out.println("Inside of setdisplay" + display);
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
    ArrayList<String> display_word = new ArrayList<>();
    if (internet == false){
    word_list = generate_word_list();
    String s_word = game_word(length, word_list);
    word = new String[s_word.length()];
    word  = s_word.split("");
    for(int i = 0; i < word.length; i++){
        System.out.print("Char is: " + word[i] );
        display_word.add("_");
    }
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

