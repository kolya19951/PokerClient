package pack;


/**
 * Created by Коля on 26.05.2015.
 */
public class Command {
    protected String command;
    private String cards, suit1, suit2 = null, card_1 = null, card_2, login, field;
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
        return;
    }

    private void youHand() {
        card_1 = getCard(client.ReadUTF());
        card_2 = getCard(client.ReadUTF());
        form.card1_0.setText(card_1);
        form.card2_0.setText(card_2);
        return;
    }

    private void smallBlind() {
        return;
    }

    private void bigBlind() {
        return;
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
        return;
    }

    private void Flop() {
        for (int i = 0; i < 3; i++) {
            form.flops[i].setText(getCard(client.ReadUTF()));
        }
    }

    private static String getCard(String card) {
        String tmp = "";
        if (card.substring(1).equals("0"))
            tmp = "♠";
        if (card.substring(1).equals("1"))
            tmp =  "♣";
        if (card.substring(1).equals("2"))
            tmp = "♥";
        if (card.substring(1).equals("3"))
            tmp = "♦";
        if (card.substring(0, 1).equals("T"))
            return "10" + tmp;
        return card.substring(0,1) + tmp;
    }

    private void Bank() {
        String bank;
        bank = new String(String.valueOf(client.ReadInt()));
        form.bank.setText(bank);
    }

    private void youTurn() {
        return;
    }
}


