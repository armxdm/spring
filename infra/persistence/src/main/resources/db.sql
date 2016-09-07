--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.8
-- Dumped by pg_dump version 9.4.8
-- Started on 2016-09-07 08:48:50 PHT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2081 (class 1262 OID 12173)
-- Dependencies: 2080
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 2 (class 3079 OID 11893)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 1 (class 3079 OID 16447)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 2085 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 182 (class 1259 OID 16499)
-- Name: account; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE account (
    id integer NOT NULL,
    username character varying,
    password character varying,
    user_role character varying,
    account_non_expired boolean,
    account_non_locked boolean,
    credentials_non_expired boolean,
    enabled boolean,
    authorities character varying
);


ALTER TABLE account OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16400)
-- Name: contact; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contact (
    id integer NOT NULL,
    type character varying(15),
    person_id integer,
    info character varying
);


ALTER TABLE contact OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16438)
-- Name: contact_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contact_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE contact_id_seq OWNER TO postgres;

--
-- TOC entry 2086 (class 0 OID 0)
-- Dependencies: 178
-- Name: contact_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contact_id_seq OWNED BY contact.id;


--
-- TOC entry 181 (class 1259 OID 16444)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16393)
-- Name: person; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE person (
    first_name character varying(50) NOT NULL,
    middle_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    suffix character varying(5),
    title character varying(5),
    id integer NOT NULL,
    birthday date NOT NULL,
    gwa double precision DEFAULT 0 NOT NULL,
    is_currently_employed boolean NOT NULL,
    date_hired date NOT NULL,
    house_number integer,
    home_street character varying(50),
    home_barangay character varying(50),
    home_city character varying(50),
    home_zip_code integer,
    work_bldg_number integer,
    work_street character varying,
    work_city character varying,
    work_barangay character varying,
    work_zip_code integer
);


ALTER TABLE person OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16440)
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE person_id_seq OWNER TO postgres;

--
-- TOC entry 2087 (class 0 OID 0)
-- Dependencies: 179
-- Name: person_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE person_id_seq OWNED BY person.id;


--
-- TOC entry 177 (class 1259 OID 16427)
-- Name: person_role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE person_role (
    role_id integer NOT NULL,
    person_id integer NOT NULL
);


ALTER TABLE person_role OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16406)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE role (
    id integer NOT NULL,
    role_name character varying(50) NOT NULL
);


ALTER TABLE role OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16442)
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_id_seq OWNER TO postgres;

--
-- TOC entry 2088 (class 0 OID 0)
-- Dependencies: 180
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE role_id_seq OWNED BY role.id;


--
-- TOC entry 183 (class 1259 OID 16512)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_id_seq OWNER TO postgres;

--
-- TOC entry 2074 (class 0 OID 16499)
-- Dependencies: 182
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY account (id, username, password, user_role, account_non_expired, account_non_locked, credentials_non_expired, enabled, authorities) FROM stdin;
12	admin	$2a$10$JcUsVrBiLW7wQC5BRExIheFSNIzk/KnD9jk/lqnXqvMtb9OHRyavO	ADMIN	t	t	t	t	\N
13	user	$2a$10$OpolbupmnVuXfh.aA1bBTuxQW2CMI8vj2T.n8G0YsChrTO4sVh5MW	USER	t	t	t	t	\N
14	abdul	$2a$10$1JxwzxCrhOpjR.a9z5FerOk5avZsNNVHhoroy32gAoJhrPGhBd.Eq	USER	t	t	t	t	\N
15	domado	$2a$10$V0P1XFGeHCiQP7Kv7CauZejOB1N92ujNO8WDw8ME1vqoKJAm6HUEi	ADMIN	t	t	t	t	\N
\.


--
-- TOC entry 2067 (class 0 OID 16400)
-- Dependencies: 175
-- Data for Name: contact; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contact (id, type, person_id, info) FROM stdin;
248	EMAIL_ADDRESS	31	harry.potter@gmail.com
244	EMAIL_ADDRESS	117	sam.domado@gmail.com
261	MOBILE_NUMBER	117	1234
231	MOBILE_NUMBER	\N	1111
246	EMAIL_ADDRESS	28	armdomado@gmail.com
247	MOBILE_NUMBER	28	09155844188
249	EMAIL_ADDRESS	112	lskywalker@yahoo.com
227	EMAIL_ADDRESS	\N	sasa
228	MOBILE_NUMBER	\N	asasa
229	EMAIL_ADDRESS	\N	fsd
230	EMAIL_ADDRESS	\N	fdsf
\.


--
-- TOC entry 2089 (class 0 OID 0)
-- Dependencies: 178
-- Name: contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contact_id_seq', 267, true);


--
-- TOC entry 2090 (class 0 OID 0)
-- Dependencies: 181
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 68, true);


--
-- TOC entry 2066 (class 0 OID 16393)
-- Dependencies: 174
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY person (first_name, middle_name, last_name, suffix, title, id, birthday, gwa, is_currently_employed, date_hired, house_number, home_street, home_barangay, home_city, home_zip_code, work_bldg_number, work_street, work_city, work_barangay, work_zip_code) FROM stdin;
Luke	Asds	Skywalker		Mr.	112	1981-01-20	1.5	t	2010-01-20	23	Buendia Ave.	San Antonio	Makati	2000	43	Onyx	Makati	San Lorenzo	54
Sam	Maamor	Moxcir	Jr.	Mrs.	117	1992-08-30	1.19999999999999996	t	2010-02-11	124	BD	Pembo	Taguig	1234	12	Salcedo St.	Makati	San Antonio	2456
Abdul	Moxcir	Domados		Mr.	28	1995-01-30	1	t	2011-02-01	2407	Opalo	San Andres Bukid	Manila	3000	12	Emerald Ave.	Pasig	Ortigas Center	124
Harry	G	Potter		Mr.	31	1991-07-31	1	f	2012-01-30	12	Pasong Tamo	San Lorenzo	Manila	9856	13	Ruby	Quezon	Diliman	124
\.


--
-- TOC entry 2091 (class 0 OID 0)
-- Dependencies: 179
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('person_id_seq', 124, true);


--
-- TOC entry 2069 (class 0 OID 16427)
-- Dependencies: 177
-- Data for Name: person_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY person_role (role_id, person_id) FROM stdin;
11	28
23	48
11	112
12	31
11	31
17	117
12	117
\.


--
-- TOC entry 2068 (class 0 OID 16406)
-- Dependencies: 176
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY role (id, role_name) FROM stdin;
11	dev
12	admin
17	ba
109	sample3
\.


--
-- TOC entry 2092 (class 0 OID 0)
-- Dependencies: 180
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('role_id_seq', 109, true);


--
-- TOC entry 2093 (class 0 OID 0)
-- Dependencies: 183
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 15, true);


--
-- TOC entry 1949 (class 2606 OID 16421)
-- Name: contact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (id);


--
-- TOC entry 1947 (class 2606 OID 16412)
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 1953 (class 2606 OID 16431)
-- Name: person_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person_role
    ADD CONSTRAINT person_role_pkey PRIMARY KEY (role_id, person_id);


--
-- TOC entry 1951 (class 2606 OID 16414)
-- Name: role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 1955 (class 2606 OID 16515)
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY account
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 1956 (class 2606 OID 16456)
-- Name: contact_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contact
    ADD CONSTRAINT contact_person_id_fkey FOREIGN KEY (person_id) REFERENCES person(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-09-07 08:48:50 PHT

--
-- PostgreSQL database dump complete
--

