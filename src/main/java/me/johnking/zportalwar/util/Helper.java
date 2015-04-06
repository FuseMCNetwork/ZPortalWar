package me.johnking.zportalwar.util;

import me.johnking.zportalwar.ZPortalWar;
import net.fusemc.zcore.util.WinHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Helper {

    public static void winGame(Player winner){
        ZPortalWar.getInstance().setDamage(false);
        WinHelper.win(winner, false);
    }

    public static void restartServer(){
        Bukkit.getScheduler().scheduleSyncDelayedTask(ZPortalWar.getInstance(), new Runnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()){
                    player.kickPlayer("lobby");
                }
                Bukkit.shutdown();
            }
        }, 200);
    }
}
