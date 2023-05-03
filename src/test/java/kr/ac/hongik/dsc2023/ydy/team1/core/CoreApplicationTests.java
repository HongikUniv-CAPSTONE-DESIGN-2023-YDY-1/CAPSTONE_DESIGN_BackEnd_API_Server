package kr.ac.hongik.dsc2023.ydy.team1.core;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.impl.SearchStrength;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.konbini.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	@Test
	void searchDTOTest(){
		KonbiniSearchItemRequestDTO konbiniSearchItemRequestDTO = KonbiniSearchItemRequestDTO.builder()
				.name("꼬깔콘")
				.strength(SearchStrength.STRONG).build();
		System.out.println(konbiniSearchItemRequestDTO);
	}
	@Test
	void createItemDTOTest(){
		KonbiniItemCreateRequestDTO dto = KonbiniItemCreateRequestDTO.builder()
				.name("꼬갈콘")
				.brand(KonbiniBrand.CU)
				.promotion(KonbiniPromotion.ONE_PLUS_ONE)
				.pricePerUnit(1)
				.picture(null)
				.build();
		System.out.println(dto);
	}
}