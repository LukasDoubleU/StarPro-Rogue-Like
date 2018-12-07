import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * <p>
 * Diese Klasse handhabt das Scoring.
 * </p>
 *
 * TODO - Es sollen immer alle Scores gespeichert werden - Auf der normalen
 * Score-Seite werden nur die Top X (10) angezeigt - Im GameOverScreen wird der
 * erzielte Score in Einordnung gezeigt
 */
public class ScoringHandler {

    static final int PLAYER_NAME_MAX_LENGTH = 12;

    public static class Table extends Actor {

        public Table() {
            setImage(new GreenfootImage(ScoringHandler.generateTable(), 36, Color.WHITE, Color.BLACK));
        }
    }

    /**
     * <p>
     * Repräsentiert einen einzelnen Score
     * </p>
     */
    public static class Score implements Comparable<Score> {

        String name;
        int value;

        public Score(String name, int value) {
            super();
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }

        public String getValueFilledToMax() {
            return fill(getValue() + "", 6, true);
        }

        @Override
        public int compareTo(Score o) {
            return Integer.valueOf(o.getValue()).compareTo(Integer.valueOf(getValue()));
        }

        @Override
        public String toString() {
            return getName() + SCORE_SEP_CHAR + getValue();
        }

        public String getNameFilledToMax() {
            return fill(getName(), PLAYER_NAME_MAX_LENGTH, false);
        }

    }

    static final String FILE_PATH = System.getProperty("user.home") + File.separator + "MoltenHeartScoring.txt";
    static final char SCORE_SEP_CHAR = ';';

    static File file;

    static String fill(String s, int length, boolean prepend) {
        StringBuilder sb = new StringBuilder(s);
        // Fülle den Namen auf die maximale Länge auf, mit Leerzeichen
        while (sb.length() < length) {
            if (prepend) {
                sb.insert(0, " ");
            } else {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * Speichert die Datei
     */
    public static void saveScore() {
        String name = askForPlayerName();
        if (name == null) {
            // null = Abbruch
            return;
        }
        int points = calcScoreForCurrentGame();
        final List<Score> previousScores = new CopyOnWriteArrayList<Score>(read());
        Score score = new Score(name, points);
        previousScores.add(score);
        // Sortiere, sodass die besten Scores ganz oben stehen
        Collections.sort(previousScores);
        // Behalte nur die 10 besten Scores
        for (int i = 9; i < previousScores.size(); i++) {
            previousScores.remove(i);
        }

        // Schreibe alle Scores in die Datei
        StringBuilder sb = new StringBuilder();
        for (Score s : previousScores) {
            sb.append(s.toString() + "\n");
        }
        try {
            Files.write(getPath(), sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String askForPlayerName() {
        String ask = Greenfoot.ask("What's your name boi?");
        if (ask == null || ask.isEmpty()) {
            return null;
        }
        if (ask.matches("[a-zA-Z0-9]")) {
            JOptionPane.showMessageDialog(null, "Die Eingabe enthält ungültige Einzeichen!");
            return askForPlayerName();
        }
        if (ask.length() > PLAYER_NAME_MAX_LENGTH) {
            JOptionPane.showMessageDialog(null, "Die Eingabe ist zu lang! (Maximal 12 Zeichen)");
            return askForPlayerName();
        }
        return ask;
    }

    /**
     * @return Score für das aktuelle Spiel: Die verbleibende Zeit in Millisekunden
     */
    private static int calcScoreForCurrentGame() {
        TimerDisplay timer = TimerDisplay.get();
        int secs = timer.getSecondsLeft();
        int mins = timer.getMinutesLeft();
        int millis = secs * 1000 + mins * 60 * 1000;
        return millis;
    }

    public static File getFile() {
        if (file == null) {
            file = new File(FILE_PATH);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static Path getPath() {
        return getFile().toPath();
    }

    public static List<Score> read() {
        List<Score> scores = new ArrayList<Score>();

        try {
            List<String> lines = Files.readAllLines(getPath());
            for (String line : lines) {
                String[] split = line.split(";");
                scores.add(new Score(split[0], Integer.valueOf(split[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scores;
    }

    public static String generateTable() {
        List<Score> read = read();
        Collections.sort(read);
        StringBuilder sb = new StringBuilder();
        sb.append("Rangliste\n");
        for (Score score : read) {
            sb.append(score.getNameFilledToMax() + " " + score.getValueFilledToMax() + "\n");
        }
        return sb.toString();
    }

}