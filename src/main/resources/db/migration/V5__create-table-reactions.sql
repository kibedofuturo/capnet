CREATE TABLE reactions (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255),
    publication_id INT REFERENCES Publications(id) ON DELETE CASCADE
);
