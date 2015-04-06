package me.johnking.zportalwar.util;

import me.johnking.zportalwar.ZPortalWar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Marco on 10.08.2014.
 */
public class GameTimer implements Runnable {

    private final int schedulerId;
    private int minutes;

    public GameTimer(int minutes){
        this.minutes = minutes;
        schedulerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(ZPortalWar.getInstance(), this, 0, 1200);
    }

    @Override
    public void run(){
        this.minutes--;
        if(this.minutes <= 5 && this.minutes != 4 && this.minutes != 0){
            Bukkit.broadcastMessage("\u00a78\u00a7l[\u00a7b\u2756\u00a78\u00a7l] \u00A7dSpiel endet in \u00A76" + minutes + " \u00A7dMinuten!");
        }
        if(this.minutes == 0){
            Player winner = null;
            int kills = 0;
            for(Player player: Bukkit.getOnlinePlayers()){
                int current = ZPortalWar.getInstance().getScoreboard().getKills(player);
                if(current > kills || kills == 0){
                    winner = player;
                    kills = current;
                }
            }
            Bukkit.getScheduler().cancelTask(schedulerId);

            Helper.winGame(winner);
            /*
            ZPortalWar.getInstance().setDamage(false);
            Bukkit.broadcastMessage("\u00A78\u00A7l[\u00A7b\u2756\u00A78\u00A7l] \u00A73" + winner.getDisplayName() + " \u00A7dhat das Spiel gewonnen!");
            Helper.playFireworks(winner);
            Helper.restartServer();
            */
        }
    }
}
