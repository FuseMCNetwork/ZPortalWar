package me.johnking.zportalwar.events;

import me.johnking.zportalwar.ZPortalWar;
import me.johnking.zportalwar.util.PlayerStatus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EntityByEntityDamage implements Listener{
	
	@EventHandler
	public void onEntityByEntityDamage(EntityDamageByEntityEvent e){
		if(!ZPortalWar.getInstance().isDamage()){
			e.setCancelled(true);
			return;
		}
		if(e.getDamager() instanceof Player){
			Player p = (Player) e.getDamager();
			p.getItemInHand().setDurability((short) 0);
            PlayerStatus ps = PlayerStatus.getPlayer(p);
            if(ps != null){
                if(!ps.isAttackable()){
                    e.setCancelled(true);
                    //player noch in der respawnphase
                }
            }
		}
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			for(ItemStack is: p.getInventory().getArmorContents()){
				if(is != null) {
                    is.setDurability((short) 0);
                }
			}
            PlayerStatus ps = PlayerStatus.getPlayer(p);
            if(ps != null){
                if(!ps.isAttackable()){
                    e.setCancelled(true);
                    //player noch in der respawnphase
                }
            }
		}
	}
}
