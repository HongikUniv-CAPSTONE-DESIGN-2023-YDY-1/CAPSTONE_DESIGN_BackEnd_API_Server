package kr.ac.hongik.dsc2023.ydy.team1.core;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemsCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchStrength;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service.ItemSearchService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniItemCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniSearchItemRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.KonbiniItemCreateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CoreApplicationTests {
	@Autowired
	private ItemSearchService<KonbiniSearchItem,KonbiniSearchItemRequestDTO> itemSearchService;
	@Autowired
	private KonbiniItemCreateService<KonbiniItemCreateRequestDTO,ItemsCreateRequestDTO<KonbiniItemCreateRequestDTO>> createService;

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
	@Test
	void tt(){
		var list = itemSearchService.search(KonbiniSearchItemRequestDTO.builder()
				.name("꼬깔콘").build());
		for (KonbiniSearchItem searchItem : list.getSearchItems()) {
			System.out.println(searchItem.getName());
		}
	}
	@Test
	void create(){
		List<KonbiniItemCreateRequestDTO> list = new ArrayList<>();
		KonbiniItemCreateRequestDTO konbiniItemCreateRequestDTO =
				KonbiniItemCreateRequestDTO.builder()
						.brand(KonbiniBrand.GS25)
						.name("aaaa")
						.promotion(KonbiniPromotion.TWO_PLUS_ONE)
						.pricePerUnit(1000)
						.build();
		list.add(konbiniItemCreateRequestDTO);
		list.add(KonbiniItemCreateRequestDTO.builder()
				.name("꼬깔콘")
				.brand(KonbiniBrand.GS25)
				.promotion(KonbiniPromotion.ONE_PLUS_ONE)
				.pricePerUnit(10000)
				.build());

		ItemsCreateRequestDTO<KonbiniItemCreateRequestDTO> dto = ItemsCreateRequestDTO.<KonbiniItemCreateRequestDTO>builder().itemList(list).build();
		createService.create(dto);
		var result = itemSearchService.search(KonbiniSearchItemRequestDTO.builder()
				.name("꼬깔콘").build());
		for (KonbiniSearchItem searchItem : result.getSearchItems()) {
			System.out.println(searchItem.getName());
		}
	}
}