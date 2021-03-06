package abilities;

import players.Knight;
import players.Rogue;
import players.Wizard;
import players.Pyromancer;

public interface Ability {
    void attack(Knight knight, int round, float landModifier);
    void attack(Rogue rogue, int round, float landModifier);
    void attack(Wizard wizard, int round, float landModifier);
    void attack(Pyromancer pyro, int round, float landModifier);
}
