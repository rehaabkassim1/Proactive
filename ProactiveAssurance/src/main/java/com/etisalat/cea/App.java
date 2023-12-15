package com.etisalat.cea;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.etisalat.cea.model.Alert;
import com.etisalat.cea.model.ProactiveAssurance;
import com.etisalat.cea.repositories.ProactiveAssuranceRepository;
import com.etisalat.cea.utils.DateUtil;

@SpringBootApplication
public class App {
	
	static Logger logger = LoggerFactory.getLogger(App.class);
	
	@Autowired
	public Environment environment;
	
	@Autowired
	public ProactiveAssuranceRepository proactiveAssuranceRepository;

	@Autowired
	public MongoTemplate mt;

	private static String collectionName;
	
	private static String FORMAT = "yyyy-MM-dd'T'HH:mm";

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		InputStream inputStream = App.class.getClassLoader().getResourceAsStream("application.properties");
		prop.load(inputStream);
		logger.info("property loaded");
		collectionName = prop.get("mongo.collection.name") + "_"
				+ new SimpleDateFormat("YYMMdd").format(new Date()).toString();
		logger.info(collectionName);
		SpringApplication.run(App.class, args);

	}

	@PostConstruct
	public void invokeMongoDB() {
		
		String dateFormat=getISODate();
		
		String isoDateFormat = "ISODate("+'"'+dateFormat+'"'+")";
		
		logger.info("isoDateFormat::::"+isoDateFormat);
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("event").is("KeepAliveDown"));
		/*comment date criteria to verify if the code is working*/
		//query.addCriteria(Criteria.where("date").gt(isoDateFormat));
		/*comment date criteria to verify if the code is working*/
		List<Alert> find = mt.find(query, Alert.class, collectionName);
				
		logger.info("find::::"+find);
		
		List<ProactiveAssurance> proactiveAssuranceList = new ArrayList<>();
		
		for (Alert alert : find) {
			logger.info("**********Start***********");
			logger.info("DeviceId::::"+alert.getDeviceId());
			logger.info("Event::::"+alert.getEvent());
			logger.info("Line Id::::"+alert.getLineId());
			logger.info("Date::::"+alert.getDate());
			logger.info("Last Contact::::"+alert.getLastContact());
			logger.info("Id::::"+alert.getId());
			logger.info("**********End***********");
			
			ProactiveAssurance proactiveAssurance = new ProactiveAssurance();
			proactiveAssurance.setDateFromAssia(DateUtil.convertDateToTimestamp(alert.getDate()));
			proactiveAssurance.setLastContact(DateUtil.convertDateToTimestamp(alert.getLastContact()));
			proactiveAssurance.setDeviceId(alert.getDeviceId());
			proactiveAssurance.setLineId(alert.getLineId());
			proactiveAssurance.setEvent(alert.getEvent());
			
			proactiveAssuranceList.add(proactiveAssurance);
		}	
		proactiveAssuranceRepository.saveAll(proactiveAssuranceList);	
	}

	private static String getISODate() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
		Date now = new Date();
		now.setHours(now.getHours()-4);
		now.setMinutes(now.getMinutes()-10);
		String dateFormat = sdf.format(now);
		dateFormat=dateFormat.concat(":00.000+0000");
		return dateFormat;
	}
}
