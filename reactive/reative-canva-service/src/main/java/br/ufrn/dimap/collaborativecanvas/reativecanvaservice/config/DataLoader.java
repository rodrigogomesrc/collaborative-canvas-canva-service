package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.config;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service.CanvasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements ApplicationRunner {

    @Autowired
    CanvasService canvasService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        canvasService.createCanvas("Canvas 1", 1);
        canvasService.createCanvas("Canvas 2", 2);
        canvasService.createCanvas("Canvas 3", 3);
    }
}
