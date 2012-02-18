package org.avateam.ava;

import org.avateam.ava.Resource.WoodCutting;
import org.bukkit.Location;
import org.bukkit.block.Biome;

import com.topcat.npclib.entity.HumanNPC;

public class AvaLogic {
	@SuppressWarnings("unused")
	private Main plugin;

	public AvaLogic(Main instance) {
		plugin = instance;
	}

	public void ChopWood(HumanNPC npc) {
		System.out.println("Beginning the wood thread");
		WoodCutting wc = new WoodCutting(npc);
		
		wc.start();
	}
}
