package com.tiaa.applicationHealthCheck.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/application")
public class DataController {
	 private final DataService dataService;
	 
	 @Autowired
	 public DataController(DataService dataService) {
		 this.dataService = dataService;
	 }
	 
	 
	 /*add Bunch Of data in data table and 
	  * returns status and  list of hashMap
	  * [
	  * 	{"appId":appId1, "appName":appName1},
	  * 	{"appId":appId2, "appName":appName2}
	  * ]	 
	  */
	 @PostMapping("/data")
	 public ResponseEntity<List<HashMap<String, String>>> addAllData(@RequestBody List<Data> datas){
		 
		 for(Data data : datas) {
//			 data.setApplication(new Application(data.getApplication().getAppId(),"","","",""));
		 }
		List<Data> allData = dataService.addAllData(datas);
		List<HashMap<String,String>>response = new ArrayList<>();
		for(Data oneData : allData) {
			System.out.println(oneData);
			HashMap<String,String>temp=new HashMap<String, String>();
			temp.put("appId",Long.toString(oneData.getApplication().getAppId()));
			temp.put("applicationName",oneData.getApplication().getApplicationName());
			response.add(temp);
		}
		 return new ResponseEntity<>(response,HttpStatus.OK);
	 }
	 
	 
	 /*add one by one data in data table and returns status and hashMap
	  * 	{"appId":appId, "appName":appName}
	  */
	 @PostMapping("/onedata")
	 public ResponseEntity<HashMap<String, String>> addData(Data data){
		 HashMap<String, String>response=new HashMap<String, String>();
		 
		 Data newData=dataService.addData(data);
		 response.put("appId",Long.toString(newData.getApplication().getAppId()));
		 response.put("applicationName",newData.getApplication().getApplicationName());
		 return new ResponseEntity<HashMap<String,String>>(response,HttpStatus.OK);
	 }

	//get all data of single application with specified appId
	 @GetMapping("/{appId}/data")
	    public ResponseEntity<List<Data>> getData(@PathVariable Long appId){
	        return new ResponseEntity<>(dataService.getAllData(appId), HttpStatus.OK);
	    }
	 
}
