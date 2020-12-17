package com.zyc.mrcweb;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MrcwebApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(MrcwebApplication.class, args);
	}
}
