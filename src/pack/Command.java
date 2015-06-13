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

    public void Action() throws CommandException {
        if (command.equals("game start info"))
            gameStartInfo();
        else if (command.equals("you hand"))
            youHand();
        else if (command.equals("change"))
            Change();
        else if (command.equals("flop"))
            Flop();
        else if (command.equals("turn"))
            Turn();
        else if (command.equals("river"))
            River();
        else if (command.equals("bank"))
            Bank();
        else if (command.equals("small blind"))
            smallBlind();
        else if (command.equals("big blind"))
            bigBlind();
        else if (command.equals("You turn"))
            youTurn();
        else {
            throw new CommandException("No such command");
        }
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
        return "C:\\Users\\Коля\\IdeaProjects\\PokerClient\\img\\cards\\" + card_1 + ".png";
    }

    private void Bank() {
        String bank;
        bank = new String(String.valueOf(client.ReadInt()));
        form.bank.setText(bank);
    }

    private void youTurn() {
        form.setButtonsEnable();
    }

    private void Turn() {
        form.turn.setIcon(new ImageIcon(getCard()));
    }

    private void River() {
        form.river.setIcon(new ImageIcon(getCard()));
    }
}


