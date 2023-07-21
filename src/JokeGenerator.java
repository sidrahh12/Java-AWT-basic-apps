import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class JokeGenerator extends Frame {
    private final TextArea jokeTextArea;

    private final String[] jokes = {
            "Why don't scientists trust atoms?\n Because they make up everything!",
            "Why don't skeletons fight each other?\n They don't have the guts!",
            "Why don't eggs tell jokes?\n Because they might crack up!",
            "Why did the scarecrow win an award?\n Because he was outstanding in his field!",
            "Why did the bicycle fall over?\n It was two-tired!",
            "Why don't skeletons fight each other?\n They don't have the guts!",
            "Why don't oysters donate to charity?\n Because they are shellfish!",
            "Why don't some couples go to the gym?\n Because some relationships don't work out!",
            "Why did the tomato turn red?\n Because it saw the salad dressing!",
            "Why did the golfer bring two pairs of pants?\n In case he got a hole in one!",
            "Why don't ants get sick?\n Because they have little anty-bodies!"
    };

    public JokeGenerator() {
        setTitle("Joke Generator");
        setSize(300, 200);
        setLayout(new BorderLayout());

        Button generateButton = new Button("Let's Laugh!");
        jokeTextArea = new TextArea();
        jokeTextArea.setEditable(false);

        add(generateButton, BorderLayout.NORTH);
        add(jokeTextArea, BorderLayout.CENTER);

        generateButton.addActionListener(e -> {
            String joke = getRandomJoke();
            jokeTextArea.setText(joke);
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private String getRandomJoke() {
        Random random = new Random();
        int index = random.nextInt(jokes.length);
        return jokes[index];
    }

    public static void main(String[] args) {
        new JokeGenerator();
    }
}