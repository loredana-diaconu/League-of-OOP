package players;

public final class PlayerFactory {
    public Player getPlayer(final String type) {
        if (type.equals("K")) {
            return new Knight();
        }
        if (type.equals("P")) {
            return new Pyromancer();
        }
        if (type.equals("R")) {
            return new Rogue();
        }
        if (type.equals("W")) {
            return new Wizard();
        }
        return null;
    }
}
