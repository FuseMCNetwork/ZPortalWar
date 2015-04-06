package me.johnking.zportalwar.portal;

import net.fusemc.zcore.projectileAPI.event.CustomProjectileEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;

public class PortalProjectileEvent extends CustomProjectileEvent{

	private static HandlerList handlers = new HandlerList();
	
	public PortalProjectileEvent(PortalProjectile projectile, Block block, BlockFace face, LivingEntity entity) {
		super(projectile, block, face, entity);
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	@Override
	public HandlerList getHandlers(){
		return handlers;
	}
	
	@Override
	public PortalProjectile getProjectile(){
		return (PortalProjectile) super.getProjectile();
	}
}
