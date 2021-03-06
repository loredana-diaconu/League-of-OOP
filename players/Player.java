package players;

import java.awt.Point;
import java.util.ArrayList;

import abilities.Ability;
import common.Constants;
import map.Map;
import map.TerrainType;

public abstract class Player implements Attackable {
    private int level;
    private int hp;
    private int hpLevelBonus;
    private float landModifier;
    private int xp;
    private int levelUpXP;
    private Point position;
    private Character type;
    private TerrainType bestTerrain;
    private int damageWithoutMods;
    private boolean isDead;
    private boolean ableToMove;
    private Point deathPosition;
    private float terrainBonus;
    private ArrayList<Integer> overtimeDamage;
    private int round;
    private int inflictedDamage;
    private int stunnedRounds;

    Player() {
        level = 0;
        xp = 0;
        computeLevelUpXP();
        landModifier = 1;
        damageWithoutMods = 0;
        isDead = false;
        ableToMove = true;
        position = new Point();
        deathPosition = new Point();
        overtimeDamage = new ArrayList<>();
        inflictedDamage = 0;
        stunnedRounds = 0;
        round = 0;
    }

    /**
     * Boosts level and abilities.
     */
    abstract void levelUp();

    /**
     * Computes the XP needed for player to level up.
     */
    private void computeLevelUpXP() {
        levelUpXP = Constants.BASE_XP + level * Constants.XP_LEVEL_RATIO;
    }

    /**
     * Boosts player's XP.
     * @param loserLevel is needed to compute the new XP.
     */
    private void gainXP(final int loserLevel) {
        int baseGain = Constants.BASE_XP_GAIN;
        int levelRatio = Constants.XP_GAIN_LEVEL_RATIO;
        xp += Math.max(0, baseGain - (level - loserLevel) * levelRatio);
    }

    /**
     * Moves player to another location on the map.
     */
    public void move(final Map map, final Character direction) {
        int x = position.x;
        int y = position.y;
        if (direction == 'U') {
            position.x = x - 1;
            map.removePlayer(this, x, y);
            map.addPlayer(this, x - 1, y);
        }
        if (direction == 'D') {
            position.x = x + 1;
            map.removePlayer(this, x, y);
            map.addPlayer(this, x + 1, y);
        }
        if (direction == 'L') {
            position.y = y - 1;
            map.removePlayer(this, x, y);
            map.addPlayer(this, x, y - 1);
        }
        if (direction == 'R') {
            position.y = y + 1;
            map.removePlayer(this, x, y);
            map.addPlayer(this, x, y + 1);
        }
    }

    /**
     * Kills player and gets him off the map.
     */
    public void die() {
        isDead = true;
        Map map = Map.getInstance();
        map.removePlayer(this, position.x, position.y);
        deathPosition.x = position.x;
        deathPosition.y = position.y;
        position.x = -1;
        position.y = -1;
        ableToMove = false;
        hp = 0;
    }

    /**
     * Checks if player should level up and does so if necessary.
     * @param loserLevel needed for computing XP
     */
    public void checkStatus(final int loserLevel) {
        gainXP(loserLevel);
        computeLevelUpXP();
        // The player can level up multiple times at once
        while (xp >= levelUpXP) {
            levelUp();
            computeLevelUpXP();
        }
    }

    public final void disableMovement(final int rounds) {
        ableToMove = false;
        stunnedRounds = rounds;
    }

    @Override
    public abstract void getAttacked(Ability ability, int noRound, float landMod);

    public abstract void fight(Player p);

    public final boolean isAbleToMove() {
        return ableToMove;
    }

    public final void enableMovement() {
        ableToMove = true;
    }

    public final void setPosition(final Point position) {
        this.position = position;
    }

    public final Point getPosition() {
        return position;
    }

    public final Character getType() {
        return type;
    }

    final void setType(final Character type) {
        this.type = type;
    }

    public final float getLandModifier() {
        return landModifier;
    }

    public final void setLandModifier(final float landModifier) {
        this.landModifier = landModifier;
    }

    public final TerrainType getBestTerrain() {
        return bestTerrain;
    }

    final void setBestTerrain(final TerrainType terrainType) {
        this.bestTerrain = terrainType;
    }

    public final int getRound() {
        return round;
    }

    public final void setRound(final int round) {
        this.round = round;
    }

    public final void setDamageWithoutMods(final int damage) {
        damageWithoutMods = damage;
    }

    public final int getDamageWithoutMods() {
        return damageWithoutMods;
    }

    public final int getStunnedRounds() {
        return stunnedRounds;
    }

    public final void setStunnedRounds(final int rounds) {
        stunnedRounds = rounds;
    }

    public final int getHp() {
        return hp;
    }

    public final void setHp(final int hp) {
        this.hp = hp;
    }

    public final int getLevel() {
        return level;
    }

    final void setLevel(final int level) {
        this.level = level;
    }

    final int getHpLevelBonus() {
        return  hpLevelBonus;
    }

    final void setHpLevelBonus(final int bonus) {
        hpLevelBonus = bonus;
    }

    public final ArrayList<Integer> getOvertimeDamage() {
        return overtimeDamage;
    }

    public final void setOvertimeDamage(final ArrayList<Integer> damage) {
        overtimeDamage = damage;
    }

    public final boolean isDead() {
        return isDead;
    }

    public final int getXp() {
        return xp;
    }

    public final float getTerrainBonus() {
        return terrainBonus;
    }

    final void setTerrainBonus(final float bonus) {
        terrainBonus = bonus;
    }

    public final int getInflictedDamage() {
        return inflictedDamage;
    }

    public final void setInflictedDamage(final int inflictedDamage) {
        this.inflictedDamage = inflictedDamage;
    }
}
