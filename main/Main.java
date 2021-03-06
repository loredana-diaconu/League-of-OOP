package main;

import map.Map;
import players.Player;

import java.io.IOException;
import java.util.ArrayList;

public final class Main {
    private Main() { }

    public static void main(final String[] args) throws IOException {
        InputLoader inputLoader = new InputLoader(args[0], args[1]);
        Input input = inputLoader.load();
        // Gets the input and populates the map.
        Map map = input.getMap();
        ArrayList<Player> playerArray = input.getPlayerArray();
        int rounds = input.getRounds();
        map.populate(playerArray);
        // Sequence of events.
        for (int i = 0; i < rounds; i++) {
            ArrayList<Character> moves = input.getMovesThisRound(i);
            for (int j = 0; j < moves.size(); j++) {
                Player p = playerArray.get(j);
                // Check if player p has to suffer from over time damage.
                if (!p.isDead() && p.getOvertimeDamage().size() > 0) {
                    ArrayList<Integer> otDamage = p.getOvertimeDamage();
                    int damage = otDamage.get(0);
                    otDamage.remove(0);
                    p.setOvertimeDamage(otDamage);
                    int hp = p.getHp();
                    p.setHp(hp - damage);
                    if (p.getHp() <= 0) {
                        p.die();
                    }
                }
                // Check where player is headed.
                Character direction = moves.get(j);
                // Check if player is stunned or able to move.
                if (p.getStunnedRounds() <= 0 && !p.isDead()) {
                    p.enableMovement();
                }
                if (p.isAbleToMove()) {
                    p.move(map, direction);
                } else {
                    if (!p.isDead()) {
                        // If stunned, he will have to remain still one less round.
                        p.setStunnedRounds(p.getStunnedRounds() - 1);
                    }
                }
            }
            //See what fights are going on.
            map.detectFights();
        }
        // Write game result.
        inputLoader.writeResult(playerArray);
    }
}
