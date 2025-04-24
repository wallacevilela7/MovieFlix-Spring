CREATE TABLE tb_movie_streaming (
    --Foreign key to movie
    movie_id BIGINT NOT NULL,
    --Foreign key to category
    streaming_id BIGINT NOT NULL,
    CONSTRAINT fk_movie_streaming FOREIGN KEY (movie_id) REFERENCES tb_movie(id),
    CONSTRAINT fk_streaming_movie FOREIGN KEY (streaming_id) REFERENCES tb_streaming(id)
);