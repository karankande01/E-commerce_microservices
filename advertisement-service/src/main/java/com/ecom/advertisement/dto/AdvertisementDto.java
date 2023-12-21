package com.ecom.advertisement.dto;



import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AdvertisementDto {

    private Long advertisementId;

    
    private String title;

 
    private String description;

   
    private List<String> imageUrl;

    
    private String linkUrl;

   
    private LocalDateTime startDate;

    
    private LocalDateTime endDate;

    
    private Integer priority;

   
    private Boolean isActive;

    private String createdBy;

    private LocalDateTime createdDate;

    private String modifiedBy;

    private LocalDateTime modifiedDate;

    private String targetAudience;

    private String campaignId;

    private String location;

    private BigDecimal price;

    private Long clickCount;


}
