package de.itarchitecture.smarthome.api.entities.devices;

public class WindowDoorSensor extends LogicalDevice {
	private boolean isOpen = false; 
	private String state = "";
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
		if (isOpen)
			state = "open";
		else
			state = "closed";
	}
	public String getState() {
		return state;
	}
	/**
	 * 
	 */
	public WindowDoorSensor() {
		super();
        this.setType(LogicalDevice.Type_WindowDoorSensor);
	} 
	
}
