import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Коля on 18.05.2015.
 */
public class MainForm extends JFrame{
    private JButton button1;
    private JPanel panel1;
    private JTextField message;
    String line;
    public MainForm(){
        super("Hello  world");

        final Client client = new Client();


        setContentPane(panel1);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    line = client.in.readUTF();
                    message.setText(line);
                    client.out.writeUTF(message.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });




        setVisible(true);


    }
}
