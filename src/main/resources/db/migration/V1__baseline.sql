CREATE TABLE IF NOT EXISTS digit_interval
(
    "id"    INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "start" INTEGER NOT NULL,
    "end"   INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS letter_interval
(
    "id"    INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "start" TEXT NOT NULL CHECK (LENGTH("start") = 1),
    "end"   TEXT NOT NULL CHECK (LENGTH("end") = 1)
);