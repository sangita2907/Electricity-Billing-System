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
@Table(name = "user_request")
public class UserRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_req_seq")
	@GenericGenerator(name = "user_req_seq", strategy = "com.electricityBillingSystem.services.StringPrefixedSequenceIdGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "EBS_req"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "phone_number", unique = true)
	private String phoneNumber;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "address")
	private String address;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "request_date")
	private Date requestDate;

}
