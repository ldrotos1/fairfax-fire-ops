
package gov.ffx.fire.ops.resources_service.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import gov.ffx.fire.ops.resources_service.domain.entities.CountyApparatusEntity;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStationApparatus;
import gov.ffx.fire.ops.resources_service.test_utilities.TestObjectClassLoader;

public class ApparatusMapperTest {

  CountyApparatusEntity apparatusEntity;

  CountyStationApparatus stationApparatus;

  @BeforeEach
  void setup() {
    apparatusEntity = TestObjectClassLoader.loadClassFromJson("apparatusEntity.json", CountyApparatusEntity.class);
  }

  @Test
  @DisplayName("Ensure apparatus mapper maps apparatus entity to apparatus")
  void testApparatusEntityToApparatus() {
  
    stationApparatus = ApparatusMapper.apparatusEntityToApparatus(apparatusEntity);
    assertAll("Apparatus entity mapped to apparatus", 
      () -> assertEquals("E408", stationApparatus.getUnitDesignator(), "Unit designator not mapped correctly"),
      () -> assertEquals("Engine", stationApparatus.getApparatusType(), "Apparatus type not mapped correctly"),
      () -> assertEquals("Suppression", stationApparatus.getApparatusCategory(), "Apparatus category not mapped correctly"),
      () -> assertEquals(2023, stationApparatus.getYear(), "Year not mapped correctly"),
      () -> assertEquals("Ford", stationApparatus.getMake(), "Make not mapped correctly"),
      () -> assertEquals("Fire Engine", stationApparatus.getModel(), "Model not mapped correctly"),
      () -> assertFalse(stationApparatus.getIsReserved(), "Is reserved flag not mapped correctly"));
  }

}