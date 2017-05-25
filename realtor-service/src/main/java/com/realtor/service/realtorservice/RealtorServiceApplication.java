package com.realtor.service.realtorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.regex.Pattern;

@SpringBootApplication
public class RealtorServiceApplication {

	@Autowired
	HomeOfferRepository homeOfferRepository;

	@Component
	class homeOfficeDataBase implements CommandLineRunner {

		@Override
		public void run(String... strings) throws Exception {
			homeOfferRepository.save(new HomeOffer("Yuriy", "+3801111111", "Lviv, Schevchenka 11, 257", 55000, 57));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(RealtorServiceApplication.class, args);
	}

	@RefreshScope
	@RestController
	class HomeOfferRestController {

		@Value("${rate:100}")
		private String rate;

		@RequestMapping(value = "/maxRate")
		String getMaxRate() {
			return rate;
		}

		@RequestMapping(value = "/create", method = RequestMethod.POST)
		@ResponseBody
		public HomeOffer updateCustomer(@RequestBody HomeOffer homeOffer) {
			if(homeOffer.getPrice() / homeOffer.getSqft() < Double.valueOf(rate))
			homeOfferRepository.save(homeOffer);
			return homeOffer;
		}

		@RequestMapping(value = "/isPhoneUnique", method = RequestMethod.GET)
		public long getLowestRateOffer(@RequestParam("phone") String phone) {
			List<HomeOffer> homeOffers = homeOfferRepository.findAll();
			return homeOffers.stream().filter(e -> Pattern.compile(phone).matcher(e.getPhoneNumber()).find()).count();
		}
	}
}

