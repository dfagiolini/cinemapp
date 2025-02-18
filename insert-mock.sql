-- Inserimento dati nel cinema
INSERT INTO cinemapp.cinema (indirizzo, nome, telefono)
VALUES 
('Via Roma, 1', 'Cinema Roma', '123456789'),
('Via Milano, 20', 'Cinema Milano', '987654321'),
('Via Napoli, 15', 'Cinema Napoli', '234567890'),
('Via Firenze, 8', 'Cinema Firenze', '345678901'),
('Via Torino, 33', 'Cinema Torino', '456789012');

-- Inserimento dati nei film
INSERT INTO cinemapp.film (durata, copertina, descrizione, genere, regia, titolo)
VALUES 
(120, 'copertina1.jpg', 'Descrizione del Film 1', 'Azione', 'Regista 1', 'Film 1'),
(90, 'copertina2.jpg', 'Descrizione del Film 2', 'Commedia', 'Regista 2', 'Film 2'),
(110, 'copertina3.jpg', 'Descrizione del Film 3', 'Dramma', 'Regista 3', 'Film 3'),
(130, 'copertina4.jpg', 'Descrizione del Film 4', 'Fantascienza', 'Regista 4', 'Film 4'),
(95, 'copertina5.jpg', 'Descrizione del Film 5', 'Horror', 'Regista 5', 'Film 5'),
(105, 'copertina6.jpg', 'Descrizione del Film 6', 'Animazione', 'Regista 6', 'Film 6');

-- Inserimento dati nelle sale
INSERT INTO cinemapp.sala (capacita, cinema_id, numero)
VALUES 
(150, 1, 'Sala 1'),
(100, 1, 'Sala 2'),
(200, 2, 'Sala 3'),
(180, 2, 'Sala 4'),
(220, 3, 'Sala 5'),
(170, 4, 'Sala 6'),
(160, 4, 'Sala 7'),
(250, 5, 'Sala 8');

-- Inserimento dati nelle proiezioni
INSERT INTO cinemapp.proiezione (data_ora_fine, data_ora_inizio, prezzo, film_id, sala_id)
VALUES 
('2025-02-08 20:00:00', '2025-02-08 18:00:00', 10.50, 1, 1),
('2025-02-08 22:00:00', '2025-02-08 20:00:00', 10.50, 1, 1),
('2025-02-08 22:30:00', '2025-02-08 21:00:00', 8.00, 2, 2),
('2025-02-08 23:00:00', '2025-02-08 21:00:00', 12.00, 3, 3),
('2025-02-08 21:30:00', '2025-02-08 19:00:00', 9.50, 4, 4),
('2025-02-08 19:30:00', '2025-02-08 17:00:00', 11.00, 5, 5),
('2025-02-08 18:30:00', '2025-02-08 16:00:00', 7.50, 6, 6),
('2025-02-08 22:00:00', '2025-02-08 20:00:00', 10.00, 1, 7),
('2025-02-08 21:00:00', '2025-02-08 19:00:00', 8.50, 2, 8);

-- Inserimento dati negli utenti
INSERT INTO cinemapp.utente (ruolo, email, nome, password)
VALUES
(1, 'user1@example.com', 'User 1', '$2a$10$Ji2DE.oF9bX43HpeP.2rPu2lrSBVfFyhigV7Mh0KsEO7ysVMsBo8G'),
(1, 'user2@example.com', 'User 2', '$2a$10$PpDe9ik71kVhtPRkyn2GYuu8j2rrTCNwHTMA6RrTN/6lzVEObim1K'),
(1, 'user3@example.com', 'User 3', '$2a$10$UEBaAUiIsvdH30.T.ayONugW7FLHExsdVAIT/bS/kHXMGIuE7bhPu'),
(1, 'user4@example.com', 'User 4', '$2a$10$V6MWHgFBQwyGRN8XWcawoO.UcbOs0yW2nv1JRrSy6IKj/yKyOpooq'),
(1, 'user5@example.com', 'User 5', '$2a$10$S4bNOTZ4MCrHhumc7sTKIesiuKDrbNnkzGWICgpPK.hTDWTWB6DT2'),
(0, 'admin@cinemapp.cinemapp', 'Admin', '$2a$10$0WrHzEFIq01P4MZBGspvZ.xyBgwAaYoKZtUjczENwbBYMFTrl2FkW');

-- Inserimento dati nelle prenotazioni
INSERT INTO cinemapp.prenotazione (data_prenotazione, numero_biglietti, proiezione_id, utente_id)
VALUES 
('2025-02-07', 2, 1, 1),
('2025-02-07', 4, 2, 2),
('2025-02-07', 3, 3, 3),
('2025-02-07', 5, 4, 4),
('2025-02-07', 1, 5, 5);
