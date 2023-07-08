package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.model.PaintingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class MessageReceiverService {

    @Autowired
    private CanvasService canvasService;

    @Bean
    public Consumer<PaintingDTO> painting() {
        return paintingDTO -> canvasService.processPainting(paintingDTO).subscribe();
    }


}
