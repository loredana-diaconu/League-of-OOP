package abilities;

import common.Constants;
import players.Player;
import players.Knight;
import players.Rogue;
import players.Wizard;
import players.Pyromancer;

public final class Fireblast implements Ability {
    private int baseDamage;

    public Fireblast() {
        baseDamage = Constants.FIREBLAST_DMG;
    }

    public void levelUp() {
        baseDamage += Constants.FIREBLAST_LEVEL_BONUS;
    }

    private void computeDamage(final Player p, final int round,
                               final float landMod, final float raceMod) {
        int damageWithoutMods = p.getDamageWithoutMods();
        float damage = baseDamage * landMod;
        p.setDamageWithoutMods(damageWithoutMods + Math.round(damage));
        damage *= raceMod;
        p.setHp(Math.round(p.getHp() - damage));
    }

    @Override
    public void attack(final Knight knight, final int round, final float landModifier) {
        computeDamage(knight, round, landModifier, Constants.FIREBLAST_KNIGHT_MOD);
    }

    @Override
    public void attack(final Rogue rogue, final int round, final float landModifier) {
        computeDamage(rogue, round, landModifier, Constants.FIREBLAST_ROGUE_MOD);
    }

    @Override
    public void attack(final Wizard wizard, final int round, final float landModifier) {
        computeDamage(wizard, round, landModifier, Constants.FIREBLAST_WIZARD_MOD);
    }

    @Override
    public void attack(final Pyromancer pyro, final int round, final float landModifier) {
        computeDamage(pyro, round, landModifier, Constants.FIREBLAST_PYRO_MOD);
    }
}
