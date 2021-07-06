package com.tiaa.applicationHealthCheck.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/application")
public class ApplicationController {
	@Autowired
    private final ApplicationService applicationService;
	
	 public ApplicationController(ApplicationService applicationService) {
		 this.applicationService = applicationService;
	 }
	 
	//registering new Application one by one and returning status and a map: {"appId":appId,"appName":appName}
	 @PostMapping("/new")
	 public ResponseEntity<HashMap<String,String>> addApplication(@RequestBody Application application){
		 Application newApplication =applicationService.addApplication(application);
		 HashMap<String,String> response = new HashMap<String,String>();
	     response.put("appId",Long.toString(newApplication.getAppId()));
	     response.put("applicationName",newApplication.getApplicationName());
	     return new ResponseEntity<>(response, HttpStatus.OK);
	 }
	 
	    
	 /*Registering bunch of unregistered applications and returning list of map, having key value pair 
	  * [
	  * 	{"appId":appId1, "appName":appName1},
	  * 	{"appId":appId2, "appName":appName2}
	  * ]
	  */
	 @PostMapping("/allNew")
	 public ResponseEntity<List<HashMap<String, String>>> addAllApplication(@RequestBody List<Application> application){
		List<Application> allApplications = applicationService.addAllApplication(application);
		List<HashMap<String,String>>response = new ArrayList<>();
		
		for(Application app : allApplications) {
			HashMap<String,String>temp=new HashMap<String, String>();
			//temp.put(app.getAppId(),app.getApplicationName());
			temp.put("appId",Long.toString(app.getAppId()));
			temp.put("applicationName",app.getApplicationName());
			response.add(temp);
		}
		 return new ResponseEntity<>(response,HttpStatus.OK);
	 }
	 
	 
	 //fetching all registered application details in the form of list
	    @GetMapping("/getApplicationNames")
	    public ResponseEntity<List<Application>> getAllApplications() {
	        List<Application> applications = applicationService.findAllApplication();
	        return new ResponseEntity<>(applications, HttpStatus.OK);
	    }
	
	 

}
