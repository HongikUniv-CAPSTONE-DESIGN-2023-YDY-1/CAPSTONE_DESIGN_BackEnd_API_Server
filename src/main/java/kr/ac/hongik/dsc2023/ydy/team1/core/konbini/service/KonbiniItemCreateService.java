package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service;

import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemsCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.response.ItemCreateResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.service.ItemCreateService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.KonbiniItemCreateResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.Item;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.entity.PromotionInfo;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.model.KonbiniBrand;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniItemRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniPromotionInfoRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.util.SHA256;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KonbiniItemCreateService<E extends KonbiniItemCreateRequest, T extends ItemsCreateRequest<E>> implements ItemCreateService<E, T> {
    private final KonbiniPromotionInfoRepository promotionInfoRepository;
    private final KonbiniItemRepository itemRepository;
    @Value("${item.img-path}")
    private String IMG_PATH;

    public KonbiniItemCreateService(KonbiniPromotionInfoRepository promotionInfoRepository, KonbiniItemRepository itemRepository) {
        this.promotionInfoRepository = promotionInfoRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemCreateResponse<E> create(T requestDTO) {
        List<E> lists = requestDTO.getItemList();
        List<PromotionInfo> promotionInfos = new ArrayList<>();
        List<E> failList = new ArrayList<>();
        for (E createDto : lists) {
            String itemName = createDto.getName();
            KonbiniBrand brand = createDto.getBrand();
            Optional<PromotionInfo> searchResult = promotionInfoRepository.findByItem_NameAndBrand(itemName,brand);
            PromotionInfo promotionInfo;
            if (searchResult.isEmpty()){ // 이 상품이 이 브랜드에서 처음 행사 진행
                String imgName = makeImageName(createDto) + "." + getFileExtension(createDto.getPicture());
                Item item = itemRepository.findByName(itemName).orElse(new Item(itemName,imgName)); // 시스템에 상품이 등록되어잇지 않으면 상품 생성.
                if(!saveImage(createDto,failList)) continue;
                promotionInfo = createDto.toPromotionInfo(item);
            }else{
                promotionInfo = searchResult.get();
                promotionInfo.update(createDto);
            }
            promotionInfos.add(promotionInfo);
        }
        promotionInfoRepository.saveAll(promotionInfos);
        return new KonbiniItemCreateResponse<E>(failList);
    }
    private String makeImageName(E createDto){
        String name = createDto.getName();
        return SHA256.hash(name);
    }
    private boolean saveImage(E createDto,List<E> failList){
        try {
            MultipartFile imgFile = createDto.getPicture();
            String imgName = makeImageName(createDto);
            File dest = new File(IMG_PATH + File.separator + imgName + "." + getFileExtension(imgFile));
            imgFile.transferTo(dest);
        }catch (Exception e){
            failList.add(createDto);
            return false;
        }
        return true;
    }
    private static String getFileExtension(MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();
        if(!fileName.contains(".")){
            return "jpeg";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
