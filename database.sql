CREATE DATABASE academia CHARACTER SET latin1;

USE academia;

CREATE TABLE aluno ( 
	id int not null,
	nome varchar(30),
	sobrenome varchar(30),
	sexo char(1),
	esporte varchar(50),
	dataRegistro date,
	primary key(id)
);

CREATE TABLE instrutor (
	id int not null,
	nome varchar(30),
	sobrenome varchar(30),
	area varchar(50),
	sexo char(1),
	primary key(id),
);