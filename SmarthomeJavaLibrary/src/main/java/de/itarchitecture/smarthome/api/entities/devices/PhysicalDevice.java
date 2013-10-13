/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 *
 * @author michael
 */
public class PhysicalDevice extends SmartHomeDevice {

    String physicalDeviceId = "";

    public String getPhysicalDeviceId() {
        return physicalDeviceId;
    }

    public void setPhysicalDeviceId(String physicalDeviceId) {
        this.physicalDeviceId = physicalDeviceId;
        this.setDeviceId(physicalDeviceId);
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public Boolean getIsReachable() {
        return isReachable;
    }

    public void setIsReachable(Boolean isReachable) {
        this.isReachable = isReachable;
    }

    public String getUpdateState() {
        return updateState;
    }

    public void setUpdateState(String updateState) {
        this.updateState = updateState;
    }

    public String getDeviceInclusionState() {
        return deviceInclusionState;
    }

    public void setDeviceInclusionState(String deviceInclusionState) {
        this.deviceInclusionState = deviceInclusionState;
    }

    public String getDeviceConfigurationState() {
        return deviceConfigurationState;
    }

    public void setDeviceConfigurationState(String deviceConfigurationState) {
        this.deviceConfigurationState = deviceConfigurationState;
    }
    String firmwareVersion = "";
    Boolean isReachable = Boolean.FALSE;
    String updateState = "UpToDate";
    String deviceInclusionState = "Included";
    String deviceConfigurationState = "Complete";
    @Override
    public String toString()
    {
        return "Physical Device: " + physicalDeviceId;
    }
}
