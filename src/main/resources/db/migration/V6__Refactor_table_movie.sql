ALTER TABLE tb_movie DROP CONSTRAINT fk_movie_category;
ALTER TABLE tb_movie DROP CONSTRAINT fk_movie_streaming;
ALTER TABLE tb_movie DROP COLUMN category_id;
ALTER TABLE tb_movie DROP COLUMN streaming_id;
