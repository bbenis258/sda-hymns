CREATE TABLE IF NOT EXISTS public.hymn
(
    id    int8         NOT NULL,
    title varchar(255) NOT NULL,
    CONSTRAINT hymn_pkey PRIMARY KEY (id)
);
