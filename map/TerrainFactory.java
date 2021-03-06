package map;

final class TerrainFactory {
    Terrain getTerrain(final char type) {
        if (type == 'L') {
            return new Terrain(TerrainType.Land);
        }
        if (type == 'V') {
            return new Terrain(TerrainType.Volcano);
        }
        if (type == 'W') {
            return new Terrain(TerrainType.Woods);
        }
        if (type == 'D') {
            return new Terrain(TerrainType.Desert);
        }
        return null;
    }
}
