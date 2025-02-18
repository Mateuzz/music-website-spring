/* 
    ==================== JPA ======================
*/

create sequence authors_seq start with 1 increment by 50;
create sequence categories_seq start with 1 increment by 50;
create sequence music_tags_seq start with 1 increment by 50;
create sequence musics_seq start with 1 increment by 50;
create sequence playlists_seq start with 1 increment by 50;
create sequence users_seq start with 1 increment by 50;
create table authorities (user_id bigint not null, authority varchar(50) not null, primary key (user_id, authority));
create table authors (id bigint not null, name varchar(150) not null, primary key (id));
create table categories (nesting integer not null, id bigint not null, parent_id bigint, name varchar(100) not null, primary key (id));
create table music_tags (id bigint not null, name varchar(100) not null, primary key (id));
create table musics (views_count integer not null, author_id bigint not null, category_id bigint not null, create_date timestamp(6) with time zone, id bigint not null, update_date timestamp(6) with time zone, name varchar(150) not null, filepath varchar(255), primary key (id));
create table musics_tags (music_id bigint not null, tags_id bigint not null, primary key (music_id, tags_id));
create table playlists (create_date timestamp(6) with time zone, created_by_id bigint, id bigint not null, update_date timestamp(6) with time zone, name varchar(150) not null, uri_image varchar(255), primary key (id));
create table playlists_musics (musics_id bigint not null, playlist_id bigint not null, primary key (musics_id, playlist_id));
create table users (id bigint not null, username varchar(100) not null, password varchar(500) not null, primary key (id), unique (username));
create index IDXgu7js9mh96hpeqm6eh7sp2k56 on music_tags (name);
alter table if exists authorities add constraint FKk91upmbueyim93v469wj7b2qh foreign key (user_id) references users;
alter table if exists categories add constraint FKsaok720gsu4u2wrgbk10b5n8d foreign key (parent_id) references categories;
alter table if exists musics add constraint FKdiar1u91wfi42xmg6vfl74n56 foreign key (author_id) references authors;
alter table if exists musics add constraint FK7ehfcd0bqha5uat6vro7y5oki foreign key (category_id) references categories;
alter table if exists musics_tags add constraint FK4i4qwsovxbkrwjfn0unw5nnij foreign key (tags_id) references music_tags;
alter table if exists musics_tags add constraint FKgo2pdeeqn31auegjk2b490le6 foreign key (music_id) references musics;
alter table if exists playlists add constraint FKkw37pixqvap2uc094o95j8lu7 foreign key (created_by_id) references users;
alter table if exists playlists_musics add constraint FKcks57cnn25vmiollws9luoryo foreign key (musics_id) references musics;
alter table if exists playlists_musics add constraint FK1558uiaum4vtxikom9irubgc8 foreign key (playlist_id) references playlists;

/* 
    ============================ Additional SQL ===================
*/

alter table musics add column search tsvector;
create index musics_search_idx on musics using gin (search);

alter table playlists_musics add column added_date timestamp with time zone default current_timestamp;

create or replace function set_music_search_after_insert_handler() 
    returns trigger as 
$$
declare 
author_name varchar(150);
begin
    select name into author_name from authors where id = NEW.author_id;

    NEW.search = to_tsvector('pg_catalog.simple', NEW.name || ' ' || author_name);

    return NEW;
end; 
$$ language plpgsql;

create or replace trigger set_music_search_after_insert
before insert or update of author_id, name
on musics 
for each row 
    execute function set_music_search_after_insert_handler();

