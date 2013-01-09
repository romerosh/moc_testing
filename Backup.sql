--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.6
-- Dumped by pg_dump version 9.1.7
-- Started on 2013-01-09 23:10:58 EET

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 170 (class 3079 OID 11681)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1947 (class 0 OID 0)
-- Dependencies: 170
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 163 (class 1259 OID 28477)
-- Dependencies: 6
-- Name: group_students; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE group_students (
    stud_id bigint NOT NULL,
    group_id bigint
);


ALTER TABLE public.group_students OWNER TO postgres;

--
-- TOC entry 162 (class 1259 OID 28470)
-- Dependencies: 6
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE groups (
    id bigint NOT NULL,
    name text
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- TOC entry 161 (class 1259 OID 28468)
-- Dependencies: 162 6
-- Name: groups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.groups_id_seq OWNER TO postgres;

--
-- TOC entry 1948 (class 0 OID 0)
-- Dependencies: 161
-- Name: groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE groups_id_seq OWNED BY groups.id;


--
-- TOC entry 165 (class 1259 OID 28482)
-- Dependencies: 6
-- Name: marks; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE marks (
    id bigint NOT NULL,
    mark bigint,
    student_id bigint,
    subject_id bigint
);


ALTER TABLE public.marks OWNER TO postgres;

--
-- TOC entry 164 (class 1259 OID 28480)
-- Dependencies: 165 6
-- Name: marks_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE marks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.marks_id_seq OWNER TO postgres;

--
-- TOC entry 1949 (class 0 OID 0)
-- Dependencies: 164
-- Name: marks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE marks_id_seq OWNED BY marks.id;


--
-- TOC entry 167 (class 1259 OID 28488)
-- Dependencies: 6
-- Name: students; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE students (
    id bigint NOT NULL,
    name text,
    surname text
);


ALTER TABLE public.students OWNER TO postgres;

--
-- TOC entry 166 (class 1259 OID 28486)
-- Dependencies: 167 6
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.students_id_seq OWNER TO postgres;

--
-- TOC entry 1950 (class 0 OID 0)
-- Dependencies: 166
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE students_id_seq OWNED BY students.id;


--
-- TOC entry 169 (class 1259 OID 28497)
-- Dependencies: 6
-- Name: subjects; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE subjects (
    id bigint NOT NULL,
    name text
);


ALTER TABLE public.subjects OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 28495)
-- Dependencies: 6 169
-- Name: subjects_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE subjects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.subjects_id_seq OWNER TO postgres;

--
-- TOC entry 1951 (class 0 OID 0)
-- Dependencies: 168
-- Name: subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE subjects_id_seq OWNED BY subjects.id;


--
-- TOC entry 1913 (class 2604 OID 28473)
-- Dependencies: 161 162 162
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY groups ALTER COLUMN id SET DEFAULT nextval('groups_id_seq'::regclass);


--
-- TOC entry 1914 (class 2604 OID 28485)
-- Dependencies: 165 164 165
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marks ALTER COLUMN id SET DEFAULT nextval('marks_id_seq'::regclass);


--
-- TOC entry 1915 (class 2604 OID 28491)
-- Dependencies: 167 166 167
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY students ALTER COLUMN id SET DEFAULT nextval('students_id_seq'::regclass);


--
-- TOC entry 1916 (class 2604 OID 28500)
-- Dependencies: 168 169 169
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY subjects ALTER COLUMN id SET DEFAULT nextval('subjects_id_seq'::regclass);


--
-- TOC entry 1933 (class 0 OID 28477)
-- Dependencies: 163 1940
-- Data for Name: group_students; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY group_students (stud_id, group_id) FROM stdin;
\.


--
-- TOC entry 1932 (class 0 OID 28470)
-- Dependencies: 162 1940
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY groups (id, name) FROM stdin;
518	IF36d
519	IF59a
520	IF58a
521	IF35g
522	K12j
523	lkm25
\.


--
-- TOC entry 1952 (class 0 OID 0)
-- Dependencies: 161
-- Name: groups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('groups_id_seq', 524, true);


--
-- TOC entry 1935 (class 0 OID 28482)
-- Dependencies: 165 1940
-- Data for Name: marks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY marks (id, mark, student_id, subject_id) FROM stdin;
3	5	23	23
\.


--
-- TOC entry 1953 (class 0 OID 0)
-- Dependencies: 164
-- Name: marks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('marks_id_seq', 3, true);


--
-- TOC entry 1937 (class 0 OID 28488)
-- Dependencies: 167 1940
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY students (id, name, surname) FROM stdin;
22	Marianna	Roshchenko
23	Ivan	Ivanov
24	Dasha	Lutenko
25	Marianna	Roshchenko
26	Vasya	Pupkin
\.


--
-- TOC entry 1954 (class 0 OID 0)
-- Dependencies: 166
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('students_id_seq', 26, true);


--
-- TOC entry 1939 (class 0 OID 28497)
-- Dependencies: 169 1940
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY subjects (id, name) FROM stdin;
23	Matan
24	Funcan
26	English
\.


--
-- TOC entry 1955 (class 0 OID 0)
-- Dependencies: 168
-- Name: subjects_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('subjects_id_seq', 26, true);


--
-- TOC entry 1918 (class 2606 OID 28505)
-- Dependencies: 162 162 1941
-- Name: group_pk_ID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT "group_pk_ID" PRIMARY KEY (id);


--
-- TOC entry 1920 (class 2606 OID 28507)
-- Dependencies: 163 163 1941
-- Name: groups_by_stud_pk_studid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY group_students
    ADD CONSTRAINT groups_by_stud_pk_studid PRIMARY KEY (stud_id);


--
-- TOC entry 1922 (class 2606 OID 28509)
-- Dependencies: 165 165 1941
-- Name: mark_pk_ID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY marks
    ADD CONSTRAINT "mark_pk_ID" PRIMARY KEY (id);


--
-- TOC entry 1924 (class 2606 OID 28511)
-- Dependencies: 167 167 1941
-- Name: student_pk_ID; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY students
    ADD CONSTRAINT "student_pk_ID" PRIMARY KEY (id);


--
-- TOC entry 1926 (class 2606 OID 28513)
-- Dependencies: 169 169 1941
-- Name: subject_pk_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY subjects
    ADD CONSTRAINT subject_pk_id PRIMARY KEY (id);


--
-- TOC entry 1927 (class 2606 OID 28514)
-- Dependencies: 1923 163 167 1941
-- Name: grbyst_fk_stud; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY group_students
    ADD CONSTRAINT grbyst_fk_stud FOREIGN KEY (stud_id) REFERENCES students(id);


--
-- TOC entry 1928 (class 2606 OID 28519)
-- Dependencies: 163 1917 162 1941
-- Name: grbystud_fk_groupid; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY group_students
    ADD CONSTRAINT grbystud_fk_groupid FOREIGN KEY (group_id) REFERENCES groups(id);


--
-- TOC entry 1929 (class 2606 OID 28524)
-- Dependencies: 165 167 1923 1941
-- Name: mark_fk_stud; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marks
    ADD CONSTRAINT mark_fk_stud FOREIGN KEY (student_id) REFERENCES students(id);


--
-- TOC entry 1930 (class 2606 OID 28529)
-- Dependencies: 165 169 1925 1941
-- Name: mark_fk_subjects; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY marks
    ADD CONSTRAINT mark_fk_subjects FOREIGN KEY (subject_id) REFERENCES subjects(id);


--
-- TOC entry 1946 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-01-09 23:10:58 EET

--
-- PostgreSQL database dump complete
--

