package map;

import players.Player;

import java.util.ArrayList;
import java.util.List;

public final class Map {
    private Terrain[][] map;
    private int n;
    private int m;
    private static Map instance = null;

    /**
     * Creates the map based on mapList.
     * @param mapList a list of strings that represent
     *                each row in the matrix.
     */
    private Map(final List<String> mapList) {
        TerrainFactory terrainFactory = new TerrainFactory();
        this.n = mapList.size();
        this.m = mapList.get(0).length();
        map = new Terrain[n][m];
        for (int i = 0; i < n; i++) {
            String row = mapList.get(i);
            for (int j = 0; j < m; j++) {
                char type = row.charAt(j);
                Terrain square = terrainFactory.getTerrain(type);
                map[i][j] = square;
            }
        }
    }

    /**
     * Creates the map.
     */
    public static Map create(final List<String> mapList) {
        if (instance == null) {
            instance = new Map(mapList);
        }
        return instance;
    }

    /**
     * Returns an instance of the map.
     */
    public static Map getInstance() {
        return instance;
    }

    /**
     * Adds all players to the map.
     */
    public void populate(final ArrayList<Player> players) {
        for (Player p : players) {
            int x = p.getPosition().x;
            int y = p.getPosition().y;
            map[x][y].addPlayer(p);
        }
    }

    /**
     * Removes player p from spot (x, y).
     */
    public void removePlayer(final Player p, final int x, final int y) {
        map[x][y].removePlayer(p);
    }

    /**
     * Adds player p to spot (x, y).
     */
    public void addPlayer(final Player p, final int x, final int y) {
        map[x][y].addPlayer(p);
    }

    /**
     * Rewards the winner.
     */
    private void reward(final Player winner, final Player loser) {
        int loserLevel = loser.getLevel();
        winner.checkStatus(loserLevel);
    }

    /**
     * Fight between players p1 and p2.
     */
    private void fight(final Player p1, final Player p2) {
        // Reset
        p1.setInflictedDamage(0);
        p2.setInflictedDamage(0);
        p1.setDamageWithoutMods(0);
        p2.setDamageWithoutMods(0);
        if (p1.getType() == 'W') {
            // Wizard has to be the last one to attack
            p2.fight(p1);
            p2.setInflictedDamage(p1.getDamageWithoutMods());
            p1.fight(p2);
        } else {
            p1.fight(p2);
            p1.setInflictedDamage(p2.getDamageWithoutMods());
            p2.fight(p1);
        }
        if (p1.isDead()) {
            reward(p2, p1);
        }
        if (p2.isDead()) {
            reward(p1, p2);
        }
    }

    /**
     * Detects what fights are currently happening on the map.
     */
    public void detectFights() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Stores all the players that are on that exact spot.
                ArrayList<Player> playersHere = map[i][j].getPlayersHere();
                if (playersHere.size() > 1) {
                    // There is a fight happening
                    Player player1 = playersHere.get(0);
                    Player player2 = playersHere.get(1);
                    if (!player1.isDead() && !player2.isDead()) {
                        fight(player1, player2);
                    }
                }
            }
        }
    }
}
