#Introduction to java library for RWE smarthome

# Introduction #

The RWE Smarthome system consists of different actors and sensors, which are then controlled via a central system "Zentrale". This center can be controlled by default either via a Silverlight Software provided by RWE or via RWEs own portal. There is no public available api. This project provides a java library which can be used to write your own application directly controlling the "Zentrale" and an android app, which is allow to access the most common functionality.


# Details #

Currently the following hardware is supported:
  * "Zentrale": Login, logout, session handling
  * Switches/state variables: Read current state, switch current state
  * Thermostats, humidity sensors, temperature sensore: read current state, change target temperature of thermostat
  * Door/window sensors: read current state
  * Dimmer: read current state, set dim level
  * E-Mail, SMS app: read current state
  * Sundown app: read current state
  * Smoke detector: read current state