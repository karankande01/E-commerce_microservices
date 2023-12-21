package com.ecom.advertisement.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "advertisements")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long advertisementId;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotEmpty(message = "Image URLs cannot be empty")
    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    @Pattern(regexp = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)", message = "Invalid image URL format")
    private List<String> imageUrl;

    @NotBlank(message = "Link URL is required")
    @Size(max = 255, message = "Link URL cannot exceed 255 characters")
    @URL(message = "Invalid URL format")
    private String linkUrl;

    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    @FutureOrPresent(message = "End date must be in the future or present")
    private LocalDateTime endDate;

    @Min(value = 1, message = "Priority must be greater than or equal to 1")
    private Integer priority;

    @NotNull(message = "Active status is required")
    private Boolean isActive;

    @Size(max = 50, message = "Created by cannot exceed 50 characters")
    private String createdBy;

    @PastOrPresent(message = "Created date must be in the past or present")
    private LocalDateTime createdDate;

    @Size(max = 50, message = "Modified by cannot exceed 50 characters")
    private String modifiedBy;

    @PastOrPresent(message = "Modified date must be in the past or present")
    private LocalDateTime modifiedDate;

    @Size(max = 255, message = "Target audience cannot exceed 255 characters")
    private String targetAudience;

    @Size(max = 50, message = "Campaign ID cannot exceed 50 characters")
    private String campaignId;

    @Size(max = 255, message = "Location cannot exceed 255 characters")
    private String location;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0.01")
    private BigDecimal price;

    @Min(value = 0, message = "Click count must be greater than or equal to 0")
    private Long clickCount;

}

