package players;

import abilities.Backstab;
import abilities.Paralysis;
import abilities.Ability;
import common.Constants;
import map.TerrainType;

public final class Rogue extends Player {
    private Backstab backstab;
    private Paralysis paralysis;

    Rogue() {
        super();
        setType('R');
        setHp(Constants.INITIAL_HP_ROGUE);
        setBestTerrain(TerrainType.Woods);
        paralysis = new Paralysis();
        backstab = new Backstab();
        setHpLevelBonus(Constants.HP_LEVEL_BONUS_ROGUE);
        setTerrainBonus(Constants.ROGUE_LAND_BONUS);
    }

    /**
     * Levels up rogue and his abilities.
     */
    @Override
    void levelUp() {
        setLevel(getLevel() + 1);
        setHp(Constants.INITIAL_HP_ROGUE + getLevel() * getHpLevelBonus());
        backstab.levelUp();
        paralysis.levelUp();
    }

    @Override
    public void getAttacked(final Ability ability, final int round, final float landModifier) {
        ability.attack(this, round, landModifier);
    }

    @Override
    public void fight(final Player p) {
        p.getAttacked(backstab, getRound(), getLandModifier());
        p.getAttacked(paralysis, getRound(), getLandModifier());
        setRound(getRound() + 1);
        if (p.getHp() <= 0) {
            p.die();
        }
    }
}
