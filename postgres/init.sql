
-- CREATE TABLE users (
--     user_id SERIAL PRIMARY KEY,
--     username VARCHAR(50) UNIQUE NOT NULL,
--     password VARCHAR(255) NOT NULL,
--     email VARCHAR(100) UNIQUE NOT NULL,
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

-- CREATE TABLE IF NOT EXISTS products (
--     id SERIAL PRIMARY KEY,
--     name VARCHAR(100) NOT NULL,
--     price DECIMAL(10, 2) NOT NULL,
--     tax_rate DECIMAL(5, 2),
--     user_id INT NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
-- );


-- CREATE TABLE roles (
--     role_id SERIAL PRIMARY KEY,
--     role_name VARCHAR(50) UNIQUE NOT NULL
-- );

-- CREATE TABLE user_roles (
--     user_id INT NOT NULL,
--     role_id INT NOT NULL,
--     assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     PRIMARY KEY (user_id, role_id),
--     FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
--     FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE
-- );


CREATE TABLE public.users (
	id bigserial NOT NULL,
	"password" varchar(120) NULL,
	username varchar(20) NULL,
	-- CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE public.roles (
	role_id serial4 NOT NULL,
	role_name varchar(20) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (role_id),
	CONSTRAINT roles_role_name_check CHECK (((role_name)::text = ANY ((ARRAY['ROLE_USER'::character varying, 'ROLE_MODERATOR'::character varying, 'ROLE_ADMIN'::character varying])::text[])))
);

CREATE TABLE public.user_roles (
	user_id int8 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id),
	CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(role_id),
	CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id)
);

INSERT INTO roles(role_name) VALUES('ROLE_USER');
INSERT INTO roles(role_name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(role_name) VALUES('ROLE_ADMIN');