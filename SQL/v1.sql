CREATE TABLE chequera (
	id INT NOT NULL,
	estado INT NOT NULL, /* 0:no entregado, 1:entregado*/
	PRIMARY KEY(Cuenta_cuenta,id_cliente_cuenta)
);

CREATE TABLE Cliente_cuenta (
	Cuenta_cuenta INT NOT NULL,
	id_cliente_cuenta INT NOT NULL,
	Cliente_dpi INT NOT NULL,
	PRIMARY KEY(id_cliente_cuenta)
);

CREATE TABLE Cliente (
	dpi INT NOT NULL,
	nombre VARCHAR2(100) NOT NULL,	
	firma BLOB NULL, /* ruta de imagen*/
	foto BLOB NULL, /* ruta de imagen */
	fecha_nacimiento DATE NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado */
	PRIMARY KEY(dpi)
);

CREATE TABLE Cuenta (
	cuenta INT NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado */
	S VARCHAR2(30) NULL,
	R VARCHAR2(30) NULL,
	D VARCHAR2(30) NULL,
	Cantidad FLOAT NOT NULL, /* dinero en la cuenta */
	PRIMARY KEY(cuenta)
);

CREATE TABLE Cheque (
	cheque INT NOT NULL, 
	fecha DATE NOT NULL,
	monto FLOAT NOT NULL,
	estado INT NOT NULL, /* 2: robado, 1:activo, 0:extraviado */
	Cuenta_cuenta INT NOT NULL,
	Lote_lote INT NOT NULL,
	PRIMARY KEY(cheque)
);

CREATE TABLE Lote (
	lote INT NOT NULL,
	fecha DATE NOT NULL,
	de_banco VARCHAR2(30) NOT NULL,
	cantidad FLOAT NOT NULL,
	total FLOAT NOT NULL,
	estado VARCHAR2(30) NOT NULL,
	PRIMARY KEY(lote)
);
CREATE TABLE Transaccion (
	Cuenta_cuenta INT NOT NULL,
	Agencia_id_agencia INT NOT NULL,
	transaccion INT NOT NULL,
	fecha DATE NOT NULL,
	tipo VARCHAR2(30) NOT NULL,
	naturaleza VARCHAR2(30) NOT NULL,
	usuario VARCHAR2(30) NOT NULL,
	terminal VARCHAR2(30) NOT NULL,
	saldo_inicial FLOAT NOT NULL,
	valor FLOAT NOT NULL,
	saldo_final FLOAT NOT NULL,
	autorizacion CHAR(1) NOT NULL,
	rechazo CHAR(1) NOT NULL,
	razon_rechazo VARCHAR2(300) NOT NULL,
	Operador_id_operador INT NOT NULL,
	PRIMARY KEY(Cuenta_cuenta,Agencia_id_agencia,transaccion)
);
CREATE TABLE Agencia (
	id_agencia INT NOT NULL,
	nombre VARCHAR2(100) NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado */
	PRIMARY KEY(id_agencia)
);
CREATE TABLE Operador (
	id_operador INT NOT NULL,
	nombre VARCHAR2(100) NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado*/
	password VARCHAR2(20) NOT NULL,
	Agencia_id_agencia INT NOT NULL,
	Terminal_id_terminal INT NOT NULL,
	Rol_id_rol INT NOT NULL,
	PRIMARY KEY(id_operador)
);
CREATE TABLE Terminal (
	id_terminal INT NOT NULL,
	nombre VARCHAR2(100) NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado */
	PRIMARY KEY(id_terminal)
);
CREATE TABLE Rol (
	id_rol INT NOT NULL,
	nombre VARCHAR2(100) NOT NULL,
	estado INT NOT NULL, /* 1:activo, 0:eliminado */
	PRIMARY KEY(id_rol)
);

CREATE SEQUENCE agencia_seq START WITH 1000;
CREATE SEQUENCE cuenta_seq START WITH 1000;
CREATE SEQUENCE oper_seq START WITH 1000;   

select cuenta_seq.nextval from dual;


CREATE OR REPLACE TRIGGER agencia_seq_trig 
BEFORE INSERT ON agencia 
FOR EACH ROW
BEGIN
  SELECT agencia_seq.NEXTVAL
  INTO   :new.id_agencia
  FROM   dual;
END;
/



CREATE OR REPLACE TRIGGER oper_seq_trig 
BEFORE INSERT ON operador 
FOR EACH ROW
BEGIN
  SELECT oper_seq.NEXTVAL
  INTO   :new.id_operador
  FROM   dual;
END;
/


insert into agencia (nombre,estado) values ('nombre',1);

select * from agencia;
select * from cliente;
select * from operador;

select a.* from cliente a
inner join cliente_cuenta b on a.dpi = b.cliente_dpi;



drop table cliente;
drop table cuenta;
drop table operador;
