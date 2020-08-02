use locations;

commit;

insert into locations(inet_id, location_name) values(uuid(), 'Toronto');
insert into locations(inet_id, location_name) values(uuid(), 'Montreal');
insert into locations(inet_id, location_name) values(uuid(), 'Vancouver');

commit;
