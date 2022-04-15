
CREATE TABLE categoria (
    id bigint NOT NULL,
    nome character varying(20) NOT NULL
);


ALTER TABLE categoria OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 25344)
-- Name: lancamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE lancamento (
    id bigint NOT NULL,
    data_pagamento date,
    data_vencimento date NOT NULL,
    descricao character varying(255),
    observacao character varying(255),
    tipo character varying(255) NOT NULL,
    valor numeric(19,2) NOT NULL,
    categoria_id bigint NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE lancamento OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 25352)
-- Name: permissao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE permissao (
    id bigint NOT NULL,
    descricao character varying(255)
);


ALTER TABLE permissao OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 25357)
-- Name: pessoa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pessoa (
    id bigint NOT NULL,
    ativo boolean NOT NULL,
    bairro character varying(255),
    cep character varying(255),
    cidade character varying(255),
    complemento character varying(255),
    estado character varying(255),
    logradouro character varying(255),
    numero character varying(255),
    nome character varying(255) NOT NULL
);


ALTER TABLE pessoa OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 25333)
-- Name: seq_categoria; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seq_categoria
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_categoria OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 25335)
-- Name: seq_lancamento; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seq_lancamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_lancamento OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 25337)
-- Name: seq_pessoa; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seq_pessoa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_pessoa OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 25365)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE usuario (
    id bigint NOT NULL,
    email character varying(255),
    nome character varying(255),
    senha character varying(255)
);


ALTER TABLE usuario OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 25373)
-- Name: usuario_permissao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE usuario_permissao (
    usuario_id bigint NOT NULL,
    permissao_id bigint NOT NULL
);


ALTER TABLE usuario_permissao OWNER TO postgres;



SELECT pg_catalog.setval('public.seq_categoria', 1, false);


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 201
-- Name: seq_lancamento; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seq_lancamento', 1, false);



SELECT pg_catalog.setval('seq_pessoa', 1, false);


ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- TOC entry 2880 (class 2606 OID 25351)
-- Name: lancamento lancamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lancamento
    ADD CONSTRAINT lancamento_pkey PRIMARY KEY (id);


--
-- TOC entry 2882 (class 2606 OID 25356)
-- Name: permissao permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id);


--
-- TOC entry 2884 (class 2606 OID 25364)
-- Name: pessoa pessoa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT pessoa_pkey PRIMARY KEY (id);


--
-- TOC entry 2886 (class 2606 OID 25372)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 2887 (class 2606 OID 25376)
-- Name: lancamento categoria_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lancamento
    ADD CONSTRAINT categoria_fk FOREIGN KEY (categoria_id) REFERENCES categoria(id);


--
-- TOC entry 2890 (class 2606 OID 25391)
-- Name: usuario_permissao fk5wc2vh351r26qbqt1tc52sh4m; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fk5wc2vh351r26qbqt1tc52sh4m FOREIGN KEY (usuario_id) REFERENCES usuario(id);


--
-- TOC entry 2889 (class 2606 OID 25386)
-- Name: usuario_permissao fktcuagcmypmug2ddh2d5uol8s5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario_permissao
    ADD CONSTRAINT fktcuagcmypmug2ddh2d5uol8s5 FOREIGN KEY (permissao_id) REFERENCES permissao(id);


--
-- TOC entry 2888 (class 2606 OID 25381)
-- Name: lancamento pessoa_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lancamento
    ADD CONSTRAINT pessoa_fk FOREIGN KEY (pessoa_id) REFERENCES pessoa(id);


