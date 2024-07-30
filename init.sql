CREATE TABLE courses (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT UNIQUE
);

INSERT INTO courses(name) VALUES
    ('Chemistry'),
    ('Statistics'),
    ('Social Studies');