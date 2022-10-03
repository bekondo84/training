package cm.pak.training;

import cm.pak.TrainingCoreConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TrainingCoreConfig.class})
public class TrainingConfig {

}
