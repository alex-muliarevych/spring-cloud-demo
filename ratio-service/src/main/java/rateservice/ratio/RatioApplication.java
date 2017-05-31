package rateservice.ratio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RatioApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatioApplication.class, args);
	}
}
