CREATE TABLE persona
(
  cedula integer NOT NULL,
  nombre character varying(1000),
  apellido character varying(1000),
  CONSTRAINT pk_cedula PRIMARY KEY (cedula)
);