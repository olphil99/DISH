drop schema public cascade;
create schema public;

CREATE TABLE users (
  id text unique PRIMARY KEY,
  is_org boolean not null default FALSE,
  name text,
  email text,
  bio text,
  goal numeric,
  progress numeric,
  icon text
);

CREATE TABLE posts (
  id serial PRIMARY KEY,
  user_id text not null,
  title text not null,
  body text not null,
  tags text[] not null,
  pics text[],
  is_event boolean default FALSE,
  ts timestamp not null default current_timestamp,
  FOREIGN KEY (user_id) references users (id)
);

CREATE TABLE donations (
  post_id serial not null,
  goal numeric not null,
  sum_donations numeric not null,
  deadline timestamp not null,
  FOREIGN KEY (post_id) references posts (id)
);

CREATE TABLE transactions (
  id serial PRIMARY KEY,
  post_id serial not null,
  user_id text not null,
  amount numeric not null,
  FOREIGN KEY (post_id) references posts (id),
  FOREIGN KEY (user_id) references users (id)
);

CREATE TABLE events (
  post_id serial not null,
  location text not null,
  event_date date not null,
  start_time time not null,
  end_time time not null,
  FOREIGN KEY (post_id) references posts (id)
);

CREATE TABLE volunteers (
  user_id text not null,
  post_id serial not null,
  FOREIGN KEY (user_id) references users (id),
  FOREIGN KEY (post_id) references posts (id)
);

CREATE TABLE forums (
  id serial PRIMARY KEY,
  user_id text not null,
  name text not null,
  details text not null,
  tags text[] not null,
  FOREIGN KEY (user_id) references users (id)
);

CREATE TABLE forum_posts (
  id serial PRIMARY KEY,
  forum_id serial not null,
  user_id text not null,
  content text not null,
  ts timestamp not null default current_timestamp,
  FOREIGN KEY (forum_id) references forums (id),
  FOREIGN KEY (user_id) references users (id)
);

CREATE TABLE tags (
  tags text unique not null
);
