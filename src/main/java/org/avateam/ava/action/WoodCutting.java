package org.avateam.ava.action;

import org.avateam.ava.AvaNPC;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;

public class WoodCutting extends SimpleAction {
	public void run(AvaNPC npc) {
		// iterate around for blocks
		// max vertical limit of +/- the npc's location
		// check chunks for biomes?? that way if its a desert it wont look there
		// it will look for the closest other biome
		Location loc = npc.getBukkitEntity().getLocation();
		Biome npcCurrentBiome = loc.getBlock().getBiome();
		if(biomeCheck(npcCurrentBiome)){
			searchForWoodBiome(npc, loc);
			loc = npc.getBukkitEntity().getLocation();
			npcCurrentBiome = loc.getBlock().getBiome();
		}
		
		//wood search code, If not found she walks 16? blocks and does this action again?
	}
	
	public synchronized void searchForWoodBiome(AvaNPC npc, Location loc){
		int blockX = loc.getBlockX();
		int blockZ = loc.getBlockZ();
		int searchArea = 2;
		int i = (int) blockX - (searchArea * 16);
		int j = (int) blockZ - (searchArea * 16);
		int k = (int) blockX + (searchArea * 16);
		int l = (int) blockZ + (searchArea * 16);
		int m = Math.max(i, k);
		int n = Math.max(j, l);
		int o = Math.min(i, k);
		int p = Math.min(j, l);
		System.out.println("Searching from " + o + "  " + p);
		boolean searching = true;
		System.out.println("Looking for wood ;)");
		while (o < m && searching) {
			p = Math.min(j, l);
			while (searching && p < n) {

				Biome biomeTest = loc.getWorld().getBiome(i * 16, j * 16);
				if (biomeCheck(biomeTest)) {
					p += 16;
					System.out.println("Biome " + biomeTest.name());
				} else {
					System.out.println("Found wood biome :D " + o + "," + p);
					World currentWorld =  npc.getBukkitEntity().getWorld();
					int highestY = currentWorld.getHighestBlockYAt(o, p);
					if (!(currentWorld.getBlockAt(o, highestY, p).getType().equals(Material.GRASS))&&!(currentWorld.getBlockAt(o, highestY, p).getType().equals(Material.DIRT))&&!(currentWorld.getBlockAt(o, highestY, p).getType().equals(Material.STONE))){
						boolean notGoodBlock = true;
						while(notGoodBlock){
							highestY -= 1;
							if (!(currentWorld.getBlockAt(o, highestY, p).getType().equals(Material.GRASS))&&!(currentWorld.getBlockAt(o, highestY, p).getType().equals(Material.DIRT))&&!(currentWorld.getBlockAt(o, highestY, p).getType().equals(Material.STONE))){
								
							}else{
								notGoodBlock = false;
							}

						}
					}
					npc.walkTo(new Location(currentWorld, o, highestY, p));
					searching = false;
				}
			}
			o += 16;
		}
	}
	
	private boolean biomeCheck(Biome npcCurrentBiome){
		if (npcCurrentBiome == (Biome.DESERT) || npcCurrentBiome == (Biome.BEACH) || npcCurrentBiome == (Biome.DESERT_HILLS) || npcCurrentBiome == (Biome.ICE_DESERT) || npcCurrentBiome == (Biome.ICE_PLAINS) || npcCurrentBiome == (Biome.PLAINS) || npcCurrentBiome.equals(Biome.OCEAN)) {
			return true;
		}
		return false;
	}

}
