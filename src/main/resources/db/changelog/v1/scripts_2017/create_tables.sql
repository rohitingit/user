
DROP TABLE IF EXISTS public.role_permission;
DROP TABLE IF EXISTS public.address;
DROP TABLE IF EXISTS public.certification;
DROP TABLE IF EXISTS public.qualification;
DROP TABLE IF EXISTS public.document;
DROP TABLE IF EXISTS public."user";
DROP TABLE IF EXISTS public.login; 
DROP TABLE IF EXISTS public.types_data;
DROP TABLE IF EXISTS public.types_metadata;


CREATE TABLE public.types_metadata
(
  id bigserial,
  name character varying(255) NOT NULL,
  CONSTRAINT pk_types_metadata_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.types_metadata
  OWNER TO postgres;



CREATE TABLE public.types_data
(
  id bigserial,
  type_id bigint NOT NULL,
  name character varying(255) NOT NULL,
  CONSTRAINT pk_types_data_id PRIMARY KEY (id),
  CONSTRAINT fk_types_data_metadata FOREIGN KEY (type_id)
      REFERENCES public.types_metadata (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.types_data
  OWNER TO postgres;

CREATE TABLE public.login
(
  id bigserial,
  username character varying(100),
  email character varying(100),
  password character varying(100),
  role_id bigint NOT NULL,
  login_with character varying(20),
  social_id character varying(50),
  device_id character varying(100),
  refresh_token character varying(100),
  access_token character varying(100),
  is_active boolean,
  created_date time without time zone,
  modified_date time without time zone,
  CONSTRAINT login_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.login
  OWNER TO postgres;  

CREATE TABLE public.role_permission
(
  role_id bigserial,
  role_name character varying,
  permissions character varying(250),
  created_date timestamp without time zone,
  modified_date timestamp without time zone,
  CONSTRAINT role_permission_pkey PRIMARY KEY (role_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.role_permission
  OWNER TO postgres;

CREATE TABLE public."user"
(
  id bigserial,
  login_id bigint,
  first_name character varying,
  middle_name character varying,
  last_name character varying,
  email character varying(50),
  mobile_no character varying,
  dob date,
  gender character varying(10),
  profile_image_url character varying,
  is_email_verified boolean NOT NULL DEFAULT false,
  is_mobile_verified boolean NOT NULL DEFAULT false,
  signup_stage character varying,
  status character varying(10),
  tz_offset bigint,
  created_date timestamp without time zone,
  modified_date timestamp without time zone,
  CONSTRAINT pk_user PRIMARY KEY (id),
  CONSTRAINT user_login_id_fkey FOREIGN KEY (login_id)
      REFERENCES public.login (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT user_email_key UNIQUE (email)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public."user"
  OWNER TO postgres;
  

CREATE TABLE public.address
(
  id bigserial,
  user_id bigint NOT NULL,
  address_type bigint,
  address text NOT NULL,
  latitude bigint,
  logitute bigint,
  landmark text,
  city character varying(50),
  zipcode bigint,
  state character varying(50),
  country character varying(50),
  is_active boolean NOT NULL DEFAULT true,
  created_date timestamp without time zone NOT NULL,
  modified_date timestamp without time zone,
  CONSTRAINT pk_address PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.address
  OWNER TO postgres;
  
  

CREATE TABLE public.certification
(
  id bigserial,
  user_id bigint NOT NULL,
  name character varying(50) NOT NULL,
  title character varying(50),
  is_active boolean NOT NULL DEFAULT true,
  created_date timestamp without time zone NOT NULL,
  modified_date timestamp without time zone,
  attachment_url character varying(255),
  CONSTRAINT pk_certifications_id PRIMARY KEY (id),
  CONSTRAINT fk_certifications_user FOREIGN KEY (user_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.certification
  OWNER TO postgres;
  

CREATE TABLE public.qualification
(
  id bigserial,
  user_id bigint NOT NULL,
  qualification character varying(50) NOT NULL,
  specialization character varying(50) NOT NULL,
  institute character varying(50) NOT NULL,
  institute_name character varying(50) NOT NULL,
  obtain_marks integer NOT NULL,
  total_marks integer NOT NULL,
  percentage double precision NOT NULL,
  admission_date date NOT NULL,
  complition_date date NOT NULL,
  is_active boolean NOT NULL DEFAULT true,
  created_date timestamp without time zone NOT NULL,
  modified_date timestamp without time zone,
  attachment_url character varying(255),
  CONSTRAINT pk_qualifications_id PRIMARY KEY (id),
  CONSTRAINT fk_qualifications_user FOREIGN KEY (user_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.qualification
  OWNER TO postgres;

CREATE TABLE public.document
(
  id bigserial,
  type_id bigint NOT NULL,
  user_id bigint NOT NULL,
  is_active boolean NOT NULL DEFAULT true,
  created_date timestamp without time zone NOT NULL,
  modified_date timestamp without time zone,
  attachment_url character varying(255),
  CONSTRAINT pk_documents_id PRIMARY KEY (id),
  CONSTRAINT fk_documents_types_data FOREIGN KEY (type_id)
      REFERENCES public.types_data (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_documents_user FOREIGN KEY (user_id)
      REFERENCES public."user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.document
  OWNER TO postgres;
  
  
