package rw.sda.sdahymns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SdaHymnsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SdaHymnsApplication.class, args);
    }

}
