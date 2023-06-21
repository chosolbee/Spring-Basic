package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    void createOrder(){
        //id가 1, 이름이 memberA, 등급이 VIP인 회원 한 명 만들어줌
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        //memberService를 통해 위 member객체를 넣어줌
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // VIP의 경우 1000원 할인해주기로 했으므로 동일함 -> test 통과
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
