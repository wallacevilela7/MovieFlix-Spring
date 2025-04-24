CREATE TABLE tb_movie_category (
    --Foreign key to movie
    movie_id BIGINT NOT NULL,
    -- Foreign keu to category
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_movie_category FOREIGN KEY (movie_id) REFERENCES tb_movie(id),
    CONSTRAINT fk_category_movie FOREIGN KEY (category_id) REFERENCES tb_category(id)
);