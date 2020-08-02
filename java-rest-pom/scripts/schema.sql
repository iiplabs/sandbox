use locations;

commit;

CREATE TABLE locations (
  id bigint unsigned NOT NULL AUTO_INCREMENT, 
  enabled boolean DEFAULT true,
  inet_id varchar(50) NOT NULL,
  location_name varchar(100) NOT NULL,
  created datetime,
  updated datetime,
  created_by varchar(50),
  updated_by varchar(50),
  optlock int unsigned DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY inet_id_UNIQUE (inet_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

commit;
