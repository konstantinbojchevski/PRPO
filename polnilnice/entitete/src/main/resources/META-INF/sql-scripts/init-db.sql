INSERT INTO user (id_user, firstname, lastname, username) VALUES (1,'Petra', 'Kos', 'petrakos');
INSERT INTO user (id_user, firstname, lastname, username) VALUES (2,'Miha', 'Novak', 'mihanovak');

INSERT INTO owner (id_owner, firstname_owner, lastname_owner, username_owner) VALUES (1,'Mojca', 'Pogačar', 'mojcap');
INSERT INTO owner (id_owner, firstname_owner, lastname_owner, username_owner) VALUES (2,'Vid', 'Dolenec', 'viddolenec');

INSERT INTO charger (id_charger, name, specifications, location, price, opening, closing, id_owner) VALUES (1, 'Zivalski vrt', 'Rapid','Ljubljana', '80', new Time(7,30,0),new Time(19,30,0), 1);
INSERT INTO charger (id_charger, name, specifications, location, price, opening, closing, id_owner) VALUES (2, 'Gospodarsko rastavišče', 'Slow','Ljubljana', '30', new Time(6,0,0),new Time(22,30,0), 2);

INSERT INTO reservation (id_reservation, id_charger, id_user, from, to) VALUES (1,2,1, new Time(9,0,0), new Time(11,0,0));
INSERT INTO reservation (id_reservation, id_charger, id_user, from, to) VALUES (2,1,2, new Time(15,0,0), new Time(18,30,0));

