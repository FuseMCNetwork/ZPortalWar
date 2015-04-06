package me.johnking.zportalwar.portal;

import me.johnking.zportalwar.util.PlayerStatus;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PortalCreate implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		if(e.getAction().equals(Action.PHYSICAL))
			return;
		Player p = e.getPlayer();
		PlayerStatus ps = PlayerStatus.getPlayer(p);
		if(!p.getItemInHand().getType().equals(Material.BLAZE_ROD) || ps == null)
			return;
		int number = (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) ? 0 : 1;
		boolean air = (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_AIR));
		if(air){
			new PortalProjectile(p, new ItemStack(ps.getPortal(number).getMaterial(), 1, (short)0, (byte) ps.getPortal(number).getSubId()), number);
			return;
		}
		else{
			if(!e.getClickedBlock().getType().equals(Material.IRON_BLOCK))
				return;
			boolean updown = false;
			if(e.getBlockFace().equals(BlockFace.UP) || e.getBlockFace().equals(BlockFace.DOWN))
				updown = true;
			if((e.getClickedBlock().getLocation().add(0,-1,0).getBlock().getType().equals(Material.IRON_BLOCK) && !updown) || updown)
				ps.getPortal(number).setPortal(e.getClickedBlock().getLocation(), e.getBlockFace());
			else if((e.getClickedBlock().getLocation().add(0, 1, 0).getBlock().getType().equals(Material.IRON_BLOCK) && !updown))
				ps.getPortal(number).setPortal(e.getClickedBlock().getLocation().add(0, 1, 0), e.getBlockFace());
		}
	}
}
