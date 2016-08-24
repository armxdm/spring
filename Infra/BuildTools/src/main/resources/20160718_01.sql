--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.8
-- Dumped by pg_dump version 9.4.8
-- Started on 2016-07-18 10:06:27 PHT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2071 (class 1262 OID 12173)
-- Dependencies: 2070
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 2 (class 3079 OID 11893)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2074 (class 0 OID 0)
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
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 175 (class 1259 OID 16400)
-- Name: contact; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contact (
    id integer NOT NULL,
    mobile_number character varying(15),
    landline_number character varying(15),
    email_address character varying(50),
    person_id integer
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
-- TOC entry 2076 (class 0 OID 0)
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
    street character varying(50),
    barangay character varying(50),
    city character varying(50),
    zip_code integer
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
-- TOC entry 2077 (class 0 OID 0)
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
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 180
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE role_id_seq OWNED BY role.id;


--
-- TOC entry 2059 (class 0 OID 16400)
-- Dependencies: 175
-- Data for Name: contact; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contact (id, mobile_number, landline_number, email_address, person_id) FROM stdin;
21	sfsd	ffsdf	fsdfsd	12
26	adsa	dsada	dsa	12
14	dad	fdfsrfs	armdomd	12
29	rerwerw	rwerwe	rwerwrw	28
32	231232131312	4354654	hp@gmail.com	31
\.


--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 178
-- Name: contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contact_id_seq', 1, false);


--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 181
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 32, true);


--
-- TOC entry 2058 (class 0 OID 16393)
-- Dependencies: 174
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY person (first_name, middle_name, last_name, suffix, title, id, birthday, gwa, is_currently_employed, date_hired, house_number, street, barangay, city, zip_code) FROM stdin;
Moxcir	Abdul	Domado		Mr.	12	1993-01-20	2	f	1332-02-03	2407	Opalo	San Andres Bukid	Manila	2321
Abdul	Rahman	Domado		Mr.	28	1993-01-30	1	f	2222-02-01	0	San Andres	Bdassdsa	dasda	12
Harry	G	Potter		Mr.	31	1991-07-31	1.5	f	2012-01-30	12	Assa	Sfdg	Efdsf	9856
\.


--
-- TOC entry 2081 (class 0 OID 0)
-- Dependencies: 179
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('person_id_seq', 1, false);


--
-- TOC entry 2061 (class 0 OID 16427)
-- Dependencies: 177
-- Data for Name: person_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY person_role (role_id, person_id) FROM stdin;
11	12
11	28
17	31
\.


--
-- TOC entry 2060 (class 0 OID 16406)
-- Dependencies: 176
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY role (id, role_name) FROM stdin;
23	admin
27	qa
11	dev
17	ba
\.


--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 180
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('role_id_seq', 1, false);


--
-- TOC entry 1941 (class 2606 OID 16421)
-- Name: contact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (id);


--
-- TOC entry 1939 (class 2606 OID 16412)
-- Name: person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- TOC entry 1945 (class 2606 OID 16431)
-- Name: person_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY person_role
    ADD CONSTRAINT person_role_pkey PRIMARY KEY (role_id, person_id);


--
-- TOC entry 1943 (class 2606 OID 16414)
-- Name: role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 1946 (class 2606 OID 16456)
-- Name: contact_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contact
    ADD CONSTRAINT contact_person_id_fkey FOREIGN KEY (person_id) REFERENCES person(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1947 (class 2606 OID 16481)
-- Name: person_role_person_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person_role
    ADD CONSTRAINT person_role_person_id_fkey FOREIGN KEY (person_id) REFERENCES person(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1948 (class 2606 OID 16491)
-- Name: person_role_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY person_role
    ADD CONSTRAINT person_role_role_id_fkey FOREIGN KEY (role_id) REFERENCES role(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-07-18 10:06:28 PHT

--
-- PostgreSQL database dump complete
--

