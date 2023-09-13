package kr.ac.hongik.dsc2023.ydy.team1.core.konbini.controller;

import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.PreItemSearchResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.PreItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PreItemService preItemService;
    private final int SIZE_PER_PAGE = 9;
    @GetMapping("")
    public String getMainPage(Model model, int page){
        Page<PreItemSearchResponse> all = preItemService.getAll(page-1, SIZE_PER_PAGE);
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
}
