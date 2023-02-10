package az.company.kafkatutorialproject.config;

import az.company.kafkatutorialproject.mapper.BookMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public BookMapper bookMapper() {
        return BookMapper.INSTANCE;
    }

}
