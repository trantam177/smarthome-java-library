package de.itarchitecture.smarthome.api.entities.devices;

/**
 * Represents a rollershutter
 *
 * @author sammy98
 */
public class RollerShutterActuator extends BaseActuator {

    private int OnLvl;
    private int OffLvl;
    private String ShDT;
    private String SCBh;
    private String TmFU;
    private String TmFD;
    private boolean IsCalibrating;
	private int shutterLevel;
	
	public String getShDT() {
		return ShDT;
	}
	public void setShDT(String shDT) {
		ShDT = shDT;
	}
	public String getSCBh() {
		return SCBh;
	}
	public void setSCBh(String sCBh) {
		SCBh = sCBh;
	}
	public String getTmFU() {
		return TmFU;
	}
	public void setTmFU(String tmFU) {
		TmFU = tmFU;
	}
	public String getTmFD() {
		return TmFD;
	}
	public void setTmFD(String tmFD) {
		TmFD = tmFD;
	}
	public boolean isIsCalibrating() {
		return IsCalibrating;
	}
	public void setIsCalibrating(boolean isCalibrating) {
		IsCalibrating = isCalibrating;
	}
	public int getOnLvl() {
		return OnLvl;
	}
	public void setOnLvl(int onLvl) {
		OnLvl = onLvl;
	}
	public int getOffLvl() {
		return OffLvl;
	}
	public void setOffLvl(int offLvl) {
		OffLvl = offLvl;
	}
	public int getShutterLevel() {
		return shutterLevel;
	}
	public void setShutterLevel(int shutterLevel) {
		this.shutterLevel = shutterLevel;
	}
	
}
