package pack;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Коля on 26.05.2015.
 */
public class Command {
    protected String command;
    private String card_1 = null, card_2, login, field;
    private int position, bankroll;
    private Client client;
    private PokerForm form;

    Command(String command, Client client, PokerForm form) {
        this.command = command;
        this.client = client;
        this.form = form;
    }


    public void Action() {
        if (command.equals("game start info"))
            gameStartInfo();
        if (command.equals("you hand"))
            youHand();
        if (command.equals("change"))
            Change();
        if (command.equals("flop"))
            Flop();
        if (command.equals("bank"))
            Bank();
        if (command.equals("small blind"))
            smallBlind();
        if (command.equals("big blind"))
            bigBlind();
        if (command.equals("You turn"))
            youTurn();
        form.WaitCommand(client);
    }

    private void gameStartInfo() {
        for (int i = 0; i < 6; i++) {
            position = client.ReadInt();
            login = client.ReadUTF();
            bankroll = client.ReadInt();
            form.names[position].setText(login);
            form.bankrolls[position].setText(Integer.toString(bankroll));
        }
    }

    private void youHand() {
        card_1 = client.ReadUTF();
        card_2 = client.ReadUTF();
    }

    private void smallBlind() {
    }

    private void bigBlind() {
    }

    private void Change() {
        position = client.ReadInt();
        field = client.ReadUTF();
        if (field.equals("bet"))
            form.bets[position].setText(String.valueOf(client.ReadInt()));
        if (field.equals("bankroll"))
            form.bankrolls[position].setText(String.valueOf(client.ReadInt()));
        if (field.equals("bank"))
            form.bank.setText(String.valueOf(client.ReadInt()));
        if (field.equals("hand")) {
            form.cards[position * 2].setIcon(new ImageIcon(getCard()));
            form.cards[position * 2 + 1].setIcon(new ImageIcon(getCard()));
        }

    }

    private void Flop() {
        for (int i = 0; i < 3; i++) {
            form.flops[i].setIcon(new ImageIcon(getCard()));
        }
    }

    private String getCard() {
        card_1 = client.ReadUTF();
        if (card_1.substring(0, 1).equals("T"))
            return "C:\\Users\\Коля\\IdeaProjects\\PokerClient\\img\\cards\\" + "10" + card_1.substring(1) + ".png";
        else
            return "C:\\Users\\Коля\\IdeaProjects\\PokerClient\\img\\cards\\" + card_1 + ".png";
    }

    private void Bank() {
        String bank;
        bank = new String(String.valueOf(client.ReadInt()));
        form.bank.setText(bank);
    }

    private void youTurn() {
    }
}


