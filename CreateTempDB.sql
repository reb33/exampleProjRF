DROP TABLE Tran IF EXISTS;
DROP TABLE Card IF EXISTS;
CREATE TABLE Card(
    id SERIAL NOT NULL PRIMARY KEY,
    number VARCHAR(255)
);
CREATE TABLE Tran(
    id SERIAL NOT NULL PRIMARY KEY,
    tranDate DATE,
    tranDescription VARCHAR(255),
    tranAmount FLOAT,
    tranType INT,
    cardId int,
    FOREIGN KEY (cardId)
            REFERENCES Card(id)
            ON DELETE CASCADE
);

INSERT INTO Card (number) VALUES ('425620******1234');
INSERT INTO Card (number) VALUES ('510069******2345');
INSERT INTO Card (number) VALUES ('510070******3456');

INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('27/03/2017', 'DD/MM/YYYY'),'AZBUKA PAYMENT RU', 800.30,0,1 );
INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('26/03/2017', 'DD/MM/YYYY'),'F-CAFE LLC MOSKVA	RUS', 90.00,0,1 );
INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('25/03/2017', 'DD/MM/YYYY'),'MY SHOP MOSCOW RU', 3321.00,1,1 );
INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('25/06/2017', 'DD/MM/YYYY'),'RONLNE CARD2CARD MOSCOW RU', 210.00,1,1 );

INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('27/03/2017', 'DD/MM/YYYY'),'AZBUKA PAYMENT RU', 800.30,0,2 );
INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('26/03/2017', 'DD/MM/YYYY'),'F-CAFE LLC MOSKVA	RUS', 90.00,0,2 );
INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('25/03/2017', 'DD/MM/YYYY'),'MY SHOP MOSCOW RU', 3321.00,1,2 );
INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('25/06/2017', 'DD/MM/YYYY'),'RONLNE CARD2CARD MOSCOW RU', 210.00,1,2 );

INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('27/03/2017', 'DD/MM/YYYY'),'AZBUKA PAYMENT RU', 800.30,0,3 );
INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('26/03/2017', 'DD/MM/YYYY'),'F-CAFE LLC MOSKVA	RUS', 90.00,0,3 );
INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('25/03/2017', 'DD/MM/YYYY'),'MY SHOP MOSCOW RU', 3321.00,1,3 );
INSERT INTO Tran (tranDate, tranDescription, tranAmount,tranType, cardId)
VALUES (TO_DATE('25/06/2017', 'DD/MM/YYYY'),'RONLNE CARD2CARD MOSCOW RU', 210.00,1,3 );