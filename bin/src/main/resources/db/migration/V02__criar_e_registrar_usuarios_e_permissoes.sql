
INSERT INTO usuario (id, nome, email, senha) values (1, 'Administrador', 'admin@financeiro.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (id, nome, email, senha) values (2, 'Maria Silva', 'maria@financeiro.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');
INSERT INTO usuario (id, nome, email, senha) values (3, 'Rafael Dev', 'rafael-souza4@outlook.com', '$2a$10$rcaM5lDak9epPpNVzqqpZO1R3dJVWCZ3HJu4F24F/tK.6DAnPp18G');


INSERT INTO permissao (id, descricao) values (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (id, descricao) values (2, 'ROLE_PESQUISAR_CATEGORIA');
INSERT INTO permissao (id, descricao) values (3, 'ROLE_REMOVER_CATEGORIA');
INSERT INTO permissao (id, descricao) values (4, 'ROLE_ATUALIZAR_CATEGORIA');


INSERT INTO permissao (id, descricao) values (5, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (id, descricao) values (6, 'ROLE_REMOVER_PESSOA');
INSERT INTO permissao (id, descricao) values (7, 'ROLE_PESQUISAR_PESSOA');
INSERT INTO permissao (id, descricao) values (8, 'ROLE_ATUALIZAR_PESSOA');

INSERT INTO permissao (id, descricao) values (9, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (id, descricao) values (10, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao (id, descricao) values (11, 'ROLE_PESQUISAR_LANCAMENTO');
INSERT INTO permissao (id, descricao) values (12, 'ROLE_DELETAR_LANCAMENTO');
INSERT INTO permissao (id, descricao) values (13, 'ROLE_ATUALIZAR_LANCAMENTO');



-- admin
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 2);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 7);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 9);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (1, 11);

-- maria
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 2);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 7);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 9);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (2, 11);

--desenvolvedor
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 1);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 2);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 3);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 4);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 5);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 6);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 7);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 8);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 9);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 10);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 11);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 12);
INSERT INTO usuario_permissao (usuario_id, permissao_id) values (3, 13);

