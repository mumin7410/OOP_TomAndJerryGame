import java.awt.*;
import javax.swing.*;
import java.net.URL;
class ttested extends JPanel implements Runnable{
    URL imageURL_jerry = this.getClass().getResource("jerry.png");
    Image image_jerry = new ImageIcon(imageURL_jerry).getImage();
    ttested()
        {
            JFrame j = new JFrame();
            j.setBackground(Color.blue);
            j.setSize(200,200);
            j.add(this);
            j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            j.setVisible(true);
        }
    int i=10;
    protected void paintComponent(Graphics g){
            g.setColor(Color.RED);
            g.drawString("me",i,i);
            g.drawImage(image_jerry,i,i,58,58,this);
            super.paintComponent(g);
            g.setFont(new Font("Arial",1,20));
            g.drawString("Hello",50,50);
            

        }
    public void run(){
        while(true){
            i+=20;
            repaint();
            System.out.println("Hello");
            try{Thread.sleep(1000);}catch(Exception e){}
        }
    }
} 
public class ttest{
    public static void main(String args[]){
        ttested tt = new ttested();
        Thread t = new Thread(tt);
        t.start();
    }
}
