INSERT INTO "doctor"("id", "first_name", "last_name") VALUES (1, 'doctorFirstName', 'doctorLastName');

INSERT INTO "patient"("id", "first_name", "last_name") VALUES (1, 'patientFirstName', 'patientLastName');

INSERT INTO "appointment"("id", "date", "from", "to", "doctor_entity_id", "patient_entity_id") VALUES (1, '2023-03-04', '2023-03-04 00:00', '2023-03-04 01:00', 1, 1);

INSERT INTO "working_day"("id", "date", "doctor_entity_id") VALUES (1, '2023-03-04', 1);

INSERT INTO "time_slot"("id", "from", "to", "working_day_entity_id") VALUES (1, '2023-03-04 00:00', '2023-03-04 11:00', 1);
INSERT INTO "time_slot"("id", "from", "to", "working_day_entity_id") VALUES (2, '2023-03-04 15:00', '2023-03-04 22:00', 1);