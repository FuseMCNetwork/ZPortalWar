package me.johnking.zportalwar.sniper;

import net.fusemc.zcore.projectileAPI.event.CustomProjectileEvent;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;

public class SniperProjectileEvent extends CustomProjectileEvent{
	
	private static HandlerList handlers = new HandlerList();
	
	public SniperProjectileEvent(SniperProjectile projectile, Block block, BlockFace face, LivingEntity entity) {
		super(projectile, block, face, entity);
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	@Override
	public SniperProjectile getProjectile(){
		return (SniperProjectile) super.getProjectile();
	}
}
