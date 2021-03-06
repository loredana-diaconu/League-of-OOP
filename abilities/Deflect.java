package abilities;

import common.Constants;
import players.Player;
import players.Knight;
import players.Rogue;
import players.Wizard;
import players.Pyromancer;

public final class Deflect implements Ability {
    private float baseDamage;

    public Deflect() {
        baseDamage = Constants.DEFLECT_DMG;
    }

    public void levelUp() {
        baseDamage = Math.min(baseDamage + Constants.DEFLECT_LEVEL_BONUS, Constants.DEFLECT_MAX);
    }

    private void computeDamage(final Player p, final int round,
                               final float landMod, final float raceMod) {
        float percentage =  baseDamage * landMod * raceMod;
        float damage = percentage * p.getInflictedDamage();
        p.setHp(Math.round(p.getHp() - Math.round(damage)));
    }

    @Override
    public void attack(final Knight knight, final int round, final float landModifier) {
        computeDamage(knight, round, landModifier, Constants.DEFLECT_KNIGHT_MOD);
    }

    @Override
    public void attack(final Rogue rogue, final int round, final float landModifier) {
        computeDamage(rogue, round, landModifier, Constants.DEFLECT_ROGUE_MOD);
    }

    @Override
    public void attack(final Wizard wizard, final int round, final float landModifier) {
    }

    @Override
    public void attack(final Pyromancer pyro, final int round, final float landModifier) {
        computeDamage(pyro, round, landModifier, Constants.DEFLECT_PYRO_MOD);
    }
}
