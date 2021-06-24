insert into elite.users(enabled, hash_password, user_role, username)
values ('true', '$2a$10$NI9e98I6SXgV0eNOk3EngeWajxGfQao7sqd5vz.rpc76Qz9rGqmHK', 'COMMANDER', 'tech');

insert into elite.systems(expansion_date, faction, "name", "number", orbit_large, orbit_large_control, orbit_medium,
                          orbit_medium_control, planet_base, planet_base_control, planet_large, planet_large_control,
                          population, primary_economy, secondary_economy)
values ('2000-01-01', 'NAGII_UNION', 'Nagii', 1, 1, 1, 2, 1, 3, 0, 1, 1, 4000000, 'INDUSTRIAL', 'EXTRACTION');

insert into tasks(combat_task, "date", do_nothing, election_task, message_header, task_1, task_2, task_description,
                   user_id)
values (null, '2000-01-01', null, null, null, null, null, null, 1);

insert into influence("date", influence, state, systems_id)
values ('2000-01-01', 0, 'NONE', 1);
insert into influence("date", influence, state, systems_id)
values ('2000-01-02', 10, 'NONE', 1);