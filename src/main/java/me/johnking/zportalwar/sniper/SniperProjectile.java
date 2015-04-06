package me.johnking.zportalwar.sniper;

import me.michidk.DKLib.effects.ParticleEffect;
import net.fusemc.zcore.projectileAPI.event.CustomProjectileEvent;
import net.fusemc.zcore.projectileAPI.projectiles.ItemProjectile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SniperProjectile extends ItemProjectile{

    private double damage;

    public SniperProjectile(Player shooter, double damage) {
        super(shooter, new ItemStack(Material.NETHER_STAR), 1.75F);
        this.damage = damage;
    }

    public double getDamage(){
        return damage;
    }

    @Override
    public void h(){
        super.h();

        ParticleEffect.FIREWORKS_SPARK.play(new Location(world.getWorld(), this.locX, this.locY - 100, this.locZ), 0.0F, 0.0F, 0.0F, 0, 1);
    }

    @Override
    public CustomProjectileEvent call(Block block, BlockFace face, LivingEntity entity, ItemStack item){
        SniperProjectileEvent event = new SniperProjectileEvent(this, block, face, entity);
        Bukkit.getPluginManager().callEvent(event);
        return event;
    }
}
