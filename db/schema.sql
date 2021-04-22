CREATE TABLE user (
  id text unique PRIMARY KEY,
  is_org boolean not null default FALSE,
  name text,
  email text,
  bio text,
  goal numeric,
  progress numeric,
  icon text
);

CREATE TABLE post (
  id serial PRIMARY KEY,
  user_id text not null,
  title text not null,
  body text not null,
  tags text[] not null,
  pics text[],
  type text not null,
  ts timestamp not null default current_timestamp,
  FOREIGN KEY (user_id) references user (id)
);

CREATE TABLE donation (
  post_id serial not null,
  goal numeric not null,
  sum_donations numeric not null,
  deadline timestamp not null,
  FOREIGN KEY (post_id) references post (id)
);

CREATE TABLE transactions (
  id serial PRIMARY KEY,
  post_id serial not null,
  donator_id text not null,
  amount numeric not null,
  FOREIGN KEY (post_id) references post (id),
  FOREIGN KEY (donator_id) references user (id)
);

CREATE TABLE event (
  post_id serial not null,
  location text not null,
  event_date date not null,
  start_time time not null,
  end_time time not null,
  FOREIGN KEY (post_id) references post (id)
);

CREATE TABLE volunteers (
  user_id text not null,
  post_id serial not null,
  FOREIGN KEY (user_id) references user (id),
  FOREIGN KEY (post_id) references post (id)
);

CREATE TABLE forum (
  id serial PRIMARY KEY,
  user_id text not null,
  name text not null,
  details text not null,
  tags text[] not null,
  FOREIGN KEY (user_id) references user (id)
);

CREATE TABLE forum_post (
  id serial PRIMARY KEY,
  forum_id serial not null,
  user_id text not null,
  content text not null,
  ts timestamp not null default current_timestamp
);

CREATE TABLE tags (
  tags text unique not null
);
