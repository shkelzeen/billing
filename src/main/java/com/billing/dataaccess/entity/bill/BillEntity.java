package com.billing.dataaccess.entity.bill;

import com.billing.dataaccess.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

//This entity contains minimum information regarding the billing
@Entity(name = "bill")
@Getter
@Setter
public class BillEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_seq")
    @SequenceGenerator(name = "billing_seq", allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String billingPeriodId;
    private LocalDateTime billingDate;
}
