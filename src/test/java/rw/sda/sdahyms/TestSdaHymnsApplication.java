package rw.sda.sdahyms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import rw.sda.sdahymns.SdaHymnsApplication;

@TestConfiguration(proxyBeanMethods = false)
public class TestSdaHymnsApplication {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>("postgres:latest");
    }

    public static void main(String[] args) {
        SpringApplication.from(SdaHymnsApplication::main).with(TestSdaHymnsApplication.class).run(args);
    }

}
