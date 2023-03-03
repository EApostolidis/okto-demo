INSERT INTO "doctor"("id", "first_name", "last_name") VALUES (1, 'Vaggelis', 'Apostolidis');
INSERT INTO "doctor"("id", "first_name", "last_name") VALUES (2, 'Kostas', 'Vakirlis');

INSERT INTO "patient"("id", "first_name", "last_name") VALUES (1, 'Rigas', 'Paparigas');
INSERT INTO "patient"("id", "first_name", "last_name") VALUES (2, 'Dimitris', 'Konstantelias');
INSERT INTO "patient"("id", "first_name", "last_name") VALUES (3, 'Marios', 'Koulierakis');
INSERT INTO "patient"("id", "first_name", "last_name") VALUES (4, 'Giorgos', 'Koudas');

INSERT INTO "appointment"("id", "date", "from", "to", "doctor_entity_id", "patient_entity_id") VALUES (1, '2023-03-04', '2023-03-04 00:00', '2023-03-04 01:00', 1, 1);
INSERT INTO "appointment"("id", "date", "from", "to", "doctor_entity_id", "patient_entity_id") VALUES (2, '2023-03-04', '2023-03-04 01:00', '2023-03-04 02:00', 1, 2);
INSERT INTO "appointment"("id", "date", "from", "to", "doctor_entity_id", "patient_entity_id") VALUES (3, '2023-03-04', '2023-03-04 02:00', '2023-03-03 02:00', 1, 2);

INSERT INTO "working_day"("id", "date", "doctor_entity_id") VALUES (1, '2023-03-04', 1);
INSERT INTO "working_day"("id", "date", "doctor_entity_id") VALUES (2, '2023-03-05', 1);
INSERT INTO "working_day"("id", "date", "doctor_entity_id") VALUES (3, '2023-03-06', 1);

INSERT INTO "time_slot"("id", "from", "to", "working_day_entity_id") VALUES (1, '2023-03-04 00:00', '2023-03-04 05:00', 1);
INSERT INTO "time_slot"("id", "from", "to", "working_day_entity_id") VALUES (2, '2023-03-04 06:00', '2023-03-04 10:00', 1);
INSERT INTO "time_slot"("id", "from", "to", "working_day_entity_id") VALUES (3, '2023-03-05 00:00', '2023-03-05 04:00', 2);
INSERT INTO "time_slot"("id", "from", "to", "working_day_entity_id") VALUES (4, '2023-03-05 04:30', '2023-03-05 08:00', 2);
INSERT INTO "time_slot"("id", "from", "to", "working_day_entity_id") VALUES (5, '2023-03-05 10:00', '2023-03-05 16:00', 2);
INSERT INTO "time_slot"("id", "from", "to", "working_day_entity_id") VALUES (6, '2023-03-06 00:00', '2023-03-06 05:00', 3);
INSERT INTO "time_slot"("id", "from", "to", "working_day_entity_id") VALUES (7, '2023-03-06 10:00', '2023-03-06 15:00', 3);



