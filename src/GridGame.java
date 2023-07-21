import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GridGame implements ActionListener {
    private final int gridSize = 30;
    private final JButton[] buttons = new JButton[gridSize];
    private Color currentPlayerColor = Color.RED;

    public GridGame() {
        JFrame frame = new JFrame("Grid Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(6, 5));

        String rules = "Game Rules:\n\n" +
                "1. Each player takes turns to click on an empty button.\n" +
                "2. The button will be colored in the player's color (red or blue).\n" +
                "3. The player who creates the most continuous structure of four or more buttons wins.\n" +
                "4. The structure can be horizontal or vertical.\n" +
                "5. Diagonals don't count.\n" +
                "Enjoy the game!";

        JOptionPane.showMessageDialog(null, rules, "Game Rules", JOptionPane.INFORMATION_MESSAGE);

        for (int i = 0; i < gridSize; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GridGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        if (buttonClicked.getBackground() == Color.RED || buttonClicked.getBackground() == Color.BLUE) {
            return;
        }

        buttonClicked.setBackground(currentPlayerColor);
        if (checkForWinner()) {
            String winner = currentPlayerColor == Color.RED ? "Red" : "Blue";
            JOptionPane.showMessageDialog(null, winner + " wins!");
            System.exit(0);
        }

        currentPlayerColor = currentPlayerColor == Color.RED ? Color.BLUE : Color.RED;
    }

    private boolean checkForWinner() {
        int[][] directions = { { 1, 0 }, { 0, 1 }, { 1, 1 }, { 1, -1 } };
        for (int i = 0; i < gridSize; i++) {
            for (int[] dir : directions) {
                if (checkContinuousStructure(i, dir[0], dir[1], 2 , currentPlayerColor)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkContinuousStructure(int index, int dx, int dy, int count, Color color) {
        int row = index / 7;
        int col = index % 7;
        int newIndex = (row + dx) * 7 + col + dy;

        if (newIndex >= 0 && newIndex < gridSize && buttons[newIndex].getBackground() == color) {
            return checkContinuousStructure(newIndex, dx, dy, count + 2, color);
        }

        return count >= 7;
    }
}