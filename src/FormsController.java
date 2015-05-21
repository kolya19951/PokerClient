import sun.misc.FormattedFloatingDecimal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.Normalizer;

/**
 * Created by Коля on 21.05.2015.
 */
public class FormsController {
    private JFrame form;

    FormsController(final MainForm frame, final Client client) {
        form = frame;
        /*frame.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.setVisible(false);
                try {

                    client.out.writeUTF(frame.message.getText());

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                frame.setVisible(false);
                final PokerForm form2 = new PokerForm(client);
                form2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                form2.setSize(500, 500);
                form2.setVisible(true);
                FormsController control2 = new FormsController(form2, client);
            }
        });*/
    }

    FormsController(final PokerForm frame, final Client client) {
        form = frame;
        frame.checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.out.writeInt(0);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.foldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.out.writeInt(-1);
                    System.out.println(-1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.raiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.out.writeInt(Integer.parseInt(frame.raise.getText()));
                    System.out.println(frame.raise.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
