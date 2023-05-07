CREATE TABLE canvas (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    creator_id BIGINT NOT NULL,
    link VARCHAR(255) NOT NULL UNIQUE,
    qtd_painted_pixels BIGINT NOT NULL
);

CREATE TABLE pixel (
    id SERIAL PRIMARY KEY,
    x INTEGER NOT NULL,
    y INTEGER NOT NULL,
    color VARCHAR(255) NOT NULL,
    canvas_id BIGINT NOT NULL REFERENCES canvas(id)
);

CREATE TABLE history (
    id SERIAL PRIMARY KEY,
    player_id BIGINT NOT NULL,
    pixel_id BIGINT NOT NULL REFERENCES pixel(id),
    canvas_id BIGINT NOT NULL REFERENCES canvas(id)
);
