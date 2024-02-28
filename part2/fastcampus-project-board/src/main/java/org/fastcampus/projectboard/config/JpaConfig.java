package org.fastcampus.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class JpaConfig {

    // 엔티티가 생성되고, 변경되는 그 시점을 감지하여 생성시각, 수정시각, 생성한 사람, 수정한 사람을 기록함
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("uno"); // TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때, 수정하자
    }

}
