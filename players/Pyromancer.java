package players;

import abilities.Fireblast;
import abilities.Ignite;
import abilities.Ability;
import common.Constants;
import map.TerrainType;

public final class Pyromancer extends Player {
    private Fireblast fireblast;
    private Ignite ignite;

    Pyromancer() {
        super();
        setType('P');
        setHp(Constants.INITIAL_HP_PYRO);
        setBestTerrain(TerrainType.Volcano);
        ignite = new Ignite();
        fireblast = new Fireblast();
        setHpLevelBonus(Constants.HP_LEVEL_BONUS_PYRO);
        setTerrainBonus(Constants.PYRO_LAND_BONUS);
    }

    /**
     * Levels up Pyromancer and his abilities.
     */
    @Override
    void levelUp() {
        setLevel(getLevel() + 1);
        setHp(Constants.INITIAL_HP_PYRO + getLevel() * getHpLevelBonus());
        fireblast.levelUp();
        ignite.levelUp();
    }

    @Override
    public void getAttacked(final Ability ability, final int round, final float landModifier) {
        ability.attack(this, round, landModifier);
    }

    @Override
    public void fight(final Player p) {
        p.getAttacked(fireblast, getRound(), getLandModifier());
        p.getAttacked(ignite, getRound(), getLandModifier());
        if (p.getHp() <= 0) {
            p.die();
        }
    }
}
