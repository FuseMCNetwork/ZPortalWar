package me.johnking.zportalwar.portal;

import me.johnking.zportalwar.util.PlayerStatus;
import net.fusemc.zcore.projectileAPI.HitType;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PortalProjectileHit implements Listener {
	
	@EventHandler
	public void onProjectileHit(PortalProjectileEvent e){
		if(!e.getHitType().equals(HitType.BLOCK))
			return;
		boolean updown = false;
		if(e.getHitFace() == BlockFace.UP || e.getHitFace() == BlockFace.DOWN)
			updown = true;
		if(!e.getHitBlock().getType().equals(Material.IRON_BLOCK))
			return;
		PlayerStatus ps = PlayerStatus.getPlayer((Player) e.getProjectile().getShooter());
		if(ps == null)
			return;
		if((e.getHitBlock().getLocation().add(0,-1,0).getBlock().getType().equals(Material.IRON_BLOCK) && !updown) || updown)
			ps.getPortal(e.getProjectile().getNumber()).setPortal(e.getHitBlock().getLocation(), e.getHitFace());
		else if((e.getHitBlock().getLocation().add(0, 1, 0).getBlock().getType().equals(Material.IRON_BLOCK) && !updown))
			ps.getPortal(e.getProjectile().getNumber()).setPortal(e.getHitBlock().getLocation().add(0, 1, 0), e.getHitFace());
	}
}
