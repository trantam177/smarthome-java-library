/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 * The Class GenericSensor.
 *
 * @author sammy98, hackground.rg
 */
public class GenericSensor extends BaseSensor {
        
        /** The SD pp n. */
        private String SDPpN;

		/**
		 * Gets the sD pp n.
		 *
		 * @return the sD pp n
		 */
		public String getSDPpN() {
			return SDPpN;
		}

		/**
		 * Sets the sD pp n.
		 *
		 * @param sDPpN the new sD pp n
		 */
		public void setSDPpN(String sDPpN) {
			SDPpN = sDPpN;
		}
}
