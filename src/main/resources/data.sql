-- Sample flight records for development and testing
-- Format: origin, destination, date, airline, price, departure_time, arrival_time

INSERT INTO flight (origin, destination, date, airline, price, departure_time, arrival_time) VALUES
-- New York to London
('New York', 'London', '2025-05-01', 'British Airways', 500, '07:00:00', '19:30:00'),
('New York', 'London', '2025-05-01', 'Delta Airlines', 450, '08:00:00', '20:45:00'),

-- New York to Paris
('New York', 'Paris', '2025-05-01', 'Air France', 480, '09:15:00', '21:55:00'),

-- Los Angeles to Tokyo
('Los Angeles', 'Tokyo', '2025-05-02', 'Japan Airlines', 900, '07:00:00', '19:30:00'),
('Los Angeles', 'Tokyo', '2025-05-02', 'Delta Airlines', 850, '08:00:00', '20:45:00'),

-- Chicago to Toronto
('Chicago', 'Toronto', '2025-05-03', 'Air Canada', 300, '09:15:00', '21:55:00'),
('Chicago', 'Toronto', '2025-05-03', 'United Airlines', 290, '10:15:00', '22:55:00'),

-- Miami to Mexico City
('Miami', 'Mexico City', '2025-05-04', 'Aeroméxico', 400, '09:15:00', '21:55:00');
