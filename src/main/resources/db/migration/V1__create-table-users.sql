CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(11),
    ra VARCHAR(6),
    is_graduated BOOLEAN,
    email VARCHAR(255),
    password VARCHAR(255),
    course VARCHAR(255),
    active BOOLEAN
);