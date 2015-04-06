package me.johnking.zportalwar.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class Portal{
	
	private Location block;
	private Location portal;
	private BlockFace face;
	private boolean updown;
	private boolean ready = false;
	private Material material;
	private int subid;
	
	public Portal(Material material, int subid){
		this.material = material;
		this.subid = subid;
	}
	
	@SuppressWarnings("deprecation")
	public void setPortal(Location loc, BlockFace state){
		deletePortal();
		boolean updown = false;
		if(state == BlockFace.UP || state == BlockFace.DOWN)
			updown = true;
		this.face = state;
		this.updown = updown;
		this.setBlock(loc);
		loc.getBlock().setTypeIdAndData(material.getId(), (byte) subid, true);
		if(updown == false){
            Location clone = loc.clone().add(0, -1, 0);
			clone.getBlock().setTypeIdAndData(material.getId(), (byte) subid, true);
		}
		Location blockloc = getSecondLoc(loc.clone(), face);
		this.setPortal(blockloc);
		this.ready = true;
	}
	
	@SuppressWarnings("deprecation")
	public void deletePortal(){
		if(ready == true){
			Location loc = this.getBlock().clone();
			this.getBlock().getBlock().setTypeId(42);
			if(updown == false)
				this.getBlock().add(0,-1,0).getBlock().setTypeId(42);
			ready = false;
			setPortal(loc);
		}
	}
	
	//getter setter
	public boolean isReady(){
		return ready;
	}

	public BlockFace getFace(){
		return face;
	}

	public Location getLocation(){
		return portal;
	}
	
	//converter
	private Location getSecondLoc(Location loc, BlockFace face) {
		if(face == BlockFace.UP){
			loc.add(0, 1, 0);
		}
		else{
			loc = loc.getBlock().getRelative(face, 1).getLocation().add(0, -1, 0);
		}
		return loc;
	}
	
	public int getSubId(){
		return subid;
	}

	public Material getMaterial(){
		return material;
	}
	
	private void setBlock(Location loc){
		this.block = loc;
	}
	
	private void setPortal(Location loc){
		this.portal = loc;
	}
	
	private Location getBlock(){
		return block;
	}
}