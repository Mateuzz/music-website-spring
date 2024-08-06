truncate categories, authors, musics cascade;

insert into categories(id, nesting, parent_id, name) values 
    (1, 0, null, 'category 1'),
    (2, 1, 1, 'category 2'),
    (3, 1, 1, 'category 3'),
    (4, 2, 2, 'category 4');

insert into authors(id, name) values 
    (1, 'author 1'),
    (2, 'author 2'),
    (3, 'author 3');

insert into musics(id, author_id, category_id, create_date, update_date, name, filepath, views_count) values
    (1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'music 1', 'public/musics/1.mp4', 0),
    (2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'music 2', 'public/musics/2.mp4', 0),
    (3, 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'music 3', 'public/musics/3.mp4', 0);
