package de.itarchitecture.smarthome.api.entities.devices;

import java.util.ArrayList;

/**
 * Actuator class with userlist (email and sms)
 *
 * @author sammy98
 */
public class SpecialActuator extends BaseActuator {

	/** The contacts. */
	private ArrayList<User> contacts = new ArrayList<User>();
	/**
	 * Adds the user.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void addUser(String name, String value) {
		User u = new User(name, value);
		this.contacts.add(u);
	}
	
	/**
	 * Gets the contacts.
	 *
	 * @return the contacts
	 */
	public ArrayList<User> getContacts() {
		return contacts;
	}

	/**
	 * Sets the contacts.
	 *
	 * @param contacts the new contacts
	 */
	public void setContacts(ArrayList<User> contacts) {
		this.contacts = contacts;
	}
	
	/**
	 * The Class User.
	 */
	protected class User {
		
		/** The name. */
		private String name;
		
		/** The value. */
		private String value;
		
		/**
		 * Instantiates a new user.
		 *
		 * @param name the name
		 * @param value the value
		 */
		public User(String name, String value) {
			this.name = name;
			this.value = value;
		}
		
		/**
		 * Gets the name.
		 *
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * Sets the name.
		 *
		 * @param name the new name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * Gets the value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
		
		/**
		 * Sets the value.
		 *
		 * @param value the new value
		 */
		public void setValue(String value) {
			this.value = value;
		}
	}
}
