/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.itarchitecture.smarthome.api.entities.devices;

import java.util.Date;

// TODO: Auto-generated Javadoc
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
		
		/** The next sunset. */
		private Date nextSunset;
		
		/** The next sunrise. */
		private Date nextSunrise;
        
        /** The next time event. */
        private Date nextTimeEvent;
		
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

		/**
		 * Gets the next sunset.
		 *
		 * @return the next sunset
		 */
		public Date getNextSunset() {
			return nextSunset;
		}

		/**
		 * Sets the next sunset.
		 *
		 * @param nextSunset the new next sunset
		 */
		public void setNextSunset(Date nextSunset) {
			this.nextSunset = nextSunset;
		}

		/**
		 * Gets the next sunrise.
		 *
		 * @return the next sunrise
		 */
		public Date getNextSunrise() {
			return nextSunrise;
		}

		/**
		 * Sets the next sunrise.
		 *
		 * @param nextSunrise the new next sunrise
		 */
		public void setNextSunrise(Date nextSunrise) {
			this.nextSunrise = nextSunrise;
		}

		/**
		 * Gets the next time event.
		 *
		 * @return the next time event
		 */
		public Date getNextTimeEvent() {
			return nextTimeEvent;
		}

		/**
		 * Sets the next time event.
		 *
		 * @param nextTimeEvent the new next time event
		 */
		public void setNextTimeEvent(Date nextTimeEvent) {
			this.nextTimeEvent = nextTimeEvent;
		}

}
