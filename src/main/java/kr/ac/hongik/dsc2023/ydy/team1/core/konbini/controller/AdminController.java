package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.ItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.PreItemSearchResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.PreItemService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PreItemService preItemService;
    private final PromotionService promotionService;
    private final int SIZE_PER_PAGE = 9;
    @GetMapping("")
    public String getMainPage(Model model, int page){
        Page<PreItemSearchResponse> all = preItemService.getAll(page-1, SIZE_PER_PAGE);
        return makeGrid(model, all);
    }
    private String makeGrid(Model model, Page<PreItemSearchResponse> all) {
        List<PreItemSearchResponse> allContent = all.getContent();
        List<List<PreItemSearchResponse>> gridMap = new ArrayList<>(IntStream.range(0, allContent.size())
                .boxed()
                .collect(Collectors.groupingBy(
                        i -> i / 3,
                        Collectors.mapping(allContent::get, Collectors.toList())
                ))
                .values());
        model.addAttribute("map",gridMap);
        return "admin";
    }

    @PostMapping("/create")
    public String createPromotion(HttpServletRequest request, Model model){
        ItemCreateRequest itemCreateRequest = ItemCreateRequest.builder()
                .promotion(request.getParameter("promotion"))
                .brand(request.getParameter("brand"))
                .name(request.getParameter("name"))
                .preId(Long.parseLong(request.getParameter("preId")))
                .imgName(request.getParameter("imgName"))
                .endDate(LocalDate.parse(request.getParameter("endDate"),DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .startDate(LocalDate.parse(request.getParameter("startDate"),DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .pricePerUnit(Integer.parseInt(request.getParameter("pricePerUnit"))).build();
        promotionService.create(itemCreateRequest);
        Page<PreItemSearchResponse> all = preItemService.getAll(0, SIZE_PER_PAGE);
        return makeGrid(model, all);
    }
}
