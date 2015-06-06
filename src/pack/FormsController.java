package pack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Коля on 21.05.2015.
 */
public class FormsController {
    private JFrame form;

    FormsController(final PokerForm frame, final Client client) {
        form = frame;
        frame.checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.SendInt(0);
            }
        });
        frame.foldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.SendInt(-1);
            }
        });
        frame.raiseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.SendInt(Integer.parseInt(frame.raise.getText()));
            }
        });
    }
}
