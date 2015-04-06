package me.johnking.zportalwar.events;

import me.johnking.zportalwar.ZPortalWar;
import me.johnking.zportalwar.util.Helper;
import me.johnking.zportalwar.util.PlayerStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener{

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e){
		Player p = e.getPlayer();
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		p.setExp(0);
		p.setLevel(0);
		p.setHealth(20D);
		
		PlayerStatus ps = PlayerStatus.getPlayer(p);
		if(ps != null)
			ps.clearPortals();
		
		if(Bukkit.getOnlinePlayers().length > 2)
			return;
		Bukkit.broadcastMessage(ChatColor.DARK_RED + "Zu wenig Spieler! Server restartet!");
		ZPortalWar.getInstance().setDamage(false);
		Helper.restartServer();
	}
}
