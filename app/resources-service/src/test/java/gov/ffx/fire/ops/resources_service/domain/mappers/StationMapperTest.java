package gov.ffx.fire.ops.resources_service.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import gov.ffx.fire.ops.resources_service.domain.entities.ApparatusTypeEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.CountyApparatusEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.CountyStationEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.CountyStationListItemEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.DepartmentEntity;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStation;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStationListItem;

public class StationMapperTest {

  CountyStationListItemEntity stationListItemEntity;

  CountyStationEntity stationEntity;
 
  CountyStationListItem stationListItem;
 
  CountyStation station;

  @BeforeEach
  void setup() {
    stationListItemEntity = CountyStationListItemEntity.builder()
      .stationDesignator(101)
      .stationName("Test Station")
      .battalion(100)
      .build();

    stationEntity = CountyStationEntity.builder()
      .stationDesignator(405)
      .stationName("Test Station")
      .stationNumber(5)
      .department(DepartmentEntity.builder()
        .departmentFullName("Fairfax Fire Dept")
        .build())
      .battalion(401)
      .division(400)
      .isVolunteer(true)
      .isBattalionHq(false)
      .isDivisionHq(true)
      .address("123 Fake Street")
      .city("Springfield")
      .state("VA")
      .zipCode("22122")
      .phoneNumber("70355599663")
      .density("Urban")
      .specialOps("Water Rescue")
      .apparatus(Set.of(CountyApparatusEntity.builder()
        .unitDesignator("E101")
        .apparatusType(ApparatusTypeEntity.builder()
          .apparatusType("Engine")
          .apparatusCategory("Supression")
          .build())
        .year(2001)
        .make("Ford")
        .model("Engine")
        .isReserved(false)
        .build()))
      .build();
  }

  @Test
  @DisplayName("Ensure station mapper maps station list item entity to station list item")
  void testStationListItemEntityToStationListItem() {
    
    stationListItem = StationMapper.stationListItemEntityToStationListItem(stationListItemEntity);
    assertAll("Station list item entitiy mapped to station list item",
      () -> assertEquals(101, stationListItem.getStationDesignator(), "Station designator not mapped correctly"),
      () -> assertEquals("Test Station", stationListItem.getStationName(), "Station name not mapped correctly"),
      () -> assertEquals(100, stationListItem.getBattalion(), "Battalion not mapped correctly"));
  }

  @Test
  @DisplayName("Ensure station mapper maps station entity to station")
  void testStationEntityToStation() {

    station = StationMapper.stationEntityToStation(stationEntity);
    assertAll("Station list item entitiy mapped to station list item",
      () -> assertEquals(405, station.getStationDesignator(), "Station designator not mapped correctly"),
      () -> assertEquals(5, station.getStationNumber(), "Station number not mapped correctly"),
      () -> assertEquals("Test Station", station.getStationName(), "Station name not mapped correctly"),
      () -> assertEquals("Fairfax Fire Dept", station.getDepartment(), "Department not mapped correctly"),
      () -> assertEquals(401, station.getBattalion(), "Battalion not mapped correctly"),
      () -> assertEquals(400, station.getDivision(), "Division not mapped correctly"),
      () -> assertTrue(station.getIsVolunteer(), "Is volunteer flag not mapped correctly"),
      () -> assertFalse(station.getIsBattalionHq(), "Is battalion HQ flag not mapped correctly"),
      () -> assertTrue(station.getIsDivisionHq(), "Is division HQ flag not mapped correctly"),
      () -> assertEquals("123 Fake Street", station.getAddress(), "Address not mapped correctly"),
      () -> assertEquals("Springfield", station.getCity(), "City not mapped correctly"),
      () -> assertEquals("VA", station.getState(), "State not mapped correctly"),
      () -> assertEquals("22122", station.getZipCode(), "Zip code not mapped correctly"),
      () -> assertEquals("70355599663", station.getPhoneNumber(), "Phone number not mapped correctly"),
      () -> assertEquals("Urban", station.getDensity(), "Density not mapped correctly"),
      () -> assertEquals("Water Rescue", station.getSpecialOps(), "Special ops not mapped correctly"),
      () -> assertEquals(1, station.getApparatus().size(), "Apparatus list size not mapped correctly"));
  }
}
