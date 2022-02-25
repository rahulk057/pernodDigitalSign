package com.example.Line_position.linePosition;

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
public class LinePositionService {
	@Autowired
	LinePositionRepository linepositionRepository;
	
	@PostMapping("/insertLinePosition")
	@ResponseBody
	
	public String insertDeviceRegistration(@Valid @RequestBody LinePosition lineposition) {
		String message="{\"message\":\"Unsuccessful\"}";
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String CurrentDate=sdf.format(date);
		
		int insertData=(linepositionRepository).insertData(lineposition.getLine_no(),lineposition.getPosition(),lineposition.getText_language(),lineposition.getText(),CurrentDate);
		if(insertData>0) {
			 message="{\"message\":\"Successful\"}";
		}
		return message;
	

}
	
	 @GetMapping("/getLinePosition")
	    public Map<String,ArrayList<LinePosition>> getLinePosition(@RequestParam ("line_no")String line_no){
	    	
	    	Date date= new Date();
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
	    	ArrayList<LinePosition> aList=new  ArrayList<LinePosition>();    
	    	

	    	
	    	List<LinePosition>list=	linepositionRepository.getLinePosition(line_no) ;  	
	    	if(list.size()>0) {
	    		
	    		aList.add(list.get(0));
	    	}
	    	HashMap<String,ArrayList<LinePosition>> hmap=new HashMap<String,ArrayList<LinePosition>>();
	    	
	    	hmap.put("lineposition", aList);
			return hmap;
	    
	    
	}
}