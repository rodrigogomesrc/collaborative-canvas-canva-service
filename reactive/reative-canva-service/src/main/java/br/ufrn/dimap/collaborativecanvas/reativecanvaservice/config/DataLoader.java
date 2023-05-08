package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.config;

import br.ufrn.dimap.collaborativecanvas.reativecanvaservice.service.CanvasService;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.core.DatabaseClient;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Configuration
public class DataLoader implements ApplicationRunner {

    private final ConnectionFactory connectionFactory;

    private final CanvasService canvasService;

    @Value("${app.database.drop}")
    private boolean dropTables = false;

    @Value("${app.database.create}")
    private boolean createTables = false;

    @Autowired
    public DataLoader(ConnectionFactory connectionFactory, CanvasService canvasService) {
        this.connectionFactory = connectionFactory;
        this.canvasService = canvasService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String sqlDrop = new ClassPathResource("drop-tables.sql").getFile().getAbsolutePath();
        String contentDrop = Files.lines(Paths.get(sqlDrop)).collect(Collectors.joining("\n"));

        String sql = new ClassPathResource("create-tables.sql").getFile().getAbsolutePath();
        String content = Files.lines(Paths.get(sql)).collect(Collectors.joining("\n"));

        DatabaseClient databaseClient = DatabaseClient.create(connectionFactory);
        if (dropTables) {
            System.out.println("Dropping tables");
            databaseClient.sql(contentDrop).fetch().rowsUpdated().block();
        }

        if (createTables) {
            System.out.println("Creating tables");
            databaseClient.sql(content).fetch().rowsUpdated().block();
            System.out.println("Creating canvas");
            canvasService.createCanvas("canvas1", 1L).subscribe();
            canvasService.createCanvas("canvas2", 2L).subscribe();
            canvasService.createCanvas("canvas3", 3L).subscribe();
        }

    }
}

