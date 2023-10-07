CREATE TABLE users(
    uid BIGSERIAL PRIMARY KEY,
    nickname VARCHAR(20) NOT NULL
);


CREATE TABLE files(
	uid VARCHAR(50) NOT NULL,
	title VARCHAR(50) NOT NULL,
	extension VARCHAR(20) NOT NULL,
	user_id BIGINT REFERENCES users(uid),
	CONSTRAINT user_files UNIQUE(uid, user_id)
)