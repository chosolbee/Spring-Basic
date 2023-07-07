package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //@Component가 붙은 클래스를 모두 찾아서 자동으로 스프링 빈으로 등록
        // basePackages = "hello.core.member", // 해당 package 내 @Component만 등록하도록
        // basePackageClasses = AutoAppConfig.class, // 내가 속한 패키지 부터 (여기서는 hello.core)
        excludeFilters = @ComponentScan.Filter( // 그중에서 뺄 것들 지정
              // AppConfig는 현재 수동으로 등록하기 때문에 자동으로 등록되면 안된다 (충돌 발생) - 우리의 예제를 잘 유지하기 위함
              type = FilterType.ANNOTATION, classes = Configuration.class
      )
)

public class AutoAppConfig {
//      @Bean(name = "memoryMemberRepository")
//      MemberRepository memberRepository(){
//            return new MemoryMemberRepository();
//      }
}
