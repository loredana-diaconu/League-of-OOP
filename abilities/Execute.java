package abilities;

import common.Constants;
import players.Knight;
import players.Player;
import players.Rogue;
import players.Pyromancer;
import players.Wizard;

public final class Execute implements Ability {
    private int baseDamage;

    public Execute() {
        baseDamage = Constants.EXECUTE_DMG;
    }

    public void levelUp() {
        baseDamage += Constants.EXECUTE_LEVEL_BONUS;
    }

    private int getHPLimit(final Player player) {
        int percentage = Constants.HP_LIMIT;
        percentage = Math.max(Constants.EXECUTE_MAX, percentage + player.getLevel());
        int hp = player.getHp();
        float hpLimit = (float) (percentage / Constants.CENT) * hp;
        return Math.round(hpLimit);
    }

    private void computeDamage(final Player p, final int round,
                               final float landMod, final float raceMod) {
        int damageWithoutMods = p.getDamageWithoutMods();
        float damage = baseDamage * landMod;
        p.setDamageWithoutMods(damageWithoutMods + Math.round(damage));
        damage *= raceMod;
        int hp = p.getHp();
        int hpLimit = getHPLimit(p);
        if (hp <= hpLimit) {
            damage = hp;
        }
        p.setHp(Math.round(hp - damage));
    }

    @Override
    public void attack(final Knight knight, final int round, final float landModifier) {
        computeDamage(knight, round, landModifier, Constants.EXECUTE_KNIGHT_MOD);
    }

    @Override
    public void attack(final Rogue rogue, final int round, final float landModifier) {
        computeDamage(rogue, round, landModifier, Constants.EXECUTE_ROGUE_MOD);
    }

    @Override
    public void attack(final Wizard wizard, final int round, final float landModifier) {
        computeDamage(wizard, round, landModifier, Constants.EXECUTE_WIZARD_MOD);
    }

    @Override
    public void attack(final Pyromancer pyro, final int round, final float landModifier) {
        computeDamage(pyro, round, landModifier, Constants.EXECUTE_PYRO_MOD);
    }
}
