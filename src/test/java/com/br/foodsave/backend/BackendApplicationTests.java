package com.br.foodsave.backend;

import com.br.foodsave.backend.domain.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTests {

	@Autowired
	private WebTestClient webTest;
	@Test
	void contextLoads() {
	}

	@Test
	void createPartnerTest(){
		var newPartnerTest = new Partner("Juninho", "Distribuição");
		webTest
				.post()
				.uri("/partner")
				.bodyValue(newPartnerTest)
				.exchange()
				.expectStatus()
				.isCreated()
				.expectBody()
				.jsonPath("$.partnerId").exists()
				.jsonPath("$.name").isEqualTo(newPartnerTest.getName())
				.jsonPath("$.sector").isEqualTo(newPartnerTest.getSector());
	}

}
