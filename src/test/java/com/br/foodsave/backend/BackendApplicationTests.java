package com.br.foodsave.backend;

import com.br.foodsave.backend.domain.Partner;
import com.br.foodsave.backend.infrastructure.repository.PartnerEntity;
import com.br.foodsave.backend.service.PartnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTests {

	@Autowired
	private WebTestClient webTest;

	@Autowired
	private PartnerService partnerService;

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

	@Test
	void readAllPartnerTest(){
		partnerService.create(new PartnerEntity("Julio", "Distibuição"));
		partnerService.create(new PartnerEntity("Vitória", "Consultora RH"));

		webTest
				.get()
				.uri("/partner")
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(2);

	}

	@Test
	void readByIDPartnerTest(){
		var person = new PartnerEntity("Julio", "Distibuição");
		var person2 = new PartnerEntity("Vitória", "Consultora RH");

		var result = partnerService.create(person);
		var resultTwo = partnerService.create(person2);

		webTest
				.get()
				.uri("/partner/consult?id=" + result.getPartnerId())
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$.partnerId").exists()
				.jsonPath("$.name").isEqualTo(person.getName())
				.jsonPath("$.sector").isEqualTo(person.getSector());

		webTest
				.get()
				.uri("/partner/consult?id=" + resultTwo.getPartnerId())
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$.partnerId").exists()
				.jsonPath("$.name").isEqualTo(person2.getName())
				.jsonPath("$.sector").isEqualTo(person2.getSector());

	}


	@Test
	void deleteByIDPartnerTest() {
		var person = new PartnerEntity("Vitória", "Consultora RH");

		partnerService.create(person);

		webTest
				.delete()
				.uri("/partner?id=1")
				.exchange()
				.expectStatus()
				.isOk();
	}


	// failures

	@Test
	void createPartnerFailureEmptyPayloadTest(){
		var newPartnerTest = new Partner("", "");
		webTest
				.post()
				.uri("/partner")
				.bodyValue(newPartnerTest)
				.exchange()
				.expectStatus()
				.isBadRequest();
	}

	@Test
	void readByIdPartnerNotExistsTest(){
		webTest
				.get()
				.uri("/partner/consult?id=433")
				.exchange()
				.expectStatus()
				.isNotFound();
	}
}
