CREATE TABLE Venues (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        address VARCHAR(255),
                        floor INT,
                        price DOUBLE,
                        capacity INT,
                        city VARCHAR(255) NOT NULL
);

CREATE TABLE Events (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,

                        type VARCHAR(255),

                        start_date TIMESTAMP,
                        end_date TIMESTAMP,

                        description VARCHAR(255),

                        is_active BOOLEAN DEFAULT TRUE,

                        venue_id BIGINT,

                        CONSTRAINT fk_event_venue
                            FOREIGN KEY (venue_id)
                                REFERENCES Venues(id)
);