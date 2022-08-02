package com.electricityBillingSystem.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.electricityBillingSystem.services.StringPrefixedSequenceIdGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@NoArgsConstructor
@Table(name = "bill")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_seq")
    @GenericGenerator(
        name = "bill_seq", 
        strategy = "com.electricityBillingSystem.services.StringPrefixedSequenceIdGenerator", 
        parameters = {
            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EBB_B_"),
            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@Column(name = "id")
	private String id;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "unit")
	private int unit;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "payment_status")
	private String paymentStatus;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "bill_generation_date")
	private Date billGenerationDate;
	
	@Column(name = "payment_date")
	private Date paymentDate;
}
