/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 *
 * @author michael
 */
public class ValveActuator extends BaseActuator{
    private String valveIndex = "0";

    /**
     * @return the valveIndex
     */
    public String getValveIndex() {
        return valveIndex;
    }

    /**
     * @param valveIndex the valveIndex to set
     */
    public void setValveIndex(String valveIndex) {
        this.valveIndex = valveIndex;
    }
    
}
