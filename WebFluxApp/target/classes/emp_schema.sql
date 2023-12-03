CREATE TABLE emp(
                    id bigserial NOT NULL,
                    name character varying(255) ,
                    city character varying(255) ,
                    age bigint,
                    CONSTRAINT pk_emp_id PRIMARY KEY (id)
);