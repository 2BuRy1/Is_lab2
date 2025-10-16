package systems.project.configuratons;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class Config {


    @Bean
    public Logger logger() {
        var log = Logger.getLogger(getClass().getName());
        log.setLevel(Level.INFO);
        return log;
    }

}
