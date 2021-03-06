package abilities;

import common.Constants;
import players.Player;
import players.Knight;
import players.Rogue;
import players.Wizard;
import players.Pyromancer;

public final class Drain implements Ability {
    private float baseDamage;

    public Drain() {
        baseDamage = Constants.DRAIN_DMG;
    }

    public void levelUp() {
        baseDamage += Constants.DRAIN_LEVEL_BONUS;
    }

    private void computeDamage(final Player p, final int round, final float landMod,
                               final float raceMod, final int baseHp) {
        float percentage = baseDamage * landMod * raceMod;
        float damage = percentage * Math.min(Constants.DRAIN_PERCENTAGE * baseHp, p.getHp());
        p.setHp(Math.round(p.getHp() - damage));
    }

    @Override
    public void attack(final Knight knight, final int round, final float landModifier) {
        float raceMod = Constants.DRAIN_KNIGHT_MOD;
        computeDamage(knight, round, landModifier, raceMod, Constants.INITIAL_HP_KNIGHT);
    }

    @Override
    public void attack(final Rogue rogue, final int round, final float landModifier) {
        float raceMod = Constants.DRAIN_ROGUE_MOD;
        computeDamage(rogue, round, landModifier, raceMod, Constants.INITIAL_HP_ROGUE);
    }

    @Override
    public void attack(final Wizard wizard, final int round, final float landModifier) {
        float raceMod = Constants.DRAIN_WIZARD_MOD;
        computeDamage(wizard, round, landModifier, raceMod, Constants.INITIAL_HP_WIZARD);
    }

    @Override
    public void attack(final Pyromancer pyro, final int round, final float landModifier) {
        float raceMod = Constants.DRAIN_PYRO_MOD;
        computeDamage(pyro, round, landModifier, raceMod, Constants.INITIAL_HP_PYRO);
    }
}
