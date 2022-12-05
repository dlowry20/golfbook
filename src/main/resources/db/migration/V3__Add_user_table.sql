create table golf_book_user(
   user_id varchar(100) primary key not null,
   display_name varchar(100) not null,
   email varchar(100) not null,
   password varchar(100),
   handicap int4
);
create unique index display_name_idx on golf_book_user (display_name);