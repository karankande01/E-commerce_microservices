package com.ecom.productservice.dto;

import lombok.Data;

@Data
public class SpecificationsDto {

	
	  private long specificationsId;
		
	    private String brand;

	    // Network  GSM / HSPA/ LTE / 4G
	    private String network;

	    // SIM- Dual SIM (Nano-SIM, dual stand-by)
	    private String sim;

	    // Body
	    private double dimensionsX;
	    private double dimensionsY;
	    private double dimensionsZ;
	    private double weight;

	    // Display
	    private String displayType;
	    private double displaySize;
	    private int displayResolutionX;
	    private int displayResolutionY;
	    private boolean multitouch;

	    // Sound
	    private boolean loudspeaker;

	    // Features
	    private String os;
	    private String chipset;
	    private String cpu;
	    private String gpu;

	    // Memory
	    private String cardSlot;
	    private String internal;

	    // Camera
	    private String primaryCamera;
	    private String cameraFeatures;
	    private String videoResolution;
	    private String secondaryCamera;

	    // Data
	    private String wlan;
	    private String bluetooth;
	    private boolean nfc;
	    private String usb;
	    private boolean radio;

	    // Sensors
	    private boolean fingerprintSensor;
	    private boolean accelerometer;

	    // Battery
	    private String batteryStandBy;
	
}
