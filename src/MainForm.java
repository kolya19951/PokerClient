import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Коля on 18.05.2015.
 */
public class MainForm extends JFrame{
    private JPanel panel1;
    public JButton button1;
    public JTextField message;
    String line;
    public Client client;
    public MainForm(){
        super("Hello  world");

        client = new Client();


        setContentPane(panel1);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    client.out.writeUTF(message.getText());

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                final PokerForm form2 = new PokerForm(client);
                form2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                form2.setSize(500, 500);
                form2.setVisible(true);
                FormsController control2 = new FormsController(form2, client);

        }
    });
    }}

