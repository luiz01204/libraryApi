package br.dev.luizmachado.LibraryApi;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
        SpringApplication builder = new SpringApplication(Application.class);
        builder.setBannerMode(Banner.Mode.OFF);
        builder.run(args);
	}
}
