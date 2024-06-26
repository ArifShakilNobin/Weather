CREATE DATABASE weather;


CREATE TABLE "user"
(
    id        SERIAL PRIMARY KEY,
    password  VARCHAR(255),
    user_name VARCHAR(255) UNIQUE
);


CREATE TABLE locations
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255)     NOT NULL,
    city      VARCHAR(255)     NOT NULL,
    country   VARCHAR(255)     NOT NULL,
    latitude  DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL
);

CREATE TABLE user_favourites
(
    id        SERIAL PRIMARY KEY,
    city      VARCHAR(255),
    country   VARCHAR(255),
    latitude  DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    user_id   INT NOT NULL,
    CONSTRAINT unique_city_country_lat_long UNIQUE (city, country, latitude, longitude),
    FOREIGN KEY (user_id) REFERENCES "user" (id)
);