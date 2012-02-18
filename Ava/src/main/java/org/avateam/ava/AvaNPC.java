package org.avateam.ava;

import org.avateam.ava.logic.WoodCutting;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.topcat.npclib.entity.HumanNPC;
import com.topcat.npclib.nms.NPCEntity;

public class AvaNPC extends HumanNPC{
	SpoutPlayer player;
	String texture = "http://s3.amazonaws.com/squirt/i4ecf2a7242e7952832826103242823282382812.png";

	public AvaNPC(NPCEntity npcEntity) {
		super(npcEntity);
		player = this.getSpoutPlayer();
		player.setSkin(texture);
	}
	
	public void chat(String message) {
		player.chat(message);
	}
	public void chop() {
		WoodCutting wc = new WoodCutting(this);
		wc.start();
	}
	public void build() {
		//TODO: Add Code
	}
}
