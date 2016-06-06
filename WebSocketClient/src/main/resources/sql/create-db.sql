CREATE TABLE users (
  id BIGINT IDENTITY,
  email  VARCHAR(50),
  password VARCHAR(100), 
  created_time TIMESTAMP, 
  modified_time TIMESTAMP
);
CREATE TABLE tokens (
  sequence_id VARCHAR(36) PRIMARY KEY,
  expiry_date TIMESTAMP, 
  usr BIGINT,
  created_time TIMESTAMP, 
  modified_time TIMESTAMP, 
  CONSTRAINT FK_Users FOREIGN KEY (usr) REFERENCES users (id) ON DELETE SET NULL ON UPDATE CASCADE 
);