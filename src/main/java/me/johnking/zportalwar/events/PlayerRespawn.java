package me.johnking.zportalwar.events;

import me.johnking.zportalwar.ZPortalWar;
import me.johnking.zportalwar.util.PlayerStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener{
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e){
		e.setRespawnLocation(ZPortalWar.getInstance().getWorldHandler().nextSpawn());
        PlayerStatus status = PlayerStatus.getPlayer(e.getPlayer());
        if(status == null){
            return;
        }
        e.getPlayer().getInventory().setItem(1, null);
        e.getPlayer().getInventory().setItem(2, null);
        status.setRespawn();
	}
}
