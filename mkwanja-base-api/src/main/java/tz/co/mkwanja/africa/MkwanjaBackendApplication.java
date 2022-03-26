package tz.co.mkwanja.africa;

import tz.co.mkwanja.africa.config.file.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableScheduling
@SpringBootApplication
public class MkwanjaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MkwanjaBackendApplication.class, args);
	}

}
