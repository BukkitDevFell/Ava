package org.avateam.ava.logic;

import org.avateam.ava.AvaNPC;
import org.bukkit.entity.LivingEntity;

public class AttackTarget {
	private LivingEntity target;
	private AvaNPC npc;
	
	public AttackTarget(AvaNPC npc) {
		this.npc = npc;
	}
	
	public void Attack() {
		target.damage(4, npc.getBukkitEntity());
	}
	
	public void setTarget(LivingEntity target) {
		this.target = target;
	}
}

//TODO: Someone Feel free to work on