package br.com.toolschallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.toolschallenge"})
public class ToolsChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsChallengeApplication.class, args);
	}

}
