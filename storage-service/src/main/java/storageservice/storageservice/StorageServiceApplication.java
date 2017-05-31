package storageservice.storageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;

@EnableDiscoveryClient
@SpringBootApplication
public class StorageServiceApplication {

	@Autowired
	HomeOfferRepository homeOfferRepository;

	@Autowired
	OwnerInfoRepository ownerInfoRepository;

	@Component
	class homeOfficeDBInitializer implements CommandLineRunner {
		@Override
		public void run(String... strings) throws Exception {
			homeOfferRepository.save(new HomeOffer("Yuriy", "+3801111111", "Lviv", "Schevchenka 11, 257", 55000, 57, 1));
		}
	}

	@Component
	class ownerDBInitializer implements CommandLineRunner {
		@Override
		public void run(String... strings) throws Exception {
			ownerInfoRepository.save(new OwnerInfo("yuriy@mail.com", "Yuriy", "Tkachenko", "+3801111111",
					"Professional realtor, bla bla"));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(StorageServiceApplication.class, args);
	}
}
