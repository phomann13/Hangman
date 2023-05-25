import java.util.*;
import java.io.*;
import javax.swing.*;  
public class Game{
    public static String[] word;
public static void main(String[] args) {
    initialize();
    rungame();
    
    System.out.println("No Errors");

}
public static void rungame(){
    System.out.println("WORD TO GFUESS:" + Arrays.toString(word));
    ArrayList<String> guesses = new ArrayList<>();
    ArrayList<String> wordl = new ArrayList<>(Arrays.asList(word));
    ArrayList<String> display_word = setdisplay(wordl);
    int lives = 6;
    while (lives > 0){
    String guess = get_guess();
    guesses.add(guess);
    System.out.println("GUESSES:" + guesses);
    System.out.println("DISPLAY:" + display_word);
    if (wordl.contains(guess) == true){
        JOptionPane.showMessageDialog (null, "Correct Guess!", "Title", JOptionPane.INFORMATION_MESSAGE);
        while (wordl.contains(guess) == true){
            int index = wordl.indexOf(guess);
            display_word.set(index, guess);
            wordl.set(index, null);
            if (wordl.equals(display_word)){
                JOptionPane.showMessageDialog (null, "YOU WIN!", "Title", JOptionPane.INFORMATION_MESSAGE);
                return;
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