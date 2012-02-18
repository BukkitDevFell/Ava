package org.avateam.ava.Resource;

import org.bukkit.Location;
import org.bukkit.block.Biome;

import com.topcat.npclib.entity.HumanNPC;

public class WoodCutting extends Thread implements Runnable{

	public HumanNPC npc;
	
	public WoodCutting(HumanNPC npc){
		this.npc = npc;
	}
	
	public void run() {
		// iterate around for blocks
		// max vertical limit of +/- the npc's location
		// check chunks for biomes?? that way if its a desert it wont look there
		// it will look for the closest other biome
		Location loc = npc.getBukkitEntity().getLocation();
		Biome npcCurrentBiome = loc.getBlock().getBiome();
		if (npcCurrentBiome==(Biome.DESERT) || npcCurrentBiome==(Biome.BEACH) || npcCurrentBiome==(Biome.DESERT_HILLS) || npcCurrentBiome==(Biome.ICE_DESERT) || npcCurrentBiome==(Biome.ICE_PLAINS) || npcCurrentBiome==(Biome.PLAINS)) {
			int blockX = loc.getBlockX();
			int blockZ = loc.getBlockZ();
			int searchArea = 3;
			int i = (int)(blockX/16)-searchArea;
			int j = (int)(blockZ/16)-searchArea;
			System.out.println("Searching from "+i+"  "+j);
			boolean searching = true;
			System.out.println("Looking for wood ;)");
			while (i < blockX + searchArea && searching) {
				j = (int)(blockZ/16)-searchArea;
				while (searching && j < blockZ + searchArea) {
					
					Biome biomeTest = loc.getWorld().getBiome(i*16, j*16);
					if (biomeTest.equals(Biome.DESERT) || biomeTest.equals(Biome.BEACH) || biomeTest.equals(Biome.DESERT_HILLS) || biomeTest.equals(Biome.ICE_DESERT) || biomeTest.equals(Biome.ICE_PLAINS) || biomeTest.equals(Biome.PLAINS)) {
						j++;
						System.out.println("Biome "+ biomeTest.name());
					}else{
						System.out.println("Found wood biome :D " + i + "," + j);
						searching = false;
					}
				}
				i++;
			}
		}
	}

}
