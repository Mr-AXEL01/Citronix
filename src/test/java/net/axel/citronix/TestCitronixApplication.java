package net.axel.citronix;

import org.springframework.boot.SpringApplication;

public class TestCitronixApplication {

    public static void main(String[] args) {
        SpringApplication.from(CitronixApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
