package me.johnking.zportalwar.sniper;

import me.johnking.jlib.protocol.util.EntityUtilities;
import me.johnking.zportalwar.ZPortalWar;
import me.johnking.zportalwar.util.PlayerStatus;
import net.fusemc.zcore.projectileAPI.HitType;
import net.fusemc.zcore.util.NamedItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShotSniper implements Listener{

    @EventHandler
    public void onProjectileHit(SniperProjectileEvent e){
        if(!ZPortalWar.getInstance().isDamage()){
            return;
        }

        if(e.getHitType() == HitType.ENTITY) {
            if(e.getHitEntity() instanceof Player){
                if(e.getHitEntity().getHealth() == 0){
                    return;
                }
                PlayerStatus ps = PlayerStatus.getPlayer((Player) e.getHitEntity());
                if(ps != null){
                    if(!ps.isAttackable()){
                        return;
                        //player noch in der respawnphase
                    }
                }
                EntityUtilities.setKiller(e.getHitEntity(), e.getProjectile().getShooter(), 2);
                e.getHitEntity().setHealth(0.0D);
            }
        }
    }

	@EventHandler
	public void onClick(PlayerInteractEvent e){
		if(e.getAction().equals(Action.PHYSICAL))
			return;
		Player p = e.getPlayer();
		if(!p.getItemInHand().getType().equals(Material.IRON_HOE))
			return;

        if(!ZPortalWar.getInstance().isDamage()){
            return;
        }

        PlayerStatus ps = PlayerStatus.getPlayer(p);
        if(ps != null){
            if(!ps.isAttackable()){
                return;
                //player noch in der respawnphase
            }
        }

        p.getWorld().playSound(p.getEyeLocation(), Sound.ENDERDRAGON_HIT, 1, 2);
			
		@SuppressWarnings("deprecation")
        NamedItem portal = new NamedItem(Material.STICK, "Nachladen...");
			
		e.getPlayer().setItemInHand(portal);
			
		new SniperProjectile(p, 8);
			
		Bukkit.getScheduler().scheduleSyncDelayedTask(ZPortalWar.getInstance(), new SniperRunnable(p), 100L);
	}
}
