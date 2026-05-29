CREATE TABLE Categories (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE
);

ALTER TABLE Events
    ADD category_id BIGINT;

ALTER TABLE Events
    ADD CONSTRAINT fk_event_category
        FOREIGN KEY (category_id)
            REFERENCES Categories(id);
