package me.johnking.zportalwar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.util.ArrayList;

public class ScoreboardManager {

	private ArrayList<Objective> objectives = new ArrayList<>();

    public void activate(){
        for(Player p : Bukkit.getOnlinePlayers()) {
            objectives.add(p.getScoreboard().registerNewObjective("kills", "dummy"));
        }
        for(Objective objective : objectives) {
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName("\u00A76Ziel: \u00A7a" + ZPortalWar.getKills() + " Kills");
        }
    }

	public int addKill(Player p){
        int newscore = 0;
        for(Objective objective : objectives) {
            Score score = objective.getScore(p);
            newscore = score.getScore() + 1;
            score.setScore(newscore);
        }
		return newscore;
	}

    public int getKills(Player p){
        return objectives.get(0).getScore(p).getScore();
    }

	public void registerPlayer(Player p){
        for(Objective objective: objectives){
            objective.getScore(p).setScore(0);
        }
	}
	
	public ArrayList<Objective> getObjectives(){
		return objectives;
	}
}
