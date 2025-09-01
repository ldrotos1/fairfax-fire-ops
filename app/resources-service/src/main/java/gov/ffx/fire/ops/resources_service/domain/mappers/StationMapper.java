package gov.ffx.fire.ops.resources_service.domain.mappers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import gov.ffx.fire.ops.models.station.Station;
import gov.ffx.fire.ops.models.station.StationListItem;
import gov.ffx.fire.ops.resources_service.domain.entities.StationEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.StationListItemEntity;


@Component
public class StationMapper {

  /**
   * Converts a {@link StationEntity} to a {@link Station}
   * 
   * @param stationEntity
   * @return
   */
  public static Station stationEntityToStation(StationEntity stationEntity) {
    return Station.builder()
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
   * Converts a {@link Station} to a {@link StationEntity}
   * 
   * @param stationListItemEntity
   * @return
   */
  public static StationListItem  stationListItemEntityToStationListItem(StationListItemEntity stationListItemEntity) {
    return StationListItem.builder()
      .stationDesignator(stationListItemEntity.getStationDesignator())
      .stationName(stationListItemEntity.getStationName())
      .battalion(stationListItemEntity.getBattalion())
      .build();
  }
}
