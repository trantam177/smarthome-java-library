/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

/**
 * The Class DaySensor.
 *
 * @author sammy98
 */
public class DaySensor extends GenericSensor {
        
        /** The latitude. */
        private String latitude;
        
        /** The longitude. */
        private String longitude;
		
        /**
         * Gets the latitude.
         *
         * @return the latitude
         */
        public String getLatitude() {
			return latitude;
		}
		
		/**
		 * Sets the latitude.
		 *
		 * @param latitude the new latitude
		 */
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		
		/**
		 * Gets the longitude.
		 *
		 * @return the longitude
		 */
		public String getLongitude() {
			return longitude;
		}
		
		/**
		 * Sets the longitude.
		 *
		 * @param longitude the new longitude
		 */
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

}
