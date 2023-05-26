import java.util.ArrayList;

public class Main implements ModelObserver{
    private final GUI gui;
    Main() {
        ArrayList<String> temp = new ArrayList<>();
        Game game = new Game(temp,this);
        gui = new GUI(game);
        gui.setVisible(true);
    }
    @Override
    public void modelChanged() {
      gui.repaint();
    }
    public static void main(String[] args){
        new Main();
    }
}
