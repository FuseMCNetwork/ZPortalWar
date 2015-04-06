package me.johnking.zportalwar.events;

import me.johnking.zportalwar.ZPortalWar;
import me.johnking.zportalwar.util.Helper;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener{

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e){
		Player killer = null;
        e.setDeathMessage(null);
		e.setKeepLevel(true);
		if(e.getEntity().getKiller() instanceof Player){
            killer = e.getEntity().getKiller();
        }
		if(killer != null) {
            killer.setLevel(ZPortalWar.getInstance().getScoreboard().addKill(killer));
            //killer.sendMessage("\u00A78\u00A7l[\u00A72\u2694\u00A78\u00A7l] \u00A7aDu hast \u00A73"+ e.getEntity().getDisplayName() + " \u00A7aget\u00F6tet!");
            //e.getEntity().sendMessage("\u00A78\u00A7l[\u00A74\u2694\u00A78\u00A7l] \u00A7aDu wurdest von \u00A73" + killer.getDisplayName() + " \u00A7aget\u00F6tet!");
            e.getEntity().playSound(e.getEntity().getEyeLocation(), Sound.BLAZE_DEATH, 1, 1);
            killer.playSound(killer.getEyeLocation(), Sound.ORB_PICKUP, 1, 0);
        } else {
            return;
        }
		if(!(killer.getLevel() == 20)) {
            return;
        }
        Helper.winGame(killer);
        /*
		Bukkit.broadcastMessage("\u00A78\u00A7l[\u00A7b\u2756\u00A78\u00A7l] \u00A73" + killer.getDisplayName() + " \u00A7dhat das Spiel gewonnen!");
		ZPortalWar.getInstance().setDamage(false);
        Helper.playFireworks(killer);
		Helper.restartServer();
		*/
	}
}
