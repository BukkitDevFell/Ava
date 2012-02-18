/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.avateam.ava.action;

import org.avateam.ava.AvaNPC;

/**
 *
 * @author simplyianm
 */
public abstract class SimpleAction implements AvaAction {
    public void run(AvaNPC npc, Object[] args) {
        run(npc);
    }

}
