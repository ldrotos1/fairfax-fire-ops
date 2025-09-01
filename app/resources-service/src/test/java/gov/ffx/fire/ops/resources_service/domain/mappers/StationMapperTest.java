package gov.ffx.fire.ops.resources_service.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import gov.ffx.fire.ops.resources_service.domain.entities.StationEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.StationListItemEntity;
import gov.ffx.fire.ops.models.station.Station;
import gov.ffx.fire.ops.models.station.StationListItem;
import gov.ffx.fire.ops.resources_service.test_utilities.TestObjectClassLoader;

public class StationMapperTest {

  StationListItemEntity stationListItemEntity;

  StationEntity stationEntity;
 
  StationListItem stationListItem;
 
  Station station;

  @BeforeEach
  void setup() {
    stationListItemEntity = TestObjectClassLoader.loadClassFromJson("stationListItemEntity.json", StationListItemEntity.class);
    stationEntity = TestObjectClassLoader.loadClassFromJson("stationEntity.json", StationEntity.class);
  }

  @Test
  @DisplayName("Ensure station mapper maps station list item entity to station list item")
  void testStationListItemEntityToStationListItem() {
    
    stationListItem = StationMapper.stationListItemEntityToStationListItem(stationListItemEntity);
    assertAll("Station list item entitiy mapped to station list item",
      () -> assertEquals(411, stationListItem.getStationDesignator(), "Station designator not mapped correctly"),
      () -> assertEquals("Springfield", stationListItem.getStationName(), "Station name not mapped correctly"),
      () -> assertEquals(403, stationListItem.getBattalion(), "Battalion not mapped correctly"));
  }

  @Test
  @DisplayName("Ensure station mapper maps station entity to station")
  void testStationEntityToStation() {

    station = StationMapper.stationEntityToStation(stationEntity);
    assertAll("Station list item entitiy mapped to station list item",
      () -> assertEquals(401, station.getStationDesignator(), "Station designator not mapped correctly"),
      () -> assertEquals(1, station.getStationNumber(), "Station number not mapped correctly"),
      () -> assertEquals("Sample Fire Station", station.getStationName(), "Station name not mapped correctly"),
      () -> assertEquals("Fire Department", station.getDepartment(), "Department not mapped correctly"),
      () -> assertEquals(401, station.getBattalion(), "Battalion not mapped correctly"),
      () -> assertEquals(400, station.getDivision(), "Division not mapped correctly"),
      () -> assertTrue(station.getIsVolunteer(), "Is volunteer flag not mapped correctly"),
      () -> assertFalse(station.getIsBattalionHq(), "Is battalion HQ flag not mapped correctly"),
      () -> assertTrue(station.getIsDivisionHq(), "Is division HQ flag not mapped correctly"),
      () -> assertEquals("123 Main St", station.getAddress(), "Address not mapped correctly"),
      () -> assertEquals("Anytown", station.getCity(), "City not mapped correctly"),
      () -> assertEquals("VA", station.getState(), "State not mapped correctly"),
      () -> assertEquals("12345", station.getZipCode(), "Zip code not mapped correctly"),
      () -> assertEquals("70355599663", station.getPhoneNumber(), "Phone number not mapped correctly"),
      () -> assertEquals("Urban", station.getDensity(), "Density not mapped correctly"),
      () -> assertEquals("Water Rescue", station.getSpecialOps(), "Special ops not mapped correctly"),
      () -> assertEquals(1, station.getApparatus().size(), "Apparatus list size not mapped correctly"));
  }
}
