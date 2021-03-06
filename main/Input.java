package main;

import players.Player;
import players.PlayerFactory;
import map.Map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores input read from file under a friendlier form.
 */
public final class Input {
    private List<String> playerDetails;
    private List<String> map;
    private List<String> moves;
    private List<Point> position;
    private int rounds;

    Input(final List<String> players, final List<String> map,
          final List<String> moves, final List<Point> pos) {
        playerDetails = players;
        this.map = map;
        this.moves = moves;
        this.position = pos;
        rounds = moves.size();
    }

    int getRounds() {
        return rounds;
    }

    /**
     * Returns all the players with their corresponding information.
     */
    ArrayList<Player> getPlayerArray() {
        ArrayList<Player> playerArray = new ArrayList<>();
        PlayerFactory playerFactory = new PlayerFactory();
        for (int i = 0; i < playerDetails.size(); i++) {
            String type = playerDetails.get(i);
            Point pos = position.get(i);
            // Crates players based on a character that describes their type.
            Player p = playerFactory.getPlayer(type);
            assert  p != null;
            p.setPosition(pos);
            playerArray.add(p);
        }
        return playerArray;
    }

    /**
     * Returns the moves made by all the players during a specific round.
     */
    ArrayList<Character> getMovesThisRound(final int round) {
        String movesString = moves.get(round);
        ArrayList<Character> movesArray = new ArrayList<>();
        for (int i = 0; i < movesString.length(); i++) {
            Character c = movesString.charAt(i);
            movesArray.add(c);
        }
        return movesArray;
    }

    public Map getMap() {
        return Map.create(map);
    }
}
