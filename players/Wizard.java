package players;

import abilities.Deflect;
import abilities.Drain;
import abilities.Ability;
import common.Constants;
import map.TerrainType;

public final class Wizard extends Player {
    private Drain drain;
    private Deflect deflect;

    Wizard() {
        super();
        setType('W');
        setHp(Constants.INITIAL_HP_WIZARD);
        setBestTerrain(TerrainType.Desert);
        drain = new Drain();
        deflect = new Deflect();
        setHpLevelBonus(Constants.HP_LEVEL_BONUS_WIZARD);
        setTerrainBonus(Constants.WIZARD_LAND_BONUS);
    }

    /**
     * Levels up wizard and his abilities.
     */
    @Override
    void levelUp() {
        setLevel(getLevel() + 1);
        setHp(Constants.INITIAL_HP_WIZARD + getLevel() * getHpLevelBonus());
        drain.levelUp();
        deflect.levelUp();
    }

    @Override
    public void getAttacked(final Ability ability, final int round, final float landModifier) {
        ability.attack(this, round, landModifier);
    }

    @Override
    public void fight(final Player p) {
        p.getAttacked(drain, getRound(), getLandModifier());
        p.getAttacked(deflect, getRound(), getLandModifier());
        if (p.getHp() <= 0) {
            p.die();
        }
    }
}
