create table Editora(
	nomeed varchar(30) not null,
	ided serial primary key,
	unique (nomeed, ided)
);

create table Autor(
	nomeau varchar(70) not null,
	idau serial primary key,
	unique (nomeau, idau)
);

create table Categoria(
	nomeca varchar(30),
	idca serial primary key,
	unique (nomeca, idca)
);

---------------------------

create table Titulo(
	nomet varchar(90),
	autor integer references Autor
	on update cascade
	on delete cascade,
	editora integer references Editora
	on delete cascade
	on update cascade,
	idTitulo serial primary key,
	unique (nomet, autor, editora)
);

create table CategoriaPorTitulo(
	livro integer references Titulo
	on update cascade
	on delete cascade,
	categoria integer references Categoria
	on update cascade
	on delete cascade,
	primary key(livro, categoria)
);

create table ExemplarFisico(
	livro integer references Titulo
	on update cascade
	on delete cascade,
	num_disponiveis integer not null,
	primary key (livro)
);

create table ExemplarOnline(
	livro integer references Titulo
	on update cascade
	on delete cascade,
	link varchar(30)
);

------------

create table Usuario(
	idu serial primary key,
	nome varchar(30) not null unique,
	senha varchar(30) not null,
	email varchar(50) not null unique,
	debito integer not null,
	adm bool not null
);

-- aqui todo dia tem que ser feita a verificação se dataDevolucao > CURRENT_DATE, se isso retorna alguem, poem multa
create table AluguelAtivo(
	livro integer references ExemplarFisico
	on update cascade 
	on delete cascade,
	usuario integer references Usuario
	on update cascade
	on delete cascade,
	dataEmprestimo date not null,
	dataDevolucao date not null
);

--  essa é pra manter o histórico de emprestimos dos usuarios (funcionalidade)
create table AluguelInativo(
	livro integer references ExemplarFisico 
	on update cascade 
	on delete cascade,
	usuario integer references Usuario
	on update cascade
	on delete cascade,
	dataEmprestimo date not null,
	dataDevolucao date not null
);
--

insert into Editora(nomeed) values ('Moderna');
insert into Editora(nomeed) values ('Positivo');
insert into Editora(nomeed) values ('Bookman');
insert into Editora(nomeed) values ('Moderna');
insert into Editora(nomeed) values ('Intrinseca');

insert into Autor(nomeau) values ('Rick Riordan');
insert into Autor(nomeau) values ('Stephenie Meyer');



insert into categoria(nomeca) values ('infanto-juvenil');
insert into categoria(nomeca) values ('ficcao');
insert into categoria(nomeca) values ('aventura');
insert into categoria(nomeca) values ('romance');


												  
insert into Titulo (nomet, autor, editora)  												  
	select 'O Labirinto de Fogo', idau, ided from autor,editora	 where nomeau='Rick Riordan' and nomeed='Intrinseca';
insert into Titulo (nomet, autor, editora)  												  
	select 'O Ladrao de Raios', idau, ided from autor,editora	 where nomeau='Rick Riordan' and nomeed='Intrinseca';
insert into Titulo (nomet, autor, editora)  												  
	select 'O Mar de Monstros', idau, ided from autor,editora	 where nomeau='Rick Riordan' and nomeed='Intrinseca';
insert into Titulo (nomet, autor, editora)  												  
	select 'A Maldicao do Tita', idau, ided from autor,editora	 where nomeau='Rick Riordan' and nomeed='Intrinseca';

insert into Titulo (nomet, autor, editora)  												  
	select 'Crepusculo', idau, ided from autor,editora	 where nomeau='Stephenie Meyer' and nomeed='Intrinseca';
insert into Titulo (nomet, autor, editora)  												  
	select 'Lua nova', idau, ided from autor,editora	 where nomeau='Stephenie Meyer' and nomeed='Intrinseca';
insert into Titulo (nomet, autor, editora)  												  
	select 'Eclipse', idau, ided from autor,editora	 where nomeau='Stephenie Meyer' and nomeed='Intrinseca';
insert into Titulo (nomet, autor, editora)  												  
	select 'Amanhecer', idau, ided from autor,editora	 where nomeau='Stephenie Meyer' and nomeed='Intrinseca';
	
insert into CategoriaPorTitulo(livro, categoria) 
	select idtitulo, idca from Titulo, Categoria where autor='3' and nomeca='infanto-juvenil';
insert into CategoriaPorTitulo(livro, categoria) 
	select idtitulo, idca from Titulo, Categoria where autor='3' and nomeca='aventura';
insert into CategoriaPorTitulo(livro, categoria) 
	select idtitulo, idca from Titulo, Categoria where autor='3' and nomeca='ficcao';
	
insert into CategoriaPorTitulo(livro, categoria) 
	select idtitulo, idca from Titulo, Categoria where autor='4' and nomeca='ficcao';
insert into CategoriaPorTitulo(livro, categoria) 
	select idtitulo, idca from Titulo, Categoria where autor='4' and nomeca='romance';
 			

												  