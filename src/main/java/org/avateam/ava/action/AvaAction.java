/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avateam.ava.action;

import org.avateam.ava.AvaNPC;

/**
 * Represents an action Ava can do.
 */
public interface AvaAction {
    /**
     * Runs the action.
     * 
     * @param npc 
     */
    public void run(AvaNPC npc);

    public void run(AvaNPC npc, Object[] args);
}
