mysql -u root -p

CREATE DATABASE clinica CHARACTER SET latin1;

use clinica;

CREATE TABLE pacientes ( 
	id int AUTO_INCREMENT,
	nome varchar(100),
	cpf varchar(20),
	telefone varchar(20),
	endereco varchar(100),
	nascimento date,
	numero_convenio int,
	primary key(id)
);

CREATE TABLE medicos (
	id int AUTO_INCREMENT,
	nome varchar(100),
	crm int,
	area_atuacao varchar(50),
	telefone varchar(20),
	endereco varchar(100),
	data_contratacao date,
	primary key(id)
);