package com.tiaa.applicationHealthCheck.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DataService {
	private DataRepo dataRepo;

    @Autowired
    public DataService(DataRepo dataRepo) {
        this.dataRepo = dataRepo;
    }

    //insert data one by one
    public Data addData(Data data){
        return dataRepo.save(data);
    }
    
    //insert bunch of data in array/list form
    public List<Data> addAllData(List<Data> data){
    	return dataRepo.saveAll(data);
    }
    
    //get all data of single application with specified appId
    public List<Data> getAllData(Long appId){
        List<Data> data = new ArrayList<>();
        dataRepo.findByApplicationAppId(appId).forEach(data::add);
        return data;
    }
}
