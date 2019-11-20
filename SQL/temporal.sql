DROP TABLE TransaccionChequeTmp;

CREATE TABLE TransaccionChequeTmp (
  referencia INT NOT NULL,
  cuenta INT NOT NULL,
  cheque INT NOT NULL,
  monto FLOAT NOT NULL,
  PRIMARY KEY(referencia)
);
