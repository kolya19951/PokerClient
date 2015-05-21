import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.jar.JarEntry;

/**
 * Created by Коля on 18.05.2015.
 */
public class PokerForm extends JFrame {


    private JPanel rootPanel;
    public JButton raiseButton;
    public JButton foldButton;
    public JButton checkButton;
    private JLabel card1;
    private JLabel card2;
    public JTextField raise;

    public PokerForm(Client client)
    {
        super("PokerGame");
        setContentPane(rootPanel);
        setVisible(true);
        String cards, suit1, suit2 = null, card_1 = null, card_2;
        try {
            cards = client.in.readUTF();
            System.out.println(cards);
            card_1 = cards.substring(0, cards.indexOf(","));
            card_2 = cards.substring(cards.indexOf("&") + 1, cards.lastIndexOf(","));
            suit1 = cards.substring(cards.indexOf(",") + 1, cards.indexOf("&"));
            suit2 = cards.substring(cards.lastIndexOf(",") + 1, cards.indexOf("."));
            if(suit1.equals("0"))
                card_1 += "♠";
            if(suit1.equals("1"))
                card_1 += "♣";
            if(suit1.equals("2"))
                card_1 += "♥";
            if(suit1.equals("3"))
                card_1 += "♦";
            if(suit2.equals("0"))
                card_2 += "♠";
            if(suit2.equals("1"))
                card_2 += "♣";
            if(suit2.equals("2"))
                card_2 += "♥";
            if(suit2.equals("3"))
                card_2 += "♦";
            card1.setText(card_1);
            card2.setText(card_2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class BgPanel extends JPanel{
        public void paintComponent(Graphics g){
            Image im = null;
            try {
                im = ImageIO.read(new File("C:\\table1.jpg"));
            } catch (IOException e) {}
            g.drawImage(im, 0, 0, null);
        }
    }

}
