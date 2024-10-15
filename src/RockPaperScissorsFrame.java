import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    private JTextField playerWinsField;
    private JTextField computerWinsField;
    private JTextField tiesField;
    private JTextArea resultsArea;
    private int playerWins = 0, computerWins = 0, ties = 0;
    private Random random = new Random();
    private JButton rockButton;
    private JButton paperButton;
    private JButton scissorsButton;
    private JButton quitButton;
    //private ImageIcon rockIcon = new ImageIcon("src/rock.png");
    //private ImageIcon paperIcon = new ImageIcon("src/paper.png");
    //private ImageIcon scissorsIcon = new ImageIcon("src/scissors.png");


    public RockPaperScissorsFrame() {

        //rockButton = new JButton(rockIcon);
        //paperButton = new JButton(paperIcon);
        //scissorsButton = new JButton(scissorsIcon);

        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        quitButton = new JButton("Quit");
        playerWinsField = new JTextField("0", 5);
        computerWinsField = new JTextField("0", 5);
        tiesField = new JTextField("0", 5);
        resultsArea = new JTextArea(10, 30);
    }

    public void guiSettings() {
        guiLayout();
        guiActionListeners();
        guiFrame();
    }

    private void guiLayout() {
        setLayout(new BorderLayout(10, 10));
        add(buttonPanel(), BorderLayout.NORTH);
        add(statsPanel(), BorderLayout.CENTER);
        add(resultsPanel(), BorderLayout.SOUTH);
    }

    private JPanel buttonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.add(rockButton);
        panel.add(paperButton);
        panel.add(scissorsButton);
        panel.add(quitButton);
        return panel;
    }

    private JPanel statsPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Player Wins:"));
        panel.add(playerWinsField);
        panel.add(new JLabel("Computer Wins:"));
        panel.add(computerWinsField);
        panel.add(new JLabel("Ties:"));
        panel.add(tiesField);
        return panel;
    }

    private JScrollPane resultsPanel() {
        return new JScrollPane(resultsArea);
    }

    private void guiActionListeners() {
        ActionListener actionListener = new MyActionListener();
        rockButton.addActionListener(actionListener);
        paperButton.addActionListener(actionListener);
        scissorsButton.addActionListener(actionListener);
        quitButton.addActionListener(actionListener);
    }

    private void guiFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == quitButton) {
                System.exit(0);
            }

            String playerChoice = ((JButton) event.getSource()).getText();
            String computerChoice = computerChoice();
            String result = winner(playerChoice, computerChoice);

            gameStats();
            gameResults(playerChoice, computerChoice, result);
        }
    }

    private String computerChoice() {
        String[] choices = {"Rock", "Paper", "Scissors"};
        return choices[random.nextInt(choices.length)];
    }
    
    private String winner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            ties++;
            return "Tie!";
        } else if ((playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))) {
            playerWins++;
            return "Player Wins!";
        } else {
            computerWins++;
            return "Computer Wins!";
        }
    }
        private void gameStats() {
            playerWinsField.setText(String.valueOf(playerWins));
            computerWinsField.setText(String.valueOf(computerWins));
            tiesField.setText(String.valueOf(ties));
        }
        private void gameResults(String playerChoice, String computerChoice, String result) {
            resultsArea.append(String.format("Player: %s, Computer: %s - %s%n",
                    playerChoice, computerChoice, result));
        }
    }

