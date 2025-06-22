package gov.ffx.fire.ops.resources_service.domain.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import gov.ffx.fire.ops.resources_service.domain.entities.CountyStationEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.CountyStationListItemEntity;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStation;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStationListItem;

@Component
public class StationMapper {

  /**
   * Converts a {@link CountyStationEntity} to a {@link CountyStation}
   * 
   * @param stationEntity
   * @return
   */
  public static CountyStation stationEntityToStation(CountyStationEntity stationEntity) {
    return CountyStation.builder()
      .stationDesignator(stationEntity.getStationDesignator())
      .stationNumber(stationEntity.getStationNumber())
      .stationName(stationEntity.getStationName())
      .department(stationEntity.getDepartment().getDepartmentFullName())
      .battalion(stationEntity.getBattalion())
      .division(stationEntity.getDivision())
      .isVolunteer(stationEntity.getIsVolunteer())
      .isBattalionHq(stationEntity.getIsBattalionHq())
      .isDivisionHq(stationEntity.getIsDivisionHq())
      .address(stationEntity.getAddress())
      .city(stationEntity.getCity())
      .state(stationEntity.getState())
      .zipCode(stationEntity.getZipCode())
      .phoneNumber(stationEntity.getPhoneNumber())
      .density(stationEntity.getDensity())
      .specialOps(stationEntity.getSpecialOps())
      .apparatus(stationEntity.getApparatus().stream()
        .map(ApparatusMapper::apparatusEntityToApparatus)
        .collect(Collectors.toSet()))
      .build();
  }

  /**
   * Converts a {@link CountyStation} to a {@link CountyStationEntity}
   * 
   * @param stationListItemEntity
   * @return
   */
  public static CountyStationListItem  stationListItemEntityToStationListItem(CountyStationListItemEntity stationListItemEntity) {
    return CountyStationListItem.builder()
      .stationDesignator(stationListItemEntity.getStationDesignator())
      .stationName(stationListItemEntity.getStationName())
      .battalion(stationListItemEntity.getBattalion())
      .build();
  }
}
