DROP TABLE corredores IF EXISTS;
DROP TABLE eventos IF EXISTS;

CREATE TABLE corredores(
    cpf VARCHAR(255), 
    nome VARCHAR(255), 
    genero VARCHAR(255),
    dia_dn int, 
    mes_dn int, 
    ano_dn int, 
    PRIMARY KEY(cpf)
);

CREATE TABLE eventos(
    id int, 
    nome VARCHAR(255), 
    dia int, 
    mes int, 
    ano int, 
    distancia int, 
    horas int, 
    minutos int, 
    segundos int, 
    PRIMARY KEY(id)
);

-- CORREDORES
INSERT INTO corredores(cpf, nome, genero, dia_dn, mes_dn, ano_dn)
VALUES ('0', '10001287', 'Luiz', 'masculino', 22, 5, 1987);

INSERT INTO corredores(cpf, nome, genero, dia_dn, mes_dn, ano_dn)
VALUES ('1', '10001288', 'Tao Ren', 'masculino', 21, 7, 1961);

INSERT INTO corredores(cpf, nome, genero, dia_dn, mes_dn, ano_dn)
VALUES ('2', '10001289', 'Horo Horo', 'masculino', 15, 4, 1982);

INSERT INTO corredores(cpf, nome, genero, dia_dn, mes_dn, ano_dn)
VALUES ('3', '10001290', 'Kororo', 'feminino', 30, 9, 1981);

INSERT INTO corredores(cpf, nome, genero, dia_dn, mes_dn, ano_dn)
VALUES ('4', '10001291', 'Anna', 'feminino', 9, 12, 2000);

-- EVENTOS
INSERT INTO eventos(id, nome, dia, mes, ano, distancia, horas, minutos, segundos)
VALUES('0', 'PUCRS Day Run', 31, 3, 2019, 5, 0, 40, 31);

INSERT INTO eventos(id, nome, dia,mes,ano,distancia,horas,minutos,segundos)
VALUES('1', 'Poa Day Run', 22, 5, 2019, 5, 0, 35, 32);

INSERT INTO eventos(id, nome, dia,mes,ano,distancia,horas,minutos,segundos)
VALUES('2', 'Poa Day Run 2', 22, 5, 2020, 5, 0, 40, 23);

INSERT INTO eventos(id, nome, dia,mes,ano,distancia,horas,minutos,segundos)
VALUES('3', 'Poa Day Run 3', 22, 5, 2021, 5, 0, 47, 59);

INSERT INTO eventos(id, nome, dia,mes,ano,distancia,horas,minutos,segundos)
VALUES('4','Run Day Run', 13, 7, 2019, 5, 0, 32, 40);
