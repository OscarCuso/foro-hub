ALTER TABLE topicos
MODIFY status VARCHAR(20);

UPDATE topicos
SET status = 'ABIERTO'
WHERE status = '0' OR status = 0;

UPDATE topicos
SET status = 'RESUELTO'
WHERE status = '1' OR status = 1;