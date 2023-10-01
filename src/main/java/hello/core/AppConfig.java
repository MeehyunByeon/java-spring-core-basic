package hello.core;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration을 안붙여도 된다.
// But @Configuration을 안 붙이면 싱글톤 패턴이 보장되지 않는다. => MemberRepository 3번 호출
@Configuration
public class AppConfig {
    // AppConfig => 설계 정보에 대한 구성 정보 및 관계가 잘 보여야함.
    // 역할을 세우고 구현이 그 안에 들어가도록~

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    // => 객체가 2개 생성되니까 싱글톤 패턴이 깨지는 것 아닌가요?

    // 예상 호출 순서 => 순서는 조금 달라져도 memberRepository가 3번 호출되어야 함.
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 실제 호출 순서
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    // @Configuration 안했을 때 Bean 직접 호출 문제가 있으면 자동 의존관계 주입으로 해결 가능
//     @Autowired MemberRepository memberRepository;

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    // static 있으면 싱글톤 안됨
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}