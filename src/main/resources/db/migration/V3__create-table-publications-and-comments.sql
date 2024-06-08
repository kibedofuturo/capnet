CREATE TABLE publications (
    id SERIAL PRIMARY KEY,
    text TEXT,
    publication_date TIMESTAMP,
    active BOOLEAN,
    user_id INT REFERENCES Users(id) ON DELETE CASCADE
);

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    text TEXT,
    comment_date TIMESTAMP,
    active BOOLEAN,
    publication_id INT REFERENCES Publications(id) ON DELETE CASCADE
);