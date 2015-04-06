package me.johnking.zportalwar;

import me.michidk.DKLib.FileHelper;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import org.bukkit.craftbukkit.libs.com.google.gson.GsonBuilder;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldHandler {

	private World world;
	private Gson json;
	private File file;
	private List<Location> spawns = new ArrayList<Location>();
    private int next = 0;
	
	public WorldHandler(World world){
        this(world, new GsonBuilder().setPrettyPrinting().create());
	}

    public WorldHandler(World world, Gson json){
        this.world = world;
        this.json = json;
        this.file = new File(Bukkit.getWorldContainer() + File.separator + world.getName(), "spawns.json");
        this.loadWorld();
        this.checkDefaults();
        this.loadConfig();
    }
	
	public void loadWorld(){
		world.setDifficulty(Difficulty.PEACEFUL);
		world.setGameRuleValue("doDaylightCycle"," false");
		world.setGameRuleValue("keepInventory", "true");
	}
	
	public void checkDefaults(){
		if(file.exists()){
            return;
        }
        try {
            throw new FileNotFoundException("No spawns defined!");
        } catch(FileNotFoundException exc){
            exc.printStackTrace();
        }
	}
	
	public void loadConfig(){
		Vector[] vectors = json.fromJson(FileHelper.stringFromFile(file), Vector[].class);
		for(Vector vec: vectors)
			spawns.add(vec.toLocation(world));
	}
	
	public Location randomSpawn(){
		Random r = new Random();
		int num = r.nextInt(spawns.size());
		return spawns.get(num);
	}

    public Location nextSpawn(){
        if(this.next >= spawns.size()){
            this.next = 0;
        }
        this.next++;
        return spawns.get(this.next - 1);
    }

    public Location getSpawn(int index){
        return spawns.get(index);
    }
}
