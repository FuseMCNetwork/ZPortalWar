package me.johnking.zportalwar;

import me.johnking.zportalwar.enumeration.PlayerColor;
import me.johnking.zportalwar.events.*;
import me.johnking.zportalwar.portal.PlayerBlockMove;
import me.johnking.zportalwar.portal.PortalCreate;
import me.johnking.zportalwar.portal.PortalProjectileHit;
import me.johnking.zportalwar.sniper.ShotSniper;
import me.johnking.zportalwar.util.GameTimer;
import me.johnking.zportalwar.util.PlayerStatus;
import me.johnking.zsignadapter.ZSignAdapter;
import net.fusemc.zcore.featureSystem.features.lobbyFeature.CountdownBreakEvent;
import net.fusemc.zcore.featureSystem.features.lobbyFeature.CountdownTickEvent;
import net.fusemc.zcore.featureSystem.features.lobbyFeature.GameStartEvent;
import net.fusemc.zcore.util.MC1_8Utils;
import net.fusemc.zcore.util.NamedItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GameStart implements Listener{

    private int seconds = 15;
    private int schedulerID;

	private Runnable run = new Runnable(){
		@Override
		public void run(){
            if(seconds != 0) {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    MC1_8Utils.displayActionbarMessage(p, ChatColor.AQUA + "Spiel startet in " + seconds + " Sekunde(n)!");
                }
                seconds--;
                return;
            }
            for(Player p : Bukkit.getOnlinePlayers()) {
                MC1_8Utils.displayActionbarMessage(p, "\u00A78\u00A7l[\u00A7d\u2756\u00A78\u00A7l] \u00A7aDu bist jetzt verwundbar!");
            }
			ZPortalWar.getInstance().setDamage(true);
			Bukkit.broadcastMessage("\u00A78\u00A7l[\u00A7d\u2756\u00A78\u00A7l] \u00A7aDu bist jetzt verwundbar!");
            for(Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
            }
            Bukkit.getScheduler().cancelTask(schedulerID);
		}
	};

    @EventHandler
    public void onTick(CountdownTickEvent e){
        if (e.getTime() == 10){
            ZSignAdapter.setJoinable(false);
        }
    }

    @EventHandler
    public void onBreak(CountdownBreakEvent e){
        ZSignAdapter.setJoinable(true);
    }
	
	@EventHandler
	public void onGameStart(GameStartEvent e){
        ZSignAdapter.setJoinable(false);
		ZPortalWar.getInstance().setWorldHandler(new WorldHandler(e.getWorld()));
        Bukkit.broadcastMessage("\u00a78\u00a7l[\u00a7d\u2756\u00a78\u00a7l] \u00a73 " + e.getMinigameWinner().getDisplayName() + " \u00a7ahatte als letzter den Goldblock und bekommt einen \u00a73Speedboost\u00a7a!");
        e.getMinigameWinner().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
        Bukkit.broadcastMessage("\u00A78\u00A7l[\u00A7d\u2756\u00A78\u00A7l] \u00A7aDu bist in \u00A7315 Sekunden \u00A7averwundbar!");
		
		ItemStack portalgun = new NamedItem(Material.BLAZE_ROD, ChatColor.BLUE + "Portal" + ChatColor.GOLD + "Gun");
		ItemStack sniper = new NamedItem(Material.IRON_HOE, ChatColor.GRAY + "Sniper");
		ItemStack sword = new NamedItem(Material.STONE_SWORD,ChatColor.RED + "Messer");

        ZPortalWar.getInstance().getScoreboard().activate();
		
		for(Player p: Bukkit.getOnlinePlayers()){
			setPlayer(p);
			
			p.getInventory().addItem(portalgun);
			p.getInventory().addItem(sniper);
			p.getInventory().addItem(sword);

			p.teleport(ZPortalWar.getInstance().getWorldHandler().nextSpawn());
			ZPortalWar.getInstance().getScoreboard().addKill(p);
			ZPortalWar.getInstance().getScoreboard().registerPlayer(p);
		}
		
		//Bukkit.getScheduler().scheduleSyncDelayedTask(ZPortalWar.getInstance(), run, 300L);
        this.schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(ZPortalWar.getInstance(), run, 0L, 20L);

        registerListener();
        new GameTimer(15);
		HandlerList.unregisterAll(this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(ZPortalWar.getInstance(), new PlayerStatus.PlayerStatusUpdater(), 0, 1);
	}
	
	public void setPlayer(Player p){
		PlayerColor pc = PlayerColor.getNext();
		new PlayerStatus(p, pc.getMaterial(), pc.getSubId());
		
	    ItemStack lchestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
	    LeatherArmorMeta lam = (LeatherArmorMeta)lchestplate.getItemMeta();
	    lam.setColor(pc.getColor().getColor());
	    lchestplate.setItemMeta(lam);
		
		ItemStack wool = new NamedItem(pc.getMaterial(), 1, (short)pc.getSubId(), ChatColor.GOLD + "Portalfarbe");
		
		p.getInventory().setChestplate(lchestplate);
		p.getInventory().setItem(8, wool);
	}
	
	private void registerListener(){
		Listener[] listeners = {
			new BlockBreakPlace(),
			new EntityByEntityDamage(),
			new PlayerDamage(),
			new PlayerDeath(),
			new PlayerDropItem(),
			new PlayerLeave(),
			new PlayerRespawn(),
			new PlayerBlockMove(),
			new PortalCreate(),
			new PortalProjectileHit(),
			new ShotSniper()
		};
		
		PluginManager pm = Bukkit.getPluginManager();
		for(Listener listener: listeners)
			pm.registerEvents(listener, ZPortalWar.getInstance());
	}
}
