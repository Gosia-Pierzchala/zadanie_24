INSERT INTO community(name, address) VALUES ('sloneczneOsiedle', 'Kwiatowa 11');
INSERT INTO community(name, address) VALUES ('podTopolami', 'Topolowa 20');
INSERT INTO community(name, address) VALUES ('NadOdra', 'Odrzanska 2');

INSERT INTO flat(number, area, community_id) VALUES (12, 56, 1);
INSERT INTO flat(number, area, community_id) VALUES (4, 100, 1);
INSERT INTO flat(number, area, community_id) VALUES (3, 65, 2);
INSERT INTO flat(number, area, community_id) VALUES (13, 48, 2);
INSERT INTO flat(number, area, community_id) VALUES (2, 50, 2);
INSERT INTO flat(number, area, community_id) VALUES (32, 65, 3);

INSERT INTO occupant(first_name, last_name, gender, flat_id) VALUES ('Jan', 'Kowalski', 'mezczyzna', 1);
INSERT INTO occupant(first_name, last_name, gender, flat_id) VALUES ('Adam', 'Szukiel', 'mezczyzna', 2);
INSERT INTO occupant(first_name, last_name, gender, flat_id) VALUES ('Alina', 'Szukiel', 'kobieta', 2);
INSERT INTO occupant(first_name, last_name, gender, flat_id) VALUES ('Barbara', 'Szukiel', 'kobieta', 2);
INSERT INTO occupant(first_name, last_name, gender, flat_id) VALUES ('Jakub', 'Tobiasz', 'mezczyzna', 3);
INSERT INTO occupant(first_name, last_name, gender, flat_id) VALUES ('Janina', 'Kowalska', 'kobieta', 3);
INSERT INTO occupant(first_name, last_name, gender, flat_id) VALUES ('Jan', 'Nowak', 'mezczyzna', 5);
INSERT INTO occupant(first_name, last_name, gender, flat_id) VALUES ('Anna', 'Pucinska', 'kobieta', 6);