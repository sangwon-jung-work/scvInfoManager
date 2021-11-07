package net.ddns.scvstorage.scvInfoManager;

import java.util.Arrays;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScvInfoManagerApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ScvInfoManagerApplication.class, args);
		SpringApplication application = new SpringApplication(ScvInfoManagerApplication.class);

		application.setBannerMode(Banner.Mode.OFF);

		// check system java args
		//System.out.println("application args:" + Arrays.toString(args));

		application.run(args);
	}

}
