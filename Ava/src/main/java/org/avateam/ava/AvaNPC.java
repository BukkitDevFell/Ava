package org.avateam.ava;

import org.avateam.ava.logic.AttackTarget;
import org.avateam.ava.logic.WoodCutting;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.topcat.npclib.entity.HumanNPC;
import com.topcat.npclib.nms.NPCEntity;

public class AvaNPC extends HumanNPC{
	private SpoutPlayer player;
	private String texture = "http://s3.amazonaws.com/squirt/i4ecf2a7242e7952832826103242823282382812.png";
	private Player owner;
	private AttackTarget at;
	private WoodCutting wc;
	private boolean inAction = false;

	public AvaNPC(NPCEntity npcEntity) {
		super(npcEntity);
		player = this.getSpoutPlayer();
		player.setSkin(texture);
	}
	
	public void chat(String message) {
		player.chat(message);
	}
	public void setOwner(Player player) {
		owner = player;
	}
	public Player getOwner() {
		return owner;
	}
	public void chop() {
		if(wc == null) {
			wc = new WoodCutting(this);
		}
		if(inAction == false) {
			wc.start();
			inAction = true;
		}
	}
	public void build() {
		//TODO: Add Code
	}
	public void attck(LivingEntity ent, Main plugin) {
		if(at == null) {
			at = new AttackTarget(this,plugin);
		}
		at.setTarget(ent);
		plugin.chat.targets.put(ent,this);
		if(inAction == false) {
			Bukkit.getScheduler().scheduleAsyncRepeatingTask(plugin, at, 10L, 20L);
			inAction = true;
		}
	}
	public void stop(Main plugin) {
		Bukkit.getScheduler().cancelTasks(plugin);
		inAction = false;
	}
}
