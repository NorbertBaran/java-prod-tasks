INSERT INTO CLIENT(ID, NAME, LAST_NAME, ADDRESS, EMAIL, PHONE) VALUES
(1, 'Jan', 'Kowalski', 'Krucza 12', 'jan.kowalski@example.pl', '987654321'),
(2, 'John', 'Smith', 'Jane Street 21', 'john.smith@example.pl', '789456123');

INSERT INTO ANIMAL(ID, NAME, SPECIES, BORN, DIE, OWNER_ID) VALUES
(1, 'Tom', 'cat', '2012-04-23', null, 1),
(2, 'Jerry', 'mouse', '2012-04-23', null, 2);

INSERT INTO VISIT(ID, PATIENT_ID, DATE, BEGIN, DURATION, END, DESCRIPTION, STATUS) VALUES
(1, 1, '2020-05-30', '12:15', 15, '12:30', null, 'APPOINTMENT');
