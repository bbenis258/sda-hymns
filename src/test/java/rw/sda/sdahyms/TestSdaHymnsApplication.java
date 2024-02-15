package rw.sda.sdahyms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import rw.sda.sdahymns.SdaHymnsApplication;

@TestConfiguration(proxyBeanMethods = false)
public class TestSdaHymnsApplication {

    public static void main(String[] args) {
        SpringApplication.from(SdaHymnsApplication::main).with(TestSdaHymnsApplication.class).run(args);
    }

}
