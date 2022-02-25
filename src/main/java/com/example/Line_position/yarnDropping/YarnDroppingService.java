package com.example.Line_position.yarnDropping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class YarnDroppingService {

	@Autowired
	YarnDroppingRepository yarndroppingRepository;
	
	@PostMapping("/insertYarn")
	@ResponseBody
	public String insertData(@Valid @RequestBody YarnDropping yarndropping) {
	 	String message="{\"message\":\"Unsuccessful\"}";
	 	Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String CurrentDate = sdf.format(date);
	 	
	 	
	 	List<YarnDropping> list=yarndroppingRepository.getYarnData(yarndropping.getLine_id());
	 	
		if(list.size()>0) {
	 	
	 	int updateData=(yarndroppingRepository).updateData(yarndropping.getLine_id(),yarndropping.getCurrent_position(),CurrentDate,yarndropping.getTime());
	 	
	 	if(updateData>0) {
	 		message="{\"message\":\"Successful\"}";
	 	}
	 	}
		else {
	 	
		int insertData=(yarndroppingRepository).insertData(yarndropping.getLine_id(),yarndropping.getCurrent_position(),CurrentDate,yarndropping.getTime());
		if(insertData>0) {
			 message="{\"message\":\"Successful\"}";
		}
	 	}
		return message;
		
	}
	
	@PostMapping("/insertYarnAll")
	@ResponseBody
	public String insertDataAll(@Valid @RequestBody YarnDropping yarndropping) {
	 	String message="{\"message\":\"Unsuccessful\"}";
	 	Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String CurrentDate = sdf.format(date);
		
		String[] a= {"1","5","2","3","4"};
	 	
	 	for (String i : a) {
			
	 		List<YarnDropping> list=yarndroppingRepository.getYarnData(i);
		 	
			if(list.size()>0) {
		 	
		 	int updateData=(yarndroppingRepository).updateData(i,yarndropping.getCurrent_position(),CurrentDate,yarndropping.getTime());
		 	
		 	if(updateData>0) {
		 		message="{\"message\":\"Successful\"}";
		 	}
		 	}
			else {
		 	
			int insertData=(yarndroppingRepository).insertData(i,yarndropping.getCurrent_position(),CurrentDate,yarndropping.getTime());
			if(insertData>0) {
				 message="{\"message\":\"Successful\"}";
			}
		 	}
		}
		return message;
		
	}
	
	@GetMapping("/getYarnData")
	
	public Map<String,ArrayList<YarnDropping>>GetYarnData(@RequestParam(value="line_id")String line_id){
		
	 	Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String CurrentDate = sdf.format(date);

		ArrayList<YarnDropping> list=(ArrayList<YarnDropping>)yarndroppingRepository.getYarnData(line_id);
		for (YarnDropping yarnDropping : list) {
			yarnDropping.setDate(CurrentDate);
		}
		
		HashMap<String,ArrayList<YarnDropping>> hmap=new HashMap<String,ArrayList<YarnDropping>>();
		hmap.put("yarn", list);
		
		return hmap;
	}

	
}
