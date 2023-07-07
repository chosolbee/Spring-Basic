package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    //회원 찾기 위함

    // interface에만 의존 -> DIP 원칙
    // 아직 어떤 MemberRepository와 DiscountPolicy가 쓰일지 모름
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 이 코드는 @RequiredArgsConstructor로 대체되었음
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //회원 찾기

        /**
         * orderServie입장에서는 할인에 대해서는 잘 모르겠고,
         * discountPolicy에게 알아서 해달라고 맡기고 반환값만 달라고 하는 것
         *
         * 이제 할인에 대한 수정이 필요하면 order 수정 없이 discountPolicy만 수정하면 된다
         */
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 4. 주문 결과 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

