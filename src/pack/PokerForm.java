package pack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Коля on 18.05.2015.
 */
public class PokerForm extends JFrame {


    private JPanel rootPanel;
    protected JButton raiseButton;
    protected JButton foldButton;
    protected JButton checkButton;
    protected JTextField raise;
    private JRadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    private JLabel name0, name1, name2, name3, name4, name5;
    protected JLabel[] names = new JLabel[]{name0, name1, name2, name3, name4, name5};
    protected JLabel card1_0, card2_0;
    private JLabel bankroll0, bankroll1, bankroll2, bankroll3, bankroll4, bankroll5;
    private JLabel flop1, flop2, flop3;
    protected JLabel[] flops = new JLabel[]{flop1, flop2, flop3};
    private JLabel turn;
    private JLabel river;
    private JLabel bet0, bet1, bet2, bet3, bet4, bet5;
    protected JLabel bank;
    private JButton button1;
    private JTextField message;
    protected JLabel[] bets = new JLabel[]{bet0, bet1, bet2, bet3, bet4, bet5};
    protected JLabel[] bankrolls = new JLabel[]{bankroll0, bankroll1, bankroll2, bankroll3, bankroll4, bankroll5};
    Command serverCommand;
    private Client client;

    public PokerForm() {
        super("PokerGame");

        client = new Client();

        setContentPane(rootPanel);
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
            }
        });
        foldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.SendInt(-1);
            }
        });
        raiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("You raise");
                client.SendInt(Integer.parseInt(raise.getText()));
            }
        });

        serverCommand = new Command("", client, this);
        WaitCommand(client);

    }

    public void WaitCommand(Client client) {
        System.out.println("w8");
        serverCommand.command = client.ReadUTF();
        serverCommand.Action();
        return;
    }



    /*class BgPanel extends JPanel {
        public void paintComponent(Graphics g) {
            Image im = null;
            try {
                im = ImageIO.read(new File("C:\\table1.jpg"));
            } catch (IOException e) {
            }
            g.drawImage(im, 0, 0, null);
        }
    }*/
}
