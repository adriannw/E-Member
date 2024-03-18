# E-Member
 JavaSource Member Catalog

1. Perlu membuat direktori berikut :
   C:\Ekaweb 
   C:\Member
2. Untuk database postgreSQL :
   1. Database: postgres pada port: 5432 dengan password : admin
   1. Dicreate schemas :eka
   2. 

CREATE TABLE eka.member2
(
    id integer NOT NULL,
    nama character varying,
    password character varying,
    nomor_hp character varying,
    tgl_lahir character varying,
    email character varying,
    jk integer,
    no_ktp character varying,
    foto character varying,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS eka.member2
    OWNER to postgres;

COMMENT ON TABLE eka.member2
    IS 'structure database member';abase member';