#!/bin/bash
echo "|-------------------------------------------------------------------|"
echo "| This script will create the ffx fire operations postgres database |"
echo "|-------------------------------------------------------------------|"

if ! [ -f ~/ffx-fire-ops/.dbconnprops ]; then
  printf "\nRequired ~/ffx-fire-ops/.dbconnprops file doesn't exist"
  printf "\nRun set_conn_props.sh to create"
  exit 1
fi

if ! [ -f ~/ffx-fire-ops/.pgpass ]; then
  printf "\nRequired ~/ffx-fire-ops/.pgpass file doesn't exist"
  printf "\nRun set_conn_props.sh to create"
  exit 1
fi

IFS=":"
read -ra conn_info < ~/ffx-fire-ops/.dbconnprops

if ! [ ${#conn_info[@]} = 3 ]; then
  printf "\nThe ~/ffx-fire-ops/.dbconnprops file is corrupted"
  printf "\nRun set_conn_props.sh to recreate"
  exit 1
fi

host=${conn_info[0]}
port=${conn_info[1]}
user=${conn_info[2]}

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
CREATE_DB_FILE="${SCRIPT_DIR}/sql/create_database.sql"
CREATE_TABLES_FILE="${SCRIPT_DIR}/sql/create_table_schema.sql"
DEPARTMENT_DATA="${SCRIPT_DIR}/datasets/depart_data.csv"
COUNTY_STATION_DATA="${SCRIPT_DIR}/datasets/county_station_data.csv"
COUNTY_APPARATUS_DATA="${SCRIPT_DIR}/datasets/county_apparatus_data.csv"
APPARATUS_TYPE_DATA="${SCRIPT_DIR}/datasets/apparatus_type_data.csv"
PERSONNEL_DATA="${SCRIPT_DIR}/datasets/personnel_data.csv"
STATION_ASSIGNMENTS_DATA="${SCRIPT_DIR}/datasets/station_assignments_data.csv"
COMMAND_ASSIGNMENTS_DATA="${SCRIPT_DIR}/datasets/command_assignments_data.csv"
STATION_SHIFTS_DATA="${SCRIPT_DIR}/datasets/station_shifts_data.csv"
CREATE_STATION_GEO_FILE="${SCRIPT_DIR}/sql/create_station_geo.sql"

echo "Creating database and table schema"
psql -U "$user" -h "$host" -p "$port" -f "$CREATE_DB_FILE"
psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops -f "$CREATE_TABLES_FILE"

echo "Loading department data"
DEPARTMENT_DATA=$(echo "$DEPARTMENT_DATA" | tr / \\\\)
DEPARTMENT_DATA="c:$(echo "$DEPARTMENT_DATA" | cut -c 3-)"
psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops \
  -c "\\copy ffx_fire_ops.department from '$DEPARTMENT_DATA' WITH DELIMITER ',' CSV;"

echo "Loading station data"
COUNTY_STATION_DATA=$(echo "$COUNTY_STATION_DATA" | tr / \\\\)
COUNTY_STATION_DATA="c:$(echo "$COUNTY_STATION_DATA" | cut -c 3-)"
psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops \
  -c "\\copy ffx_fire_ops.county_station from '$COUNTY_STATION_DATA' WITH DELIMITER ',' CSV;"

echo "Loading apparatus type data"
APPARATUS_TYPE_DATA=$(echo "$APPARATUS_TYPE_DATA" | tr / \\\\)
APPARATUS_TYPE_DATA="c:$(echo "$APPARATUS_TYPE_DATA" | cut -c 3-)"
psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops \
  -c "\\copy ffx_fire_ops.apparatus_type from '$APPARATUS_TYPE_DATA' WITH DELIMITER ',' CSV;"

echo "Loading apparatus data"
COUNTY_APPARATUS_DATA=$(echo "$COUNTY_APPARATUS_DATA" | tr / \\\\)
COUNTY_APPARATUS_DATA="c:$(echo "$COUNTY_APPARATUS_DATA" | cut -c 3-)"
psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops \
  -c "\\copy ffx_fire_ops.county_apparatus from '$COUNTY_APPARATUS_DATA' WITH DELIMITER ',' CSV;"

echo "Loading personnel data"
PERSONNEL_DATA=$(echo "$PERSONNEL_DATA" | tr / \\\\)
PERSONNEL_DATA="c:$(echo "$PERSONNEL_DATA" | cut -c 3-)"
psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops \
  -c "\\copy ffx_fire_ops.personnel from '$PERSONNEL_DATA' WITH DELIMITER ',' CSV;"

echo "Loading station assignments data"
STATION_ASSIGNMENTS_DATA=$(echo "$STATION_ASSIGNMENTS_DATA" | tr / \\\\)
STATION_ASSIGNMENTS_DATA="c:$(echo "$STATION_ASSIGNMENTS_DATA" | cut -c 3-)"
psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops \
  -c "\\copy ffx_fire_ops.station_assignments from '$STATION_ASSIGNMENTS_DATA' WITH DELIMITER ',' CSV;"

echo "Loading station shift data"
STATION_SHIFTS_DATA=$(echo "$STATION_SHIFT_DATA" | tr / \\\\)
STATION_SHIFTS_DATA="c:$(echo "$STATION_SHIFT_DATA" | cut -c 3-)"
psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops \ 
  -c "\\copy ffx_fire_ops.station_shifts from '$STATION_SHIFTS_DATA' WITH DELIMITER ',' CSV;"

echo "Loading command assignments data"
COMMAND_ASSIGNMENTS_DATA=$(echo "$COMMAND_ASSIGNMENTS_DATA" | tr / \\\\)
COMMAND_ASSIGNMENTS_DATA="c:$(echo "$COMMAND_ASSIGNMENTS_DATA" | cut -c 3-)"
psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops \
  -c "\\copy ffx_fire_ops.command_assignments from '$COMMAND_ASSIGNMENTS_DATA' WITH DELIMITER ',' CSV;"

echo "Creating station location point geometries"
psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops -f "$CREATE_STATION_GEO_FILE"

echo "Creating station first due polygon geometries"
for SQL_FILE in "${SCRIPT_DIR}"/sql/first_due_geom/*; 
  do psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops -f "$SQL_FILE"
done

echo "Creating department border polygon geometries"
for SQL_FILE in "${SCRIPT_DIR}"/sql/depart_border_geom/*; 
  do psql -U "$user" -h "$host" -p "$port" -d ffx-fire-ops -f "$SQL_FILE"
done

echo "Database created"