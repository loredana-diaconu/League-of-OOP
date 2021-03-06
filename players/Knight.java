package players;

import abilities.Execute;
import abilities.Slam;
import abilities.Ability;
import common.Constants;
import map.TerrainType;

public final class Knight extends Player {
    private Execute execute;
    private Slam slam;

    Knight() {
        super();
        slam = new Slam();
        execute = new Execute();
        setType('K');
        setHp(Constants.INITIAL_HP_KNIGHT);
        setBestTerrain(TerrainType.Land);
        setHpLevelBonus(Constants.HP_LEVEL_BONUS_KNIGHT);
        setTerrainBonus(Constants.KNIGHT_LAND_BONUS);
    }

    /**
     * Levels up knight and his abilities.
     */
    @Override
    void levelUp() {
        setLevel(getLevel() + 1);
        setHp(Constants.INITIAL_HP_KNIGHT + getLevel() * getHpLevelBonus());
        slam.levelUp();
        execute.levelUp();
    }

    @Override
    public void getAttacked(final Ability ability, final int round, final float landModifier) {
        ability.attack(this, round, landModifier);
    }

    @Override
    public void fight(final Player p) {
        p.getAttacked(execute, getRound(), getLandModifier());
        p.getAttacked(slam, getRound(), getLandModifier());
        if (p.getHp() <= 0) {
            p.die();
        }
    }
}
