CREATE DATABASE ser516p2;

show databases;

use ser516p2;

CREATE TABLE answer_table
(studentID INTEGER PRIMARY KEY,
quizID INTEGER,
questionID INTEGER,
answerGiven TEXT,
marks TEXT
);

DESCRIBE answer_table;
