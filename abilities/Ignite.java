package abilities;

import common.Constants;
import players.Player;
import players.Knight;
import players.Rogue;
import players.Wizard;
import players.Pyromancer;

import java.util.ArrayList;

public final class Ignite implements Ability {
    private int baseDamage;
    private int overtimeDmg;
    private int rounds;

    public Ignite() {
        baseDamage = Constants.IGNITE_BASE_DMG;
        overtimeDmg = Constants.IGNITE_ROUND_DMG;
        rounds = Constants.NO_IGNITE_ROUNDS;
    }

    public void levelUp() {
        baseDamage += Constants.IGNITE_LEVEL_BONUS;
        overtimeDmg += Constants.IGNITE_OVERTIME_BONUS;
    }

    private void computeDamage(final Player p, final int round,
                               final float landMod, final float raceMod) {
        p.setStunnedRounds(0);
        ArrayList<Integer> otDamage = p.getOvertimeDamage();
        otDamage.clear();
        p.setOvertimeDamage(otDamage);
        int damageWithoutMods = p.getDamageWithoutMods();
        float damage = baseDamage * landMod;
        p.setDamageWithoutMods(damageWithoutMods + Math.round(damage));
        damage *= raceMod;
        p.setHp(Math.round(p.getHp() - damage));
        float overtime = overtimeDmg * landMod * raceMod;
        for (int i = 0; i < rounds; i++) {
            otDamage.add(Math.round(overtime));
            p.setOvertimeDamage(otDamage);
        }
    }

    @Override
    public void attack(final Knight knight, final int round, final float landModifier) {
        computeDamage(knight, round, landModifier, Constants.IGNITE_KNIGHT_MOD);
    }

    @Override
    public void attack(final Rogue rogue, final int round, final float landModifier) {
        computeDamage(rogue, round, landModifier, Constants.IGNITE_ROGUE_MOD);
    }

    @Override
    public void attack(final Wizard wizard, final int round, final float landModifier) {
        computeDamage(wizard, round, landModifier, Constants.IGNITE_WIZARD_MOD);
    }

    @Override
    public void attack(final Pyromancer pyro, final int round, final float landModifier) {
        computeDamage(pyro, round, landModifier, Constants.IGNITE_PYRO_MOD);
    }
}
