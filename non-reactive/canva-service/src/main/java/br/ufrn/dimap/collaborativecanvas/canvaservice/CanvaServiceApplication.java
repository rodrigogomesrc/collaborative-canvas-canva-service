package br.ufrn.dimap.collaborativecanvas.canvaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CanvaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanvaServiceApplication.class, args);
	}

}
