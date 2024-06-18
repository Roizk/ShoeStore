package com.example.shoestore.Domain.Model.Coupon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Coupon")
public class Coupon {
    @Id
    private String id;
    private String code;
    private Double discountPercentage;
    private Date expirationDate;
    private Double minimumPurchaseAmount;
    private List<String> applicableProducts;
    private Integer usageLimit;
    private Integer usageLimitPerUser;
    private Boolean isActive;
}
