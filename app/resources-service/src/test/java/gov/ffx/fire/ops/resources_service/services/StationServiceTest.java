package gov.ffx.fire.ops.resources_service.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import gov.ffx.fire.ops.resources_service.domain.entities.CountyStationEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.CountyStationListItemEntity;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStation;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStationApparatus;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStationListItem;
import gov.ffx.fire.ops.resources_service.exceptions.StationDoesNotExistException;
import gov.ffx.fire.ops.resources_service.repositories.CountyStationListItemRepository;
import gov.ffx.fire.ops.resources_service.repositories.CountyStationRepository;
import gov.ffx.fire.ops.resources_service.test_utilities.TestObjectClassLoader;

@ExtendWith(MockitoExtension.class)
public class StationServiceTest {

  @Mock
  private CountyStationRepository countyStationRepo;

  @Mock
  private CountyStationListItemRepository countyStationListItemRepository;

  @InjectMocks
  private StationService stationService;
  
  @Test
  @DisplayName("Test getting a county station")
  void testGetCountyStation() throws StationDoesNotExistException {
    CountyStationEntity stationEntity = TestObjectClassLoader.loadClassFromJson("stationEntity.json", CountyStationEntity.class);
    
    when(countyStationRepo.findByStationDesignator(eq(401))).thenReturn(Optional.of(stationEntity));
    CountyStation station = stationService.getCountyStation(401);

    assertEquals(401, station.getStationDesignator(), "Station designator not mapped correctly");
    assertEquals(1, station.getStationNumber(), "Station number not mapped correctly");
    assertEquals("Sample Fire Station", station.getStationName(), "Station name not mapped correctly");
    assertEquals("Fire Department", station.getDepartment(), "Department name not mapped correctly");
    assertEquals(401, station.getBattalion(), "Battalion number not mapped correctly");
    assertEquals(400, station.getDivision(), "Division number not mapped correctly");
    assertTrue(station.getIsVolunteer(), "Is volunteer not mapped correctly");
    assertFalse(station.getIsBattalionHq(), "Is battalion HQ not mapped correctly");
    assertTrue(station.getIsDivisionHq(), "Is division HQ not mapped correctly");
    assertEquals("123 Main St", station.getAddress(), "Address not mapped correctly");
    assertEquals("Anytown", station.getCity(), "City not mapped correctly");
    assertEquals("12345", station.getZipCode(), "Zip code not mapped correctly");
    assertEquals("70355599663", station.getPhoneNumber(), "Phone number not mapped correctly");
    assertEquals("Urban", station.getDensity(), "Density not mapped correctly");
    assertEquals("Water Rescue", station.getSpecialOps(), "Special ops not mapped correctly");
    assertEquals(1, station.getApparatus().size(), "Apparatus number incorrect");
    assertTrue(station.getApparatus().contains(CountyStationApparatus.builder()
      .unitDesignator("E401")
      .apparatusType("Engine")
      .apparatusCategory("Supression")
      .year(2001)
      .make("Ford")
      .model("Fire Engine")
      .isReserved(false)
      .build()));
  }

  @Test
  @DisplayName("Test trying to get a county station that does not exist")
  void testGetCountyStationThatDoesNotExist() {
    when(countyStationRepo.findByStationDesignator(eq(490))).thenReturn(Optional.empty());
    
    Exception error = assertThrows(StationDoesNotExistException.class, () -> {
      stationService.getCountyStation(490);
    });
    assertTrue(error.getMessage().contains("Station number 490 does not exist"));
  }

  @Test
  @DisplayName("Test getting station list items")
  void testGetCountyStationList() {
    
    List<CountyStationListItemEntity> entities = new ArrayList<>();
    entities.add(CountyStationListItemEntity.builder()
      .stationDesignator(413)
      .stationName("Test station 13")
      .battalion(401)
      .build());
    entities.add(CountyStationListItemEntity.builder()
      .stationDesignator(409)
      .stationName("Test station 9")
      .battalion(402)
      .build());
    entities.add(CountyStationListItemEntity.builder()
      .stationDesignator(420)
      .stationName("Test station 20")
      .battalion(403)
      .build());

    when(countyStationListItemRepository.findAll()).thenReturn(entities);
    List<CountyStationListItem> stations = stationService.getCountyStationList();

    assertEquals(3, stations.size(), "Station list item list length invalid");
   
    assertTrue(stations.contains(CountyStationListItem.builder()
      .stationDesignator(413)
      .stationName("Test station 13")
      .battalion(401)
      .build()), "Station list item is missing station 13");
    
    assertTrue(stations.contains(CountyStationListItem.builder()
      .stationDesignator(409)
      .stationName("Test station 9")
      .battalion(402)
      .build()), "Station list item is missing station 9");

    assertTrue(stations.contains(CountyStationListItem.builder()
      .stationDesignator(420)
      .stationName("Test station 20")
      .battalion(403)
      .build()), "Station list item is missing station 20");
  }

}
