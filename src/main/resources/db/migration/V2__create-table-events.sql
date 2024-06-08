CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id) ON DELETE CASCADE,
    title VARCHAR(255),
    description TEXT,
    event_date TIMESTAMP,
    active BOOLEAN
)