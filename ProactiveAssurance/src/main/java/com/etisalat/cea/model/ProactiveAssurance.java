package com.etisalat.cea.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity(name = "PROACTIVE_ASSURANCE")
@Table(name = "PROACTIVE_ASSURANCE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProactiveAssurance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigDecimal Id;
	private String deviceId;
	private String lineId;
	private Date dateFromAssia;
	private Date lastContact;
	private String event;

}
