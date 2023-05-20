package kr.ac.hongik.dsc2023.ydy.team1.core;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemsCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.SearchStrength;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service.ItemSearchService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniItemCreateRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniSearchItemRequestDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniItemCreateResponseDTO;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniSearchItem;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniPromotion;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.KonbiniItemCreateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class CoreApplicationTests {
	@Value("${item.img-test}")
	String TEST_IMG;
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
		list.getSearchItems().forEach(System.out::println);
	}
	@Test
	void create() throws IOException {
		List<KonbiniItemCreateRequestDTO> list = new ArrayList<>();
		KonbiniItemCreateRequestDTO konbiniItemCreateRequestDTO =
				KonbiniItemCreateRequestDTO.builder()
						.brand(KonbiniBrand.GS25)
						.name("aaaa")
						.promotion(KonbiniPromotion.TWO_PLUS_ONE)
						.pricePerUnit(1000)
						.picture(new MockMultiPartFile())
						.build();
		list.add(konbiniItemCreateRequestDTO);
		list.add(KonbiniItemCreateRequestDTO.builder()
				.name("꼬깔콘")
				.brand(KonbiniBrand.GS25)
				.promotion(KonbiniPromotion.ONE_PLUS_ONE)
				.pricePerUnit(10000)
				.picture(new MockMultiPartFile())
				.build());

		ItemsCreateRequestDTO<KonbiniItemCreateRequestDTO> dto = ItemsCreateRequestDTO.<KonbiniItemCreateRequestDTO>builder().itemList(list).build();
		var result = (KonbiniItemCreateResponseDTO<KonbiniItemCreateRequestDTO>)createService.create(dto);
		var failList = result.getFailList();
		Assertions.assertEquals(failList, Collections.EMPTY_LIST);
	}
}
class MockMultiPartFile implements MultipartFile {

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getOriginalFilename() {
		return "test.jpg";
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public long getSize() {
		return 0;
	}

	@Override
	public byte[] getBytes(){
		return new byte[0];
	}

	@Override
	public InputStream getInputStream(){
		return null;
	}

	@Override
	public void transferTo(File dest){
		System.out.println("저장");
	}
}