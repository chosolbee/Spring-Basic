package hello.core.order;

public interface OrderService {
    // 주문 생성 시, 회원 id, 상품명, 상품가격을 파라미터로 넘겨주어야 함
    // (1. 주문 생성) 부분
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
