package abilities;

import common.Constants;
import players.Player;
import players.Knight;
import players.Rogue;
import players.Wizard;
import players.Pyromancer;

import java.util.ArrayList;

public final class Paralysis implements Ability {
    private int baseDamage;
    private int rounds;

    public Paralysis() {
        baseDamage = Constants.PARALYSIS_DMG;
        rounds = Constants.NO_PARALYSIS_ROUNDS;
    }

    public void levelUp() {
        baseDamage += Constants.PARALYSIS_LEVEL_BONUS;
    }

    private void computeDamage(final Player p, final int round,
                               final float landMod, final float raceMod) {
        // Empty the overtime damage array.
        ArrayList<Integer> otDamage = p.getOvertimeDamage();
        otDamage.clear();
        p.setOvertimeDamage(otDamage);
        int damageWithoutMods = p.getDamageWithoutMods();
        float damage = baseDamage * landMod;
        p.setDamageWithoutMods(damageWithoutMods + Math.round(damage));
        damage *= raceMod;
        p.setHp(Math.round(p.getHp() - damage));
        if (landMod != 1) {
            rounds *= 2;
        }
        for (int i = 0; i < rounds; i++) {
            // Store overtime damage in an arrayList. Each time it is applied,
            // it is removed from the arrayList.
            otDamage.add(Math.round(damage));
            p.setOvertimeDamage(otDamage);
        }
        p.disableMovement(rounds);
    }

    @Override
    public void attack(final Knight knight, final int round, final float landModifier) {
        computeDamage(knight, round, landModifier, Constants.PARALYSIS_KNIGHT_MOD);
    }

    @Override
    public void attack(final Rogue rogue, final int round, final float landModifier) {
        computeDamage(rogue, round, landModifier, Constants.PARALYSIS_ROGUE_MOD);
    }

    @Override
    public void attack(final Wizard wizard, final int round, final float landModifier) {
        computeDamage(wizard, round, landModifier, Constants.PARALYSIS_WIZARD_MOD);
    }

    @Override
    public void attack(final Pyromancer pyro, final int round, final float landModifier) {
        computeDamage(pyro, round, landModifier, Constants.PARALYSIS_PYRO_MOD);
    }
}
