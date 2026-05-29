INSERT INTO Categories (name) VALUES
    ('ROCK'),
    ('TECHNOLOGY'),
    ('BUSINESS'),
    ('SPORTS'),
    ('ARTS'),
    ('CULTURE');

INSERT INTO Venues (address, floor, price, capacity, city) VALUES
    ('Centro de Convenciones Bogota', 2, 4500.00, 1000, 'Bogota'),
    ('Teatro Medellin', 1, 3200.00, 800, 'Medellin'),
    ('Arena Cali', 3, 2800.00, 1200, 'Cali'),
    ('Centro Caribe', 2, 2500.00, 900, 'Barranquilla'),
    ('Salon Cartagena', 1, 3000.00, 700, 'Cartagena');

INSERT INTO Events (type, start_date, end_date, description, is_active, venue_id, category_id) VALUES
    ('CONCERT', TIMESTAMP '2025-02-01 19:00:00', TIMESTAMP '2025-02-01 22:00:00', 'Concierto de ROCK', TRUE, 1, 1);

INSERT INTO Events (type, start_date, end_date, description, is_active, venue_id, category_id)
SELECT
    CASE MOD(x, 4)
        WHEN 0 THEN 'CONCERT'
        WHEN 1 THEN 'CONFERENCE'
        WHEN 2 THEN 'PARTY'
        ELSE 'SPORTS'
    END,
    DATEADD('DAY', x, TIMESTAMP '2025-03-01 09:00:00'),
    DATEADD('HOUR', 3, DATEADD('DAY', x, TIMESTAMP '2025-03-01 09:00:00')),
    'Evento ' || x,
    TRUE,
    1 + MOD(x, 5),
    1 + MOD(x, 6)
FROM SYSTEM_RANGE(2, 200) x;
