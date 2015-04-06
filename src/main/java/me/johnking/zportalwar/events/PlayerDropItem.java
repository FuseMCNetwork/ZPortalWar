package me.johnking.zportalwar.events;

import me.johnking.zportalwar.util.PlayerStatus;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItem implements Listener{
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e){
		if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE))
			return;
		e.setCancelled(true);
		PlayerStatus ps = PlayerStatus.getPlayer(e.getPlayer());
		if(ps != null)
			ps.clearPortals();
	}
	
	@EventHandler
	public void onPlayerClick(InventoryClickEvent e){
		if(e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE))
			return;
		e.setCancelled(true);
	}
}
