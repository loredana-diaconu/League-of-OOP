package players;

import abilities.Ability;

public interface Attackable {
    void getAttacked(Ability ability, int round, float landModifier);
}
