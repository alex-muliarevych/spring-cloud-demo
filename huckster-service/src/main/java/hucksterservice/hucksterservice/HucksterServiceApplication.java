package hucksterservice.hucksterservice;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

@EnableDiscoveryClient
@SpringBootApplication
public class HucksterServiceApplication {

	@Autowired
	HucksterRepository hucksterRepository;

	@Component
	class HucksterDataBaseInitializer implements CommandLineRunner {
		public void run(String... strings) throws Exception {
			HucksterServiceApplication.this.hucksterRepository
					.save(new Huckster("yuriy@mail.com", "Yuriy", "0976876869"));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(HucksterServiceApplication.class, args);
	}
}
