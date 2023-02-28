-- Database: MovieApp

-- DROP DATABASE IF EXISTS "MovieApp";

CREATE DATABASE "MovieApp"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Mexico.1252'
    LC_CTYPE = 'Spanish_Mexico.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE IF NOT EXISTS public.director
(
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    birth_date date,
    nationality character varying(30) COLLATE pg_catalog."default",
    active_years character varying(30) COLLATE pg_catalog."default",
    favorite_genre character varying(30) COLLATE pg_catalog."default",
    id_director integer NOT NULL DEFAULT nextval('director_id_director_seq'::regclass),
    CONSTRAINT director_pkey PRIMARY KEY (id_director)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.director
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.studio
(
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    industry character varying(30) COLLATE pg_catalog."default",
    foundation date,
    founder character varying(30) COLLATE pg_catalog."default",
    headquarters character varying(30) COLLATE pg_catalog."default",
    id_studio integer NOT NULL DEFAULT nextval('studio_id_studio_seq'::regclass),
    CONSTRAINT studio_pkey PRIMARY KEY (id_studio)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.studio
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.movie
(
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    genre character varying(30) COLLATE pg_catalog."default",
    duration_min integer,
    classification character varying(30) COLLATE pg_catalog."default",
    release_date date,
    description text COLLATE pg_catalog."default",
    id_director integer,
    id_studio integer,
    id_movie integer NOT NULL DEFAULT nextval('movie_id_movie_seq'::regclass),
    CONSTRAINT movie_pkey PRIMARY KEY (id_movie),
    CONSTRAINT movie_id_director_fkey FOREIGN KEY (id_director)
        REFERENCES public.director (id_director) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT movie_id_studio_fkey FOREIGN KEY (id_studio)
        REFERENCES public.studio (id_studio) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.movie
    OWNER to postgres;