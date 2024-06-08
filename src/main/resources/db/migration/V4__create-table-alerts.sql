CREATE TABLE alerts (
    id SERIAL PRIMARY KEY,
    title TEXT,
    description TEXT,
    date TIMESTAMP,
    alert_date TIMESTAMP,
    active BOOLEAN
);
