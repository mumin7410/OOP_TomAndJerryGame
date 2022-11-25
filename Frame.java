
//Run game ที่ไฟล์นี้ครับ
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//Tom 
class Tom extends JPanel implements ActionListener, Runnable {

    Timer time = new Timer(30, this);
    Timer Stunt_time = new Timer(2000, this);
    int Score_Game = 0;
    int Life_Score = 0;
    int state = 1;
    int Start = 0;
    // Tom
    int w = 70 + 90;
    int h = 100 + 90;
    int y = 480;
    int x = 460;

    int moveable = 1;
    int Stunt = 0;

    // Jerry
    int Dog = 0;
    int Dog2 = 0;
    int Dog3 = 0;
    int Rand_jerry = 0;

    int w_jerry = 40 + 25;
    int h_jerry = 60 + 25;

    int x_jerry1 = 140;
    int y_jerry1 = -35;

    int x_jerry2 = 460;
    int y_jerry2 = -95;

    int x_jerry3 = 780;
    int y_jerry3 = -135;

    char keyChar = 'A';

    // Import Image:
    URL imageURL = this.getClass().getResource("car.png");
    Image image = new ImageIcon(imageURL).getImage(); // Contain Image
    // bg1
    URL imageURL_bg = this.getClass().getResource("bgv2.jpg");
    Image image_bg = new ImageIcon(imageURL_bg).getImage(); // Contain Image
    // bg2
    URL imageURL_bg2 = this.getClass().getResource("bg1.jpg");
    Image image_bg2 = new ImageIcon(imageURL_bg2).getImage(); // Contain Image
    // StartGame bg;
    URL imageURL_bgStart = this.getClass().getResource("bgStart.jpg");
    Image image_bgStart = new ImageIcon(imageURL_bgStart).getImage(); // Contain Image
    // Jerry;
    URL imageURL_jerry = this.getClass().getResource("jerry.png");
    Image image_jerry = new ImageIcon(imageURL_jerry).getImage();
    // Dog;
    URL imageURL_Dog = this.getClass().getResource("Dog.png");
    Image image_Dog = new ImageIcon(imageURL_Dog).getImage();
    // TomDie;
    URL imageURL_TomStunt = this.getClass().getResource("tomStunt.png");
    Image image_TomStunt = new ImageIcon(imageURL_TomStunt).getImage();
    // GameOver Bg;
    URL imageURL_GameOver = this.getClass().getResource("GameOver.png");
    Image image_GameOver = new ImageIcon(imageURL_GameOver).getImage();

    // ArrList
    ArrayList<Image> JerryArr1 = new ArrayList<Image>();
    ArrayList<Image> JerryArr2 = new ArrayList<Image>();
    ArrayList<Image> JerryArr3 = new ArrayList<Image>();
    // ArrayList<Integer> JerryX = new ArrayList<Integer>();
    // ArrayList<Integer> JerryY = new ArrayList<Integer>();

    // JFrame
    JFrame j = new JFrame();
    JButton btn = new JButton("RESTART");
    JButton Start_btn = new JButton("START");
    JLabel Score = new JLabel("SCORE: " + "20");
    JLabel Life = new JLabel("LIFE: " + "5");
    JPanel Score_bar = new JPanel(new GridLayout(1, 1));

    // Constructor
    public Tom() {
        JerryArr1.add(image_jerry);
        JerryArr1.add(image_Dog);
        Score_bar.setBackground(new Color(151, 71, 70));
        btn.setBackground(new Color(151, 71, 70));
        btn.setForeground(Color.white);
        Start_btn.setBackground(new Color(151, 71, 70));
        Start_btn.setForeground(Color.white);
        Score_bar.setPreferredSize(new Dimension(5, 30));
        Score.setFont(new Font("Cocsolas", Font.PLAIN, 15));
        Life.setFont(new Font("Cocsolas", Font.PLAIN, 15));
        Score_bar.add(Score);
        Score_bar.add(Life);
        Score_bar.add(Start_btn);
        Score_bar.add(btn);
        // btn.setBounds(50, 50, 50, 50);
        j.setLayout(new BorderLayout());

        j.add(this);
        j.add(Score_bar, BorderLayout.NORTH);
        // j.add(btn, BorderLayout.SOUTH);

        j.setTitle("Tom and Jerry");
        j.setSize(1000, 800);
        j.setResizable(false);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLocationRelativeTo(null);
        j.setVisible(true);

        setFocusable(true);
        requestFocus();
        setVisible(true);

        // Key Listener------------------------
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Life_Score = 5;
                requestFocus();
            }
        });

        Start_btn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Start = 1;
                Life_Score = 5;
                y_jerry1 = -35;
                y_jerry2 = -95;
                y_jerry3 = -135;
                requestFocus();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (moveable == 1) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_A:
                            x -= 20;
                            break;
                        case KeyEvent.VK_D:
                            x += 20;
                            break;
                        default:
                            keyChar = e.getKeyChar();
                    }
                    if (x >= 780) {
                        x = 780;
                    } else if (x <= 140) {
                        x = 140;
                    }
                    repaint();
                }
            }
        });

    } // End constructor;

    // Paint
    protected void paintComponent(Graphics g) {
        Thread ts = new Thread(new Runnable() {
            public void run() {
                while (Stunt != 0) {
                    try {
                        Thread.sleep(1000);
                        Stunt = 0;
                        moveable = 1;
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        if (Start == 0) {
            // Start Bg;
            g.drawImage(image_bgStart, 0, 0, getWidth(), getHeight(), this);
        } else if (Life_Score > 0 && Start == 1) {
            // Background game;
            g.drawImage(image_bg, -30, 0, getWidth() + 40, getHeight(), this);
            if (Stunt == 0) {
                g.drawImage(image, x, y, w, h, this);
            } else if (Stunt == 1) {
                moveable = 0;
                g.drawImage(image_TomStunt, x, y, w, h, this);
                ts.start();
            }
            Rectangle tomRect = new Rectangle(x, y, w, h);
            // g2d.draw(tomRect);
            // 1
            Rectangle JerryRect = new Rectangle(x_jerry1, y_jerry1, w_jerry, h_jerry);
            // g2d.draw(JerryRect);
            g.drawImage(JerryArr1.get(Dog), x_jerry1, y_jerry1, w_jerry, h_jerry, this);
            // 2
            Rectangle JerryRect2 = new Rectangle(x_jerry2, y_jerry2, w_jerry, h_jerry);
            // g2d.draw(JerryRect2);
            g.drawImage(JerryArr1.get(Dog2), x_jerry2, y_jerry2, w_jerry, h_jerry, this);
            // 3
            Rectangle JerryRect3 = new Rectangle(x_jerry3, y_jerry3, w_jerry, h_jerry);
            // g2d.draw(JerryRect3);
            g.drawImage(JerryArr1.get(Dog3), x_jerry3, y_jerry3, w_jerry, h_jerry, this);

            if (tomRect.intersects(JerryRect)) {
                if (Dog == 0) {
                    Score_Game += 1;
                    y_jerry1 = 0;
                    double rand_Dog = Math.random();
                    if (rand_Dog > 0.5) {
                        Dog = 1;
                    } else if (rand_Dog < 0.5) {
                        Dog = 0;
                    }
                } else if (Dog == 1) {
                    y_jerry1 = 0;
                    Stunt = 1;
                    double rand_Dog = Math.random();
                    if (rand_Dog > 0.5) {
                        Dog = 1;
                    } else if (rand_Dog < 0.5) {
                        Dog = 0;
                    }
                }
            } else if (tomRect.intersects(JerryRect2)) {
                if (Dog2 == 0) {
                    Score_Game += 1;
                    y_jerry2 = 0;
                    double rand_Dog = Math.random();
                    if (rand_Dog > 0.5) {
                        Dog2 = 1;
                    } else if (rand_Dog < 0.5) {
                        Dog2 = 0;
                    }
                } else if (Dog2 == 1) {
                    Stunt = 1;
                    y_jerry2 = 0;
                    double rand_Dog = Math.random();
                    if (rand_Dog > 0.5) {
                        Dog2 = 1;
                    } else if (rand_Dog < 0.5) {
                        Dog2 = 0;
                    }
                }
            } else if (tomRect.intersects(JerryRect3)) {
                if (Dog3 == 0) {
                    Score_Game += 1;
                    y_jerry3 = 0;
                    double rand_Dog = Math.random();
                    if (rand_Dog > 0.5) {
                        Dog3 = 1;
                    } else if (rand_Dog < 0.5) {
                        Dog3 = 0;
                    }
                } else if (Dog3 == 1) {
                    Stunt = 1;
                    y_jerry3 = 0;
                    double rand_Dog = Math.random();
                    if (rand_Dog > 0.5) {
                        Dog3 = 1;
                    } else if (rand_Dog < 0.5) {
                        Dog3 = 0;
                    }
                }
            }

        } else if (Life_Score <= 0 && Start == 1) {
            g.drawImage(image_bg2, 0, 0, getWidth(), getHeight(), this);
            // g.drawImage(image_TomStunt, 460, 400, w + 50, h + 50, this);
            // g.drawImage(image_GameOver, 380, 90, w + 180, h + 180, this);
            Score_Game = 0;
            y_jerry1 = -35;
            y_jerry2 = -95;
            y_jerry3 = -135;
        }
        time.start();
        repaint();
    }

    // Action
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        y_jerry1 += 3 + (Score_Game / 10);
        y_jerry2 += 3 + (Score_Game / 10);
        y_jerry3 += 3 + (Score_Game / 10);

        Score.setText("SCORE: " + Score_Game);
        Life.setText("LIFE: " + Life_Score);
    }

    public void run() {
        // Background Music;
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("BGM.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(10);
        } catch (UnsupportedAudioFileException | IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while (true) {
            double rand_dog = Math.random();
            double rand_dog2 = Math.random();
            double rand_dog3 = Math.random();
            if (Start == 1) {
                if (y_jerry1 >= 700) {
                    if (Dog == 0) {
                        y_jerry1 = 0;
                        Life_Score -= 1;
                    } else if (Dog == 1) {
                        y_jerry1 = 0;
                    }
                    // double rand_dog = Math.random();
                    if (rand_dog > 0.5) {
                        Dog = 1;
                    } else if (rand_dog < 0.5) {
                        Dog = 0;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                } else if (y_jerry2 >= 700) {
                    if (Dog2 == 0) {
                        y_jerry2 = 0;
                        Life_Score -= 1;
                    } else if (Dog2 == 1) {
                        y_jerry2 = 0;
                    }
                    if (rand_dog2 > 0.5) {
                        Dog2 = 1;
                    } else if (rand_dog2 < 0.5) {
                        Dog2 = 0;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                } else if (y_jerry3 >= 700) {
                    if (Dog3 == 0) {
                        y_jerry3 = 0;
                        Life_Score -= 1;
                    } else if (Dog3 == 1) {
                        y_jerry3 = 0;
                    }
                    if (rand_dog3 > 0.5) {
                        Dog3 = 1;
                    } else if (rand_dog3 < 0.5) {
                        Dog3 = 0;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}

// ------------------------------------------------------------------------------

// Main Program:
public class Frame {
    public static void main(String[] args) {
        Tom tt = new Tom();
        Thread t1 = new Thread(tt);
        t1.start();
    }
}
