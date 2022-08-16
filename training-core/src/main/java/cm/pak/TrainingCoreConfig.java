package cm.pak;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static cm.pak.PakTrainigCons.CM_PAK;

@Configuration
@Import({TrainingDataConfig.class})
@ComponentScan(basePackages = CM_PAK)
@EnableTransactionManagement
public class TrainingCoreConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
