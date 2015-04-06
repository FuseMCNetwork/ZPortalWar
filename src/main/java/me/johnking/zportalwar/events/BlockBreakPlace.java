package me.johnking.zportalwar.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockBreakPlace implements Listener{
	
	@EventHandler
	@SuppressWarnings("deprecation")
	public void onBlockBreak(PlayerInteractEvent e){
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE && e.getPlayer().isOp()){
            return;
        }
        if(!(e.getPlayer().getItemInHand().getType() == Material.STONE_SWORD && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK))) {
            e.setCancelled(true);
            e.getPlayer().updateInventory();
        }
	}
}
