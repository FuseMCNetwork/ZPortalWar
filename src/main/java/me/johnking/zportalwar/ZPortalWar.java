package me.johnking.zportalwar;

import me.johnking.zportalwar.util.PlayerStatus;
import me.johnking.zsignadapter.ZSignAdapter;
import net.fusemc.zcore.ZCore;
import net.fusemc.zcore.featureSystem.FeatureManager;
import net.fusemc.zcore.featureSystem.features.AntiWorldTransformFeature;
import net.fusemc.zcore.featureSystem.features.BloodFeature;
import net.fusemc.zcore.featureSystem.features.ChatFormatFeature;
import net.fusemc.zcore.featureSystem.features.TabTitleFeature;
import net.fusemc.zcore.featureSystem.features.disguiseFeature.DisguiseFeature;
import net.fusemc.zcore.featureSystem.features.jumppadFeature.JumpPapFeature;
import net.fusemc.zcore.featureSystem.features.lobbyFeature.LobbyFeature;
import net.fusemc.zcore.featureSystem.features.lobbyFeature.LobbySettings;
import net.fusemc.zcore.featureSystem.features.messageFeature.DeathFeature;
import net.fusemc.zcore.featureSystem.features.messageFeature.JoinLeaveFeature;
import net.fusemc.zcore.featureSystem.features.messageFeature.WelcomeFeature;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;

public class ZPortalWar extends JavaPlugin{

	private static ZPortalWar instance;
	
	private ScoreboardManager scoreboard;
	private boolean damage = false;
	
	private WorldHandler handler;
	
	@Override
	public void onEnable(){
		instance = this;
		
		registerListener();
		scoreboard = new ScoreboardManager();

        FeatureManager fm = ZCore.getFeatureManager();
        fm.getFeature(LobbyFeature.class).enable(new LobbySettings(4, 12, 60, Bukkit.getWorld("WGameLobby")));
        fm.getFeature(JumpPapFeature.class).enable();
        fm.getFeature(JoinLeaveFeature.class).enable();
        fm.getFeature(ChatFormatFeature.class).enable();
        fm.getFeature(AntiWorldTransformFeature.class).enable();
        fm.getFeature(BloodFeature.class).enable();
        fm.getFeature(DisguiseFeature.class).enable();
        fm.getFeature(DeathFeature.class).enable();
        fm.getFeature(WelcomeFeature.class).enable("Portal War");
        fm.getFeature(TabTitleFeature.class).enable("Portal War");
		
		ZSignAdapter.register("PW");
	}
	
	@Override
	public void onDisable(){
		for(Player p: Bukkit.getOnlinePlayers()){
			PlayerStatus ps = PlayerStatus.getPlayer(p);
			if(ps != null)
				ps.clearPortals();
			p.kickPlayer("lobby");
		}
        for(Objective objective : scoreboard.getObjectives()) {
            objective.unregister();
        }
	}
	
	private void registerListener(){
		Listener[] listener = 
			{
				new GameStart()
			};
		PluginManager pm = Bukkit.getPluginManager();
		for(Listener l: listener){
			pm.registerEvents(l, this);
		}
	}
	
	public static ZPortalWar getInstance(){
		return instance;
	}
	
	public static int getKills(){
		return 20;
	}
	
	public ScoreboardManager getScoreboard(){
		return scoreboard;
	}
	
	public boolean isDamage(){
		return damage;
	}
	
	public void setDamage(boolean damage){
		this.damage = damage;
	}
	
	public WorldHandler getWorldHandler(){
		return handler;
	}
	
	public void setWorldHandler(WorldHandler handler){
		this.handler = handler;
	}
}
