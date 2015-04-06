package me.johnking.zportalwar.events;

import me.johnking.zportalwar.ZPortalWar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class PlayerDamage implements Listener{
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e){
		if(!(e.getEntity() instanceof Player))
			return;
		if(!e.getCause().equals(DamageCause.FALL) && ZPortalWar.getInstance().isDamage())
			return;
		e.setCancelled(true);
		Player p = (Player) e.getEntity();
		for(ItemStack is :p.getInventory().getArmorContents()){
			if(is != null)
				is.setDurability((short) 0);
		}
	}
}
