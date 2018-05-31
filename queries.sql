SELECT table_name FROM information_schema.tables
                      WHERE table_schema='public'

DO $$
DECLARE
  t TEXT;
  tabulky varchar[] = array['canteen_daily_offers','employee_places','employee_phone_number','employee_voip',,
  'employee_publications','employee_web','users','lectures'];
BEGIN
   FOREACH t IN ARRAY tabulky
   LOOP
      EXECUTE format('ALTER %I DROP COLUMN deleted;',
                    t);
   END LOOP;
END $$;

DO $$
DECLARE
  t TEXT;
  tabulky varchar[] = array['employees','places','canteens', 'faculty'];
BEGIN
   FOREACH t IN ARRAY tabulky
   LOOP
      EXECUTE format('UPDATE %I set deleted = false;',
                    t);
   END LOOP;
END $$;


TRUNCATE employees CASCADE;
TRUNCATE canteens CASCADE;




'canteen_daily_offers'
'employee_places'
'faculty'
'employee_phone_number'
'employee_voip'
'canteens'
'employee_publications'
'employee_web'
'places'
'schema_version'
'users'
'lectures'
'teacher_lecture'
'timer'
'employees'