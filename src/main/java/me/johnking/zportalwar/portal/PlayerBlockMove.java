package me.johnking.zportalwar.portal;

import fr.neatmonster.nocheatplus.checks.CheckType;
import me.johnking.zportalwar.util.PlayerStatus;
import me.michidk.DKLib.event.PlayerBlockMoveEvent;
import net.fusemc.zcore.util.NCPUtils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

public class PlayerBlockMove implements Listener{

	@EventHandler
	public void onPlyerBlockMove(PlayerBlockMoveEvent e){
		Player p = e.getPlayer();
		PlayerStatus pps = PlayerStatus.getPlayer(p);
		if(pps == null)
			return;
        if(!pps.isReady()){
            pps.setReady(true);
            return;
        }
        Location loc = e.getTo().getBlock().getLocation();
        for(PlayerStatus ps : PlayerStatus.getPlayerStatus()){
            if(!ps.isPortalReady()){
                continue;
            }
            for (int num = 0; num < 2; num++) {
                if(ps.getPortal(num).getLocation().equals(loc)){
                    teleport(p, num, ps);
                    pps.setReady(false);
                    return;
                }
            }
        }
	}
	
	private void teleport(Player p, int num, PlayerStatus ps) {
		Vector velocity = p.getVelocity();
		BlockFace befor = ps.getPortal(num).getFace();
		BlockFace after = ps.getPortal(1 - num).getFace();
		Vector vec = tovelocity(befor, after, velocity);
		float yaw = toyaw(after, p.getLocation().getYaw());
		Location teleloc = ps.getPortal(1 - num).getLocation().clone().add(0.5, 0, 0.5);
		teleloc.setPitch(p.getLocation().getPitch());
		teleloc.setYaw(yaw);
		p.teleport(teleloc);
		p.setVelocity(vec);
		p.getWorld().playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);

        //ANTICHEAT EXEMPT
        NCPUtils.exemptPlayer(p, 3, CheckType.MOVING);
	}
	
	private Vector tovelocity(BlockFace befor, BlockFace after, Vector velocity){
		double speed = (befor.getModX() * velocity.getX()) + (befor.getModY() * -velocity.getY()) + (befor.getModZ() * velocity.getZ());
		return new Vector(after.getModX() * speed, after.getModY() * speed, after.getModZ() * speed);
	}
	
	private float toyaw(BlockFace after, float oldyaw){
		float yaw = (oldyaw * Math.abs(after.getModY())) - (after.getModX() * 90) - (Math.abs(after.getModZ()) * 90) + (after.getModZ() * 90);
		return yaw;
	}
}
