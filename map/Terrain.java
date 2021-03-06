package map;

import players.Player;

import java.util.ArrayList;

/**
 * Describes a spot on the map.
 */
class Terrain {
    private TerrainType type;
    private ArrayList<Player> onThisSurface;

    Terrain(final TerrainType type) {
        this.type = type;
        onThisSurface = new ArrayList<>();
    }

    /**
     * Adds player p to this spot.
     * Checks if p is on its most favourable terrain.
     */
    final void addPlayer(final Player player) {
        onThisSurface.add(player);
        if (type.equals(player.getBestTerrain())) {
            player.setLandModifier(player.getTerrainBonus());
        } else {
            player.setLandModifier(1);
        }
    }

    /**
     * Removes player p from this spot.
     */
    final void removePlayer(final Player player) {
        onThisSurface.remove(player);
    }

    /**
     * Returns an arraylist of players that are currently on this spot.
     */
    final ArrayList<Player> getPlayersHere() {
        return onThisSurface;
    }
}
