package me.johnking.zportalwar.util;

import net.fusemc.zcore.util.MC1_8Utils;
import net.fusemc.zcore.util.NamedItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatus {

    private static final ItemStack SNIPER = new NamedItem(Material.IRON_HOE, ChatColor.GRAY + "Sniper");
    private static final ItemStack SWORD = new NamedItem(Material.STONE_SWORD,ChatColor.RED + "Messer");

    private Portal[] portal;
	private Player player;
	private boolean ready;
    private int cooldown;
    private int lastsec;

	private static List<PlayerStatus> list = new ArrayList<PlayerStatus>();

	public PlayerStatus(Player player, Material material, int subId) {
		this.player = player;
        portal = new Portal[]{
            new Portal(material, subId),
            new Portal(material, subId)
        };
        this.ready = true;
		list.add(this);
	}

    public static void update(){
        for(PlayerStatus status: list){
            if(status.getPlayer().isOnline()){
                status.live();
            }
        }
    }

    private void live(){
        if(cooldown == 1) {
            for(Player other: Bukkit.getOnlinePlayers()){
                other.showPlayer(player);
            }
            player.getInventory().setItem(1, SNIPER);
            player.getInventory().setItem(2, SWORD);
        } else if(cooldown == 0) {
            return;
        }
        if(lastsec != cooldown / 20) {
            lastsec = cooldown / 20;
            MC1_8Utils.displayActionbarMessage(player, ChatColor.AQUA + "Sichtbar in " + lastsec + " Sekunden");
        }
        cooldown --;
    }

    public static List<PlayerStatus> getPlayerStatus(){
        return list;
    }

	public static PlayerStatus getPlayer(Player p) {
		for (PlayerStatus container : list) {
			if (container.getPlayer() == p)
				return container;
		}
		return null;
	}

	public Player getPlayer() {
		return player;
	}

	public Portal getPortal(int num) {
		if(num < 0 || num > 1){
            return null;
        }
        return portal[num];
	}

	public boolean isPortalReady() {
		return portal[0].isReady() && portal[1].isReady();
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

    public boolean isReady() {
        return ready;
    }

	public void clearPortals() {
		portal[0].deletePortal();
		portal[1].deletePortal();
	}

    public boolean isAttackable(){
        return cooldown == 0;
    }

    public void setRespawn(){
        for(Player other: Bukkit.getOnlinePlayers()){
            other.hidePlayer(player);
        }
        cooldown = 80;
    }

    public static final class PlayerStatusUpdater implements Runnable{

        @Override
        public void run(){
            PlayerStatus.update();
        }
    }
}
