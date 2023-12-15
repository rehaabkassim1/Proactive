package com.etisalat.cea.model;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Alert {
	
	@Id
	private String id;
	private String deviceId;
	private String lineId;
	private Date date;
	private Date lastContact;
	private String event;

}
