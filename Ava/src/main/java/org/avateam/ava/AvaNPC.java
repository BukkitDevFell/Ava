package org.avateam.ava;

import org.avateam.ava.logic.AttackTarget;
import org.avateam.ava.logic.WoodCutting;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.topcat.npclib.entity.HumanNPC;
import com.topcat.npclib.nms.NPCEntity;

public class AvaNPC extends HumanNPC{
	private SpoutPlayer player;
	private String texture = "http://s3.amazonaws.com/squirt/i4ecf2a7242e7952832826103242823282382812.png";
	private Player owner;

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
		WoodCutting wc = new WoodCutting(this);
		wc.start();
	}
	public void build() {
		//TODO: Add Code
	}
	public void attck(LivingEntity ent) {
		AttackTarget at = new AttackTarget(this);
		at.setTarget(ent);
		at.Attack();
	}
}
