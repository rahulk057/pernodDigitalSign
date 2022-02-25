package com.example.Line_position.login;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

 
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginService {
	
	@Autowired
	LoginRepository loginrepository;

	@PostMapping("/insertData")
	public String insertData(@Valid @RequestBody Login login) {
		String message="{\"message\":\"Unsuccessful\"}"; 
		int insertData=(loginrepository).insertData(login.getUsername(), login.getPassword());
		if(insertData>0) {
			message="{\"message\":\"Successful\"}";
		}
		
		
		return message;
			
}
	@RequestMapping("/getLogin")
	public String getLogindata(@RequestParam(value="username")String username,@RequestParam(value="password")String password){
		String message="{\"message\":\"notLogin\"}";
		ArrayList<Login>list=(ArrayList<Login>)loginrepository.getLogindata(username, password);
		if(list.size()>0) {
			message="{\"message\":\"login\"}";	
		}
		return message;
		
	}
	
}
