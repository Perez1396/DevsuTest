--Insert script for the person table
INSERT INTO PERSON(id, name, address, gender, age, phone) VALUES(1,'Marco', 'Avenida 123','Masculino', 43, '398712378');
INSERT INTO CLIENTS(contrasena, estado, id) VALUES ('1234', 'True', 1);

INSERT INTO PERSON(id, name, address, gender, age, phone) VALUES(2,'Mariana', 'Edificio esperanza','Femenino', 27, '3335646');
INSERT INTO CLIENTS(contrasena, estado, id) VALUES ('6789', 'False', 2);

INSERT INTO PERSON(id, name, address, gender, age, phone) VALUES(3,'Daniel', 'Wind cross 22','Masculino', 31, '356478678');
INSERT INTO CLIENTS(contrasena, estado, id) VALUES ('2225', 'True', 3);

--Insert script for the account table
INSERT INTO ACCOUNT(numero_cuenta, tipo, saldo_inicial, estado, cliente_id) VALUES (2345, 'Ahorro', 40000, 'True', 1);
INSERT INTO ACCOUNT(numero_cuenta, tipo, saldo_inicial, estado, cliente_id) VALUES (3324, 'Corriente', 2560, 'True', 1);
INSERT INTO ACCOUNT(numero_cuenta, tipo, saldo_inicial, estado, cliente_id) VALUES (1123, 'Ahorro', 1400, 'True', 2);
INSERT INTO ACCOUNT(numero_cuenta, tipo, saldo_inicial, estado, cliente_id) VALUES (6789, 'Ahorro', 670, 'True', 3);
INSERT INTO ACCOUNT(numero_cuenta, tipo, saldo_inicial, estado, cliente_id) VALUES (9987, 'Corriente', 4335, 'True', 3);

--Insert script for movements table

INSERT INTO MOVEMENTS(valor, tipo_movimiento, saldo, fecha, id_cuenta) VALUES (250, 'debito', 2250, '2023-05-14', 1);
INSERT INTO MOVEMENTS(valor, tipo_movimiento, saldo, fecha, id_cuenta) VALUES (400, 'debito', 1850, '2023-05-18', 1);
INSERT INTO MOVEMENTS(valor, tipo_movimiento, saldo, fecha, id_cuenta) VALUES (680, 'debito', 1170, '2023-05-21', 1);
INSERT INTO MOVEMENTS(valor, tipo_movimiento, saldo, fecha, id_cuenta) VALUES (500, 'credito', 1670, '2023-05-23', 1);
INSERT INTO MOVEMENTS(valor, tipo_movimiento, saldo, fecha, id_cuenta) VALUES (250, 'debito', 2250, '2023-05-14', 2);
INSERT INTO MOVEMENTS(valor, tipo_movimiento, saldo, fecha, id_cuenta) VALUES (400, 'debito', 1850, '2023-05-18', 2);
INSERT INTO MOVEMENTS(valor, tipo_movimiento, saldo, fecha, id_cuenta) VALUES (680, 'debito', 1170, '2023-05-21', 3);
INSERT INTO MOVEMENTS(valor, tipo_movimiento, saldo, fecha, id_cuenta) VALUES (500, 'credito', 1670, '2023-05-23', 3);