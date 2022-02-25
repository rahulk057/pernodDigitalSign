package com.example.Line_position.displayEvent;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DisplayEventService {

	@Autowired
	DisplayEventRepository displayeventRepository;
	
	@PostMapping("/insertDisplayEvent")
	public String insertData(@Valid  @RequestBody DisplayEvent displayevent) {
		String message="{\"message\":\"Unsuccessful\"}";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String CurrentDate = sdf.format(date);
		
		System.out.println(displayevent.getPosition());
    		List<DisplayEvent> list=displayeventRepository.getDisplayEvent(displayevent.getLine_id());
		if(list.size()>0) {
	 	
	 	int updateData=(displayeventRepository).updateData(displayevent.getLine_id(),displayevent.getPosition(),displayevent.getEvent_name(),CurrentDate);
	 	
	 	if(updateData>0) {
	 		message="{\"message\":\"Successful\"}";
	 	}
	 	}
		else {
		int insertData=(displayeventRepository).insertData(displayevent.getLine_id(),displayevent.getPosition(),displayevent.getEvent_name(),CurrentDate);
		
		if(insertData>0) {
			message="{\"message\":\"Successful\"}";
		}
		}
		return message;		
	}

	
	@PostMapping("/insertDisplayEventAll")
	public String insertDataAll(@Valid  @RequestBody DisplayEvent displayevent) {
		String message="{\"message\":\"Unsuccessful\"}";
		String[] a= {"2","3","4","5"};
		
		for(String i:a){
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String CurrentDate = sdf.format(date);
			
			System.out.println(displayevent.getPosition());
	    List<DisplayEvent> list=displayeventRepository.getDisplayEvent(i);
			if(list.size()>0) {
		 	
		 	int updateData=(displayeventRepository).updateData(i,displayevent.getPosition(),displayevent.getEvent_name(),CurrentDate);
		 	
		 	if(updateData>0) {
		 		message="{\"message\":\"Successful\"}";
		 	}
		 	}
			else {
			int insertData=(displayeventRepository).insertData(i,displayevent.getPosition(),displayevent.getEvent_name(),CurrentDate);
			
			if(insertData>0) {
				message="{\"message\":\"Successful\"}";
			}
			}
		}
		
		return message;		
	}
	
	@GetMapping("/getDisplayEvent")
	public Map<String,ArrayList<DisplayEvent>>getDisplayEvent(@RequestParam(value="line_id")String line_id){
		
		ArrayList<DisplayEvent> list=(ArrayList<DisplayEvent>)displayeventRepository.getDisplayEvent(line_id);
		
		HashMap<String,ArrayList<DisplayEvent>> hmap=new HashMap<String,ArrayList<DisplayEvent>>();
		hmap.put("displayEvent", list);
		
		return hmap;
		
		
	}
	
}
