CREATE TABLE tb_movie (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    release_date DATE,
    rating DECIMAL(2, 1),
    -- Foreign key to Category
    category_id BIGINT NOT NULL,
    -- Foreign key to Streaming
    streaming_id BIGINT NOT NULL,
    CONSTRAINT fk_movie_category FOREIGN KEY (category_id) REFERENCES tb_category(id),
    CONSTRAINT fk_movie_streaming FOREIGN KEY (streaming_id) REFERENCES tb_streaming(id)
);