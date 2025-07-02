
package gov.ffx.fire.ops.resources_service.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import gov.ffx.fire.ops.resources_service.domain.entities.ApparatusTypeEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.CountyApparatusEntity;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStationApparatus;

public class ApparatusMapperTest {

  CountyApparatusEntity apparatusEntity;

  CountyStationApparatus stationApparatus;

  @BeforeEach
  void setup() {
    stationApparatus = null;
    apparatusEntity = CountyApparatusEntity.builder()
      .unitDesignator("123")
      .year(1999)
      .make("Ford")
      .model("Fire Engine")
      .isReserved(false)
      .apparatusType(ApparatusTypeEntity.builder()
        .apparatusType("Engine")
        .apparatusCategory("Suppression")
        .build())
      .build();
  }

  @Test
  @DisplayName("Ensure apparatus mapper maps apparatus entity to apparatus")
  void testApparatusEntityToApparatus() {
  
    stationApparatus = ApparatusMapper.apparatusEntityToApparatus(apparatusEntity);
    assertAll("Apparatus entity mapped to apparatus", 
      () -> assertEquals("123", stationApparatus.getUnitDesignator(), "Station designator not mapped correctly"),
      () -> assertEquals("Engine", stationApparatus.getApparatusType(), "Apparatus type not mapped correctly"),
      () -> assertEquals("Suppression", stationApparatus.getApparatusCategory(), "Apparatus category not mapped correctly"),
      () -> assertEquals(1999, stationApparatus.getYear(), "Year not mapped correctly"),
      () -> assertEquals("Ford", stationApparatus.getMake(), "Make not mapped correctly"),
      () -> assertEquals("Fire Engine", stationApparatus.getModel(), "Model not mapped correctly"),
      () -> assertFalse(stationApparatus.getIsReserved(), "Is reserved flag not mapped correctly"));
  }

}