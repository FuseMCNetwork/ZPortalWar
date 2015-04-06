package me.johnking.zportalwar.portal;

import me.michidk.DKLib.effects.ParticleEffect;
import net.fusemc.zcore.projectileAPI.event.CustomProjectileEvent;
import net.fusemc.zcore.projectileAPI.projectiles.ItemProjectile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class PortalProjectile extends ItemProjectile{

	private int number;
	
	public PortalProjectile(Player shooter, ItemStack item, int number) {
		super(shooter, item, 2F);
		this.number = number;
	}
	
	public int getNumber(){
		return number;
	}

    @Override
    public void h() {
        super.h();

        ParticleEffect.MOB_SPELL.play(new Location(world.getWorld(), this.locX, this.locY - 100, this.locZ), 0.0F, 0.0F, 0.0F, 1, 1);
    }
	
	@Override
	public CustomProjectileEvent call(Block block, BlockFace face, LivingEntity entity, ItemStack item){
		PortalProjectileEvent event = new PortalProjectileEvent(this, block, face, entity);
		Bukkit.getPluginManager().callEvent(event);
		return event;
	}
}
