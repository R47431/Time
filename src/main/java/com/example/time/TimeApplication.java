package com.example.time;

import com.example.time.temporizador.Time;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimeApplication {

	public static void main(String[] args) {
		Time.digitar();
	}

}
