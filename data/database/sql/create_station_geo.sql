UPDATE ffx_fire_ops.county_station
SET location = ST_SetSRID(ST_MakePoint(lon,lat),4326);
CREATE INDEX location_index ON ffx_fire_ops.county_station USING GIST (location);