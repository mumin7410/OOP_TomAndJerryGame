import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

class GameOver extends JFrame {
    JButton play = new JButton("start");

    public GameOver() {
        setLayout(new BorderLayout());
        JPanel j = new JPanel();
        j.add(play);
        add(j, BorderLayout.CENTER);
    }
}

public class Display {
    public static void main(String[] args) {
        GameOver g = new GameOver();
        g.setVisible(true);
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.setSize(800, 600);
    }
}
