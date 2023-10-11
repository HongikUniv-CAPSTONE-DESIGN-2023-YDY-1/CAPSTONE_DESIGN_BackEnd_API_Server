package kr.ac.hongik.dsc2023.ydy.team1.core;


import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.JoinRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.LoginRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.PasswordChangeRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.comment.CommentCreateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.request.comment.CommentUpdateRequest;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.JoinResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.dto.response.comment.CommentResponse;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.CommentService;
import kr.ac.hongik.dsc2023.ydy.team1.core.konbini.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    @Autowired
    EntityManager entityManager;
    @BeforeEach
    public void before(){
        log.info("__________________________________________________________");
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "INSERT INTO `item` (`id`, `img_url`, `name`,`category`) VALUES (1, '6fa1224b6deba767edace189a411798323b38edccc46085c34c7db81c05e7428.jpg', '농심안성탕면125G','FOOD'), (2, 'd57c68df46db9e54ac4cae5ec5ea45a36b626a9d2dca42d37e8204627e1f29a8.jpg', '오뚜기육개장컵110G','FOOD')";
            jdbcTemplate.update(sql);
            sql = "INSERT INTO `promotion_info` (`id`, `brand_id`, `end_date`, `price`, `promotion_id`, `start_date`, `item_id`) " +
                    "VALUES " +
                    "(1, 'CU', '2025-09-09', 1700, 'ONE_PLUS_ONE', '2023-09-04', 1)," +
                    "(2, 'GS25', '2025-09-09', 1000, 'TWO_PLUS_ONE', '2023-09-04', 2)";
            jdbcTemplate.update(sql);
        }catch (Exception e){
            log.error(e.toString());
            log.error("errrrrrrrrrrrrrrrrrr");
        }
    }
    @Test
    @Order(1)
    public void memberServiceTest(){
        memberService.join(JoinRequest.getInstance());
        memberService.login(LoginRequest.getInstance());
        memberService.changePassword(PasswordChangeRequest.getInstance());
        Assertions.assertThrows(RuntimeException.class,() -> {
            memberService.login(LoginRequest.getInstance());
        });
    }
//    @Test
//    @Order(2)
//    public void commentServiceTest(){
//        memberService.join(JoinRequest.getInstance());
//        CommentResponse created = commentService.create(CommentCreateRequest.getInstance(), 2);
//        CommentResponse searched = commentService.readAllByPromotionID(2, created.getPromotionId(), 0, 10)
//                .get().findFirst().orElseThrow();
//        Assertions.assertEquals(created.getCommentId(),searched.getCommentId());
//
//        Assertions.assertThrows(RuntimeException.class,() -> commentService.create(CommentCreateRequest.getInstance(), 2));
//        Assertions.assertThrows(RuntimeException.class, () -> commentService.update(CommentUpdateRequest.getInstance(),3));
//
//        entityManager.clear();//https://juneyr.dev/hibernate-exception-does-not-flush
//
//        commentService.update(CommentUpdateRequest.getInstance(),2);
//        searched = commentService.readAllByUserID(2,0,10).get().findFirst().get();
//        Assertions.assertEquals(CommentUpdateRequest.getInstance().getContent(),searched.getContent());
//
//        CommentResponse searched2 = commentService.readAllByPromotionID(2,1,0,10).get().findFirst().get();
//
//        Assertions.assertEquals(searched.getCommentId(),searched2.getCommentId());
//
//        Assertions.assertThrows(RuntimeException.class,() -> commentService.delete(1,3));
//        commentService.delete(1,2);
//
//        Assertions.assertTrue(commentService.readAllByUserID(2, 0, 10).isEmpty());
//
//    }
}