package org.saabye_pedersen.mc;

import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameHandler {

    private final Map<Player, Integer> playerScores = new HashMap<>();
    private final Set<Sheep> sheep = new HashSet<>();
    private final SheepSpawner sheepSpawner;

    public GameHandler(SheepSpawner sheepSpawner) {
        this.sheepSpawner = sheepSpawner;
    }

    public void setPlayerScore(Player player, int score) {
        playerScores.put(player, score);
    }

    public int getPlayerScore(Player player) {
        return playerScores.getOrDefault(player, 0);
    }

    public void startGame(Player actor, int sheepcount) {
        if (!playerScores.isEmpty() & playerScores.values().stream().anyMatch(score -> score > 0)) {
            actor.sendMessage("Game already running, please reset it");
        } else {
            for (int i = 0; i < sheepcount; i++) {
                Sheep sheep = sheepSpawner.spawnSheep(actor.getWorld(), actor.getLocation());
                this.sheep.add(sheep);
            }
        }
    }

    public void resetGame(Player actor) {
        playerScores.clear();
        sheep.clear();
        actor.sendMessage("Game reset");
    }


    public void addPlayers(Player actor, Player player) {
        playerScores.put(player, 0);
        actor.sendMessage("Player added");
    }


    private void sheepKilled(Player actor, Sheep sheep) {
        if (this.sheep.remove(sheep)) {
            int score = playerScores.get(actor);
            playerScores.put(actor, score + 1);
            actor.sendMessage("You now have " + playerScores.get(actor) + " points");

            if(this.sheep.isEmpty()){
                sendFinalScore();
                playerScores.clear();
                this.sheep.clear();
            }else{
                sendKillMessage(actor, playerScores.get(actor));
            }

        } else {
            if(!this.sheep.isEmpty())
                actor.sendMessage("Sorry, the sheep you killed was not one of ours");
        }
    }

    private void sendKillMessage(Player actor, Integer integer) {
        broadCastMessage(actor.getName() + " now has " + integer + " points");
    }

    private void sendFinalScore() {
        StringBuilder sb = new StringBuilder();
        sb.append("The final score is:\n");
        for (Map.Entry<Player, Integer> entry : playerScores.entrySet()) {
            sb.append(entry.getKey().getName())
                    .append(" has ")
                    .append(entry.getValue())
                    .append(" points\n");
        }
        broadCastMessage(sb.toString());
    }

    public void sheepHit(Player actor, Sheep sheep) {
        double finalDamage = sheep.getHealth() - sheep.getLastDamage();

        if (finalDamage <= 0) {
            sheepKilled(actor, sheep);
        } else {
            if (this.sheep.contains(sheep)) {
                actor.sendMessage("You hit a sheepdog sheep, continue to kill it to get a point");
            } else {
                actor.sendMessage("That is not a sheepdog sheep");
            }
        }
    }

    public void score(Player actor) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Player, Integer> entry : playerScores.entrySet()) {
            sb.append(entry.getKey().getName())
                    .append(" has ")
                    .append(entry.getValue())
                    .append(" points\n");
        }
        String message = sb.toString();
        if (message.isEmpty()) {
            message = "No scores yet";
        }

        broadCastMessage(message);
    }

    private void broadCastMessage(String message){
        for (Player player : playerScores.keySet()) {
            player.sendMessage(message);
        }
    }
}
