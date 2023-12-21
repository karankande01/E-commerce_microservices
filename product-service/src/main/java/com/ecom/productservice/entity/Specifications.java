package com.ecom.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Specifications {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long specificationsId;
	
	@NotBlank(message = "Brand is required")
    private String brand;

    @Size(max = 255, message = "Product summary cannot exceed 255 characters")
    private String productSummary;

    // Network GSM / HSPA/ LTE / 4G
    @NotBlank(message = "Network type is required")
    private String network;

    // SIM- Dual SIM (Nano-SIM, dual stand-by)
    @NotBlank(message = "SIM type is required")
    private String sim;

    // Body
    @PositiveOrZero(message = "DimensionsX must be a positive or zero value")
    private double dimensionsX;

    @PositiveOrZero(message = "DimensionsY must be a positive or zero value")
    private double dimensionsY;

    @PositiveOrZero(message = "DimensionsZ must be a positive or zero value")
    private double dimensionsZ;

    @PositiveOrZero(message = "Weight must be a positive or zero value")
    private double weight;

    // Display
    @NotBlank(message = "Display type is required")
    private String displayType;

    @Positive(message = "Display size must be a positive value")
    private double displaySize;

    @Min(value = 1, message = "Display resolution X must be at least 1")
    private int displayResolutionX;

    @Min(value = 1, message = "Display resolution Y must be at least 1")
    private int displayResolutionY;

    private boolean multitouch;

    // Sound
    private boolean loudspeaker;

    // Features
    @NotBlank(message = "Operating system is required")
    private String os;

    @NotBlank(message = "Chipset is required")
    private String chipset;

    @NotBlank(message = "CPU is required")
    private String cpu;

    @NotBlank(message = "GPU is required")
    private String gpu;

    // Memory hybrid
    @NotBlank(message = "Card slot type is required")
    private String cardSlot;

    @NotBlank(message = "Internal memory is required")
    private String internal;

    // Camera
    @NotBlank(message = "Primary camera information is required")
    private String primaryCamera;

    @NotBlank(message = "Camera features are required")
    private String cameraFeatures;

    @NotBlank(message = "Video resolution is required")
    private String videoResolution;

    @NotBlank(message = "Secondary camera information is required")
    private String secondaryCamera;

    // Data
    @NotBlank(message = "WLAN information is required")
    private String wlan;

    @NotBlank(message = "Bluetooth information is required")
    private String bluetooth;

    private boolean nfc;

    @NotBlank(message = "USB information is required")
    private String usb;

    private boolean radio;

    // Sensors
    private boolean fingerprintSensor;

    private boolean accelerometer;

    // Battery
    @NotBlank(message = "Battery standby information is required")
    private String batteryStandBy;
}
