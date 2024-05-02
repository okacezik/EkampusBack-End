package myproject.ekampus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class EkampusApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkampusApplication.class, args);
	}

}
