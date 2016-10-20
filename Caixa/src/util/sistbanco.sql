create database if not exists sistBanco;

use sistBanco;

create table if not exists cliente (
id   integer     not null auto_increment,
nome varchar(50) not null,
tipo integer     not null,
primary key (id)
);

create table if not exists conta(
conta     integer not null,
agencia   integer not null,
saldo     double  not null,
idCliente integer not null,
primary key (conta),   
foreign key (idCliente) references cliente(id)
);

create table if not exists log(
id       integer     not null auto_increment,
conta    integer     not null,
agencia  integer     not null,
operacao varchar(50) not null,
valor    double      not null,
data     varchar(50) not null,
primary key(id)
);

create table if not exists debitoAutomatico(
id         integer     not null auto_increment,
operadora  integer     not null,
consumidor integer     not null,
data       varchar(50) not null,
valor      double      not null,
conta      integer     not null,
primary key(id)
);

insert into debitoAutomatico (operador, consumidor, data, valor, conta ) values ( 12, 32, '10/14/2016', 251, 2145987);
drop table debitoAutomatico;

insert into cliente values
(1, 'Han Solo', 01),
(2, 'Peter Parker', 01),
(3, 'Lisa Simpson', 02),
(4, 'Walter White', 01);
 
insert into conta values
(2395784, 9999, 500.00, 1),
(2385998, 9999, 1200.00, 1),
(2587415, 9999, 50000.00, 3),
(2145987, 9999, 737000.00, 4),
(2398468, 9999, 20.00, 2); 

select * from cliente;
select * from conta;
select * from log;
select * from debitoAutomatico;

truncate table conta;
truncate table log;
truncate table debitoAutomatico;

show tables from sistBanco;

drop database sistBanco;
