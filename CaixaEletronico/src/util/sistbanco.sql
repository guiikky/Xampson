create database if not exists sistBanco;

use sistBanco;
drop table cliente;
drop table conta;
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

select data, operacao, valor from log where conta = 2145987;
delete from log where id = 4;

insert into log (conta, agencia, operacao, valor, data) value(2398468, 9999, 'Saque', 50.00, '20/3/2016');
insert into log (conta, agencia, operacao, valor, data) value(2145987, 9999, 'Transferencia', 100.00, '20/3/2016');

update conta set agencia = 9999, saldo = 1000 where conta = 999;
update conta set agencia = 99, saldo = 1000 where conta = 999;
update cliente set nome = 'Lisa Simpson', tipo = 2 where id = 3;

delete from conta where conta = 999;
delete from cliente where id >= 9;

SELECT agencia, saldo, idCliente FROM conta WHERE conta = 2145987;
SELECT nome, tipo FROM cliente WHERE id = 4;

select last_insert_id();

show tables from sistBanco;

drop database sistBanco;
