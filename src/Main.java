import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Коля on 18.05.2015.
 */
public class Main {
    public static void main(String[] args) {
        final MainForm form1 = new MainForm();
        form1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form1.setVisible(true);
        System.out.println();
        //FormsController control1 = new FormsController(form1, form1.client);

    }
}
