package com.example.Line_position;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LinePositionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinePositionApplication.class, args);
		try {
			Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome http://localhost:8044/login.html"});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
