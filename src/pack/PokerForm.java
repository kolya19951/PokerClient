package pack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.text.AttributedCharacterIterator;


/**
 * Created by Коля on 18.05.2015.
 */
public class PokerForm extends JFrame {


    private JPanel rootpanel;
    private JButton raiseButton;
    private JButton foldButton;
    private JButton checkButton;
    private JTextField raise;
    private JRadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    protected JRadioButton[] radioButtons = new JRadioButton[]{radioButton1, radioButton2, radioButton3, radioButton4, radioButton5};
    private JLabel name0, name1, name2, name3, name4, name5;
    protected JLabel[] names = new JLabel[]{name0, name1, name2, name3, name4, name5};
    private JLabel bankroll0, bankroll1, bankroll2, bankroll3, bankroll4, bankroll5;
    private JLabel flop1, flop2, flop3;
    protected JLabel[] flops = new JLabel[]{flop1, flop2, flop3};
    protected JLabel turn;
    protected JLabel river;
    private JLabel bet0, bet1, bet2, bet3, bet4, bet5;
    protected JLabel bank;
    private JButton button1;
    private JTextField message;
    private JLabel card1_0, card2_0, card1_1, card2_1, card1_2, card2_2, card1_3, card2_3, card1_4, card2_4, card1_5, card2_5;
    protected JLabel[] cards = new JLabel[]{card1_0, card2_0, card1_1, card2_1, card1_2, card2_2, card1_3, card2_3, card1_4, card2_4, card1_5, card2_5};
    protected JLabel[] bets = new JLabel[]{bet0, bet1, bet2, bet3, bet4, bet5};
    protected JLabel[] bankrolls = new JLabel[]{bankroll0, bankroll1, bankroll2, bankroll3, bankroll4, bankroll5};
    Command serverCommand;
    private Client client;
    private Image img;


    public PokerForm() {
        super("PokerGame");

        client = new Client();


        setContentPane(rootpanel);

        setButtonsDisable();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.SendUTF(message.getText());
                message.setVisible(false);
                button1.setVisible(false);

            }
        });
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.SendInt(0);
                setButtonsDisable();
            }
        });
        foldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.SendInt(-1);
                setButtonsDisable();
            }
        });
        raiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You raise");
                client.SendInt(Integer.parseInt(raise.getText()));
                setButtonsDisable();
            }

        });

        serverCommand = new Command("", client, this);
        WaitCommand(client);

    }

    public void WaitCommand(Client client) {
        System.out.println("w8");
        serverCommand.command = client.ReadUTF();
        try {
            serverCommand.Action();
        } catch (CommandException e) {
            e.printStackTrace();
            WaitCommand(client);
        }
    }

    class BgPanel extends JPanel {
        public void paintComponent(Graphics g) {
            Image im = null;
            try {
                im = ImageIO.read(new File("C:\\table1.jpg"));
            } catch (IOException e) {
            }
            g.drawImage(im, 0, 0, null);
        }
    }

    private void setButtonsDisable() {
        checkButton.setEnabled(false);
        raiseButton.setEnabled(false);
        foldButton.setEnabled(false);
    }

    public void setButtonsEnable() {
        checkButton.setEnabled(true);
        raiseButton.setEnabled(true);
        foldButton.setEnabled(true);
    }
}
