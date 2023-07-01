package hello.core.member;

//구현체 하나만 있을 때는 관례상 interface명 뒤에 Impl이라 많이 사용
public class MemberServiceImpl implements MemberService{

    //가입하고 회원을 찾으려면 MemberRepository가 필요
    private final MemberRepository memberRepository; //인터페이스에만 의존하도록

    //어떤 memberRepository가 들어올지 아직 모름 -> AppConfig에서 정하겠다
    public MemberServiceImpl(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        //join에서 save 호출하면 MemberRepository가 아닌 MemoryMemberRepository의 save가 호출된다 (오버라이드)
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
