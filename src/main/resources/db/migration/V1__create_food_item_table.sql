CREATE TABLE food_item (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    amount INT NOT NULL,
    dueDate DATE NOT NULL
);