package com.tiaa.applicationHealthCheck.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ApplicationService {
	 private ApplicationRepo applicationRepo;

	    @Autowired
	    public ApplicationService(ApplicationRepo applicationRepo) {
	        this.applicationRepo = applicationRepo;
	    }
	    
	    //Adding applications one by one
	    public Application addApplication(Application application){
	        return applicationRepo.save(application);
	    }
	    
	    //Adding applications in the array form
	    public List<Application> addAllApplication(List<Application> application){	
	    	return applicationRepo.saveAll(application);
	    }
	    
	    //get all application details
	    public List<Application> findAllApplication(){
	        return applicationRepo.findAll();
	    }
}
