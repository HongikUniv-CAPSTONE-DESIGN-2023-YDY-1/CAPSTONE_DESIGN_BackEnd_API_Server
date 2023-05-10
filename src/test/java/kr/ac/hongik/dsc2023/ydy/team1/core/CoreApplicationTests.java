package kr.ac.hongik.dsc2023.ydy.team1.core;

import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.port.ItemsCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.port.SearchStrength;
import kr.ac.hongik.dsc2023.ydy.team1.core.dto.request.konbini.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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
	@Test
	void createItemsDTOTest(){
		KonbiniItemCreateRequestDTO dto = KonbiniItemCreateRequestDTO.builder()
				.name("꼬갈콘")
				.brand(KonbiniBrand.CU)
				.promotion(KonbiniPromotion.ONE_PLUS_ONE)
				.pricePerUnit(1)
				.picture(null)
				.build();
		List<KonbiniItemCreateRequestDTO> list = new ArrayList<>();
		list.add(dto);
		ItemsCreateRequestDTO<KonbiniItemCreateRequestDTO> requestDTO = ItemsCreateRequestDTO.<KonbiniItemCreateRequestDTO>builder()
				.itemList(list)
				.build();
		System.out.println(requestDTO);
	}
}