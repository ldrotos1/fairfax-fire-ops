-- Create the table schema --
CREATE SCHEMA ffx_fire_ops
	AUTHORIZATION postgres;

-- Enable post GIS --
CREATE EXTENSION postgis;

-- Create the fire department table --
CREATE TABLE ffx_fire_ops.department
(
    dept_id integer,
    dept_full_name text NOT NULL UNIQUE,
    dept_short_name text NOT NULL UNIQUE,
    dept_abbreviation character varying(6) NOT NULL UNIQUE,
    dept_border geometry(Polygon,4326),
    PRIMARY KEY (dept_id)
);

-- Create the station table --
CREATE TABLE ffx_fire_ops.county_station
(
    station_id integer,
    station_number integer NOT NULL UNIQUE,
    station_name text NOT NULL UNIQUE,
    battalion integer NOT NULL,
    is_volunteer boolean NOT NULL DEFAULT False,
    phone_number character varying(12) NOT NULL UNIQUE,
    address text NOT NULL UNIQUE,
    city text NOT NULL,
    state text NOT NULL,
    zip integer NOT NULL,
	department_id integer,
    lat double precision NOT NULL,
    lon double precision NOT NULL,
    density text NOT NULL,
    special_ops text NOT NULL,
    is_battalion_hq boolean NOT NULL DEFAULT False,
    is_division_hq boolean NOT NULL DEFAULT False,
    location geometry(Point,4326) DEFAULT NULL UNIQUE,
    PRIMARY KEY (station_id),
    CONSTRAINT dept_fk FOREIGN KEY (department_id)
        REFERENCES ffx_fire_ops.department (dept_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL
);

-- Create the station first due area table --
CREATE TABLE ffx_fire_ops.first_due_area
(
    station_id integer,
    first_due_area geometry(Polygon,4326) NOT NULL,
    PRIMARY KEY (station_id),
    CONSTRAINT station_fk FOREIGN KEY (station_id)
        REFERENCES ffx_fire_ops.county_station (station_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

-- Create the apparatus type table --
CREATE TABLE ffx_fire_ops.apparatus_type
(
    apparatus_type_id integer,
    apparatus_type character varying(35) NOT NULL UNIQUE,
    apparatus_category character varying(30) NOT NULL,
    apparatus_image character varying(30) NOT NULL UNIQUE,
    max_staff integer NOT NULL,
    min_staff_ff integer NOT NULL,
    min_staff_tech integer NOT NULL,
    min_staff_officer integer NOT NULL,
    min_staff_command integer NOT NULL,
    is_paramedic_required boolean NOT NULL,
    is_cross_staffed boolean NOT NULL,
    is_vol_staffed boolean NOT NULL,
    PRIMARY KEY (apparatus_type_id)
);

-- Create the apparatus table --
CREATE TABLE ffx_fire_ops.county_apparatus
(
    unit_designator character varying(12) NOT NULL,
    apparatus_type_id integer NOT NULL,
    station_id integer NOT NULL,
    dept_id integer NOT NULL,
    is_reserved boolean NOT NULL DEFAULT False,
    year integer,
    make text,
    model text,
    PRIMARY KEY (unit_designator),
    CONSTRAINT dept_id_fk FOREIGN KEY (dept_id)
        REFERENCES ffx_fire_ops.department (dept_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT station_desig_fk FOREIGN KEY (station_id)
        REFERENCES ffx_fire_ops.county_station (station_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL,
    CONSTRAINT apparatus_type_id_fk FOREIGN KEY (apparatus_type_id)
        REFERENCES ffx_fire_ops.apparatus_type (apparatus_type_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

-- Create the station assignments table --
CREATE TABLE ffx_fire_ops.station_assignments
(
    id integer NOT NULL,
    station_id integer NOT NULL,
    shift character varying(30) NOT NULL,
    PRIMARY KEY (id)
);

-- Create the personnel table --
CREATE TABLE ffx_fire_ops.personnel
(
    emp_id integer NOT NULL,
    first_name text NOT NULL,
    last_name text NOT NULL,
    rank character varying(30) NOT NULL,
    ems_level character varying(15) NOT NULL,
    station_assignment_id integer,
    PRIMARY KEY (emp_id),
    CONSTRAINT station_assignment_id_fk FOREIGN KEY (station_assignment_id)
        REFERENCES ffx_fire_ops.station_assignments (id) MATCH SIMPLE
            ON UPDATE CASCADE
            ON DELETE NO ACTION    
);

-- Create the command assignments table --
CREATE TABLE ffx_fire_ops.command_assignments
(
    id integer NOT NULL,
    assignment text NOT NULL,
    station_id integer NOT NULL,
    shift character varying(30) NOT NULL,
    description text NOT NULL,
    person_id integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT person_id_fk FOREIGN KEY (person_id)
        REFERENCES ffx_fire_ops.personnel (emp_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION  
);