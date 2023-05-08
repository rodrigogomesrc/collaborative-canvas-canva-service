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
        DatabaseClient databaseClient = DatabaseClient.create(connectionFactory);

        if (dropTables) {
            System.out.println("Dropping tables");
            String dropTablesQuery = """

                    DROP TABLE IF EXISTS history;
                    DROP TABLE IF EXISTS pixel;
                    DROP TABLE IF EXISTS canvas;
                    """;
            databaseClient.sql(dropTablesQuery).fetch().rowsUpdated().block();
        }

        if (createTables) {
            System.out.println("Creating tables");
            String createTablesQuery = """
                    CREATE TABLE IF NOT EXISTS canvas (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                    creator_id BIGINT NOT NULL,
                    link VARCHAR(255) NOT NULL UNIQUE,
                    qtd_painted_pixels BIGINT NOT NULL
                        );

                    CREATE TABLE IF NOT EXISTS pixel (
                            id SERIAL PRIMARY KEY,
                            x INTEGER NOT NULL,
                            y INTEGER NOT NULL,
                            color VARCHAR(255) NOT NULL,
                    canvas_id BIGINT NOT NULL REFERENCES canvas(id)
                        );

                    CREATE TABLE IF NOT EXISTS history (
                            id SERIAL PRIMARY KEY,
                            player_id BIGINT NOT NULL,
                            pixel_id BIGINT NOT NULL REFERENCES pixel(id),
                    canvas_id BIGINT NOT NULL REFERENCES canvas(id)
                        );
                    """;
            databaseClient.sql(createTablesQuery).fetch().rowsUpdated().block();
            System.out.println("Creating canvas");
            canvasService.createCanvas("canvas1", 1L).subscribe();
            canvasService.createCanvas("canvas2", 2L).subscribe();
            canvasService.createCanvas("canvas3", 3L).subscribe();
        }
    }
}

