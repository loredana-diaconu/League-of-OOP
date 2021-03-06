package main;

import fileio.FileSystem;
import players.Player;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads input data.
 */
final class InputLoader {
    private final String inputPath;
    private final String outputPath;

    InputLoader(final String inputPath, final String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    /**
     * Returns the input.
     */
    Input load() {
        int n;
        // m is needed to read and store an integer, even though it is not used.
        int m;
        int noPlayers;
        int noRounds;
        List<String> playerDetails = new ArrayList<>();
        List<String> map = new ArrayList<>();
        List<String> moves = new ArrayList<>();
        List<Point> position = new ArrayList<>();
        try {
            FileSystem fs = new FileSystem(inputPath, outputPath);
            n = fs.nextInt();
            m = fs.nextInt();
            for (int i = 0; i < n; i++) {
                // Store as ArrayList<String> instead of matrix.
                map.add(fs.nextWord());
            }
            noPlayers = fs.nextInt();
            for (int i = 0; i < noPlayers; i++) {
                // Type
                playerDetails.add(fs.nextWord());
                int x = fs.nextInt();
                int y = fs.nextInt();
                position.add(new Point(x, y));
            }
            noRounds = fs.nextInt();
            for (int i = 0; i < noRounds; i++) {
                moves.add(fs.nextWord());
            }
            fs.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return new Input(playerDetails, map, moves, position);
    }

    /**
     * Writes result to output file.
     */
    void writeResult(final ArrayList<Player> playerArray) throws IOException {
        FileSystem fs = new FileSystem(inputPath, outputPath);
        for (Player p : playerArray) {
            fs.writeCharacter(p.getType());
            fs.writeCharacter(' ');
            if (p.isDead()) {
                fs.writeWord("dead");
                fs.writeNewLine();
            } else {
                fs.writeInt(p.getLevel());
                fs.writeCharacter(' ');
                fs.writeInt(p.getXp());
                fs.writeCharacter(' ');
                fs.writeInt(p.getHp());
                fs.writeCharacter(' ');
                fs.writeInt(p.getPosition().x);
                fs.writeCharacter(' ');
                fs.writeInt(p.getPosition().y);
                fs.writeNewLine();
            }
        }
        fs.close();
    }
}
