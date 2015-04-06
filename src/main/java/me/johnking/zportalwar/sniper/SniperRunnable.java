package me.johnking.zportalwar.sniper;

import me.johnking.zportalwar.util.PlayerStatus;
import net.fusemc.zcore.util.NamedItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SniperRunnable implements Runnable{

    private static final ItemStack SNIPER = new NamedItem(Material.IRON_HOE, "Sniper");
	private Player player;
	
	public SniperRunnable(Player player){
		this.player = player;
	}
	
	@Override
	public void run(){
        PlayerStatus status = PlayerStatus.getPlayer(player);
        if(status == null){
            return;
        }
        if(status.isAttackable()){
            player.getInventory().setItem(1, SNIPER);
        }
	}
	
	public Player getPlayer(){
		return player;
	}
	
}
