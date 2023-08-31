CREATE TABLE IF NOT EXISTS public.hymn_verse
(
    id        int8         NOT NULL,
    hymn_id   int8         NOT NULL,
    "content" text         NOT NULL,
    sub_title varchar(255) NOT NULL,
    CONSTRAINT hymn_verse_pkey PRIMARY KEY (id),
    CONSTRAINT hymn_verse_fkey_01 FOREIGN KEY (hymn_id) REFERENCES public.hymn (id)
);
