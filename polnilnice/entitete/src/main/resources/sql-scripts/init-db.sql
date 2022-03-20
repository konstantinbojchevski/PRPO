INSERT INTO users.public.user (firstname, lastname, username) VALUES ('Petra', 'Kos', 'petrakos');
INSERT INTO users.public.user (firstname, lastname, username) VALUES ('Miha', 'Novak', 'mihanovak');

INSERT INTO users.public.owner (firstname_owner, lastname_owner, username_owner) VALUES ('Mojca', 'Pogačar', 'mojcap');
INSERT INTO users.public.owner (firstname_owner, lastname_owner, username_owner) VALUES ('Vid', 'Dolenec', 'viddolenec');

INSERT INTO users.public.charger (name, specifications, location, price, opening, closing, id_owner) VALUES ('Zivalski vrt', 'Rapid','Ljubljana', '80', '7:0:0','19:30:0', 1);
INSERT INTO users.public.charger (name, specifications, location, price, opening, closing, id_owner) VALUES ('Gospodarsko rastavišče', 'Slow','Ljubljana', '30', '6:0:0','22:30:0', 2);

INSERT INTO users.public.reservation (id_charger, id_user, from_time, to_time) VALUES (2,1, '9:0:0', '11:0:0');
INSERT INTO users.public.reservation (id_charger, id_user, from_time, to_time) VALUES (1,2, '15:0:0', '18:30:0');

