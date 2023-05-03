CREATE TABLE Hospital (
 -- Cadastro de dados pessoais do paciente
  idRgCpf int identity primary key, 
  nome varchar(50) not null, 
  telefone varchar(20), 
  nascimento date,
  naturalidade varchar(50), 
  email varchar(50),
  cep varchar(8), 
  numero int not null,
  complemento varchar(50)
)

SELECT * FROM Hospital