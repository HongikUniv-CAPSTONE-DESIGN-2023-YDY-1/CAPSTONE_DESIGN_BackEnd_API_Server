package kr.ac.hongik.dsc2023.ydy.team1.core;


import kr.ac.hongik.dsc2023.ydy.team1.core.architecture.dto.request.ItemsCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.KonbiniItemCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.comment.CommentCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.comment.CommentResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.repository.KonbiniItemRepository;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.CommentService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.KonbiniItemCreateService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@SpringBootTest
@Transactional
@Slf4j
class CoreApplicationTests {
    @Autowired
    MemberService memberService;
    @Autowired
    CommentService commentService;
    @Autowired
    DataSource dataSource;
    @BeforeEach
    public void before(){
        log.info("__________________________________________________________");
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "INSERT INTO `item` (`id`, `img_url`, `name`) VALUES (1, '6fa1224b6deba767edace189a411798323b38edccc46085c34c7db81c05e7428.jpg', '농심안성탕면125G'), (2, 'd57c68df46db9e54ac4cae5ec5ea45a36b626a9d2dca42d37e8204627e1f29a8.jpg', '오뚜기육개장컵110G')";
            jdbcTemplate.update(sql);
            sql = "INSERT INTO `promotion_info` (`id`, `brand_id`, `end_date`, `price`, `promotion_id`, `start_date`, `item_id`) " +
                    "VALUES " +
                    "(1, 'CU', '2023-09-04', 1700, 'ONE_PLUS_ONE', '2023-09-04', 1)," +
                    "(2, 'GS25', '2023-09-04', 1000, 'TWO_PLUS_ONE', '2023-09-04', 2)";
            jdbcTemplate.update(sql);
        }catch (Exception e){
            log.error("errrrrrrrrrrrrrrrrrr");
        }
    }
    @Test
    public void memberServiceTest(){
        memberService.join(JoinRequest.getInstance());
        memberService.login(LoginRequest.getInstance());
        memberService.changePassword(PasswordChangeRequest.getInstance());
        Assertions.assertThrows(RuntimeException.class,() -> {
            memberService.login(LoginRequest.getInstance());
        });
    }
    @Test
    public void commentServiceTest(){
        memberService.join(JoinRequest.getInstance());
        CommentResponse commentResponse = commentService.create(CommentCreateRequest.getInstance(), 2);
        Page<CommentResponse> commentResponses = commentService.readAllByPromotionID(commentResponse.getPromotionId(), 0, 10);
        commentResponses.get()
                .forEach(c ->log.info(c.getCommentId()+""));
        commentResponse = commentService.create(CommentCreateRequest.getInstance(), 2);
        commentResponses = commentService.readAllByPromotionID(commentResponse.getPromotionId(), 0, 10);
        Assertions.assertEquals(1,commentResponses.getTotalElements());
    }
}