package gov.ffx.fire.ops.resources_service.domain.mappers;

import org.springframework.stereotype.Component;

import gov.ffx.fire.ops.resources_service.domain.entities.StationEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.StationListItemEntity;
import gov.ffx.fire.ops.resources_service.domain.models.Station;
import gov.ffx.fire.ops.resources_service.domain.models.StationListItem;

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
      .stationNumber(stationEntity.getStationNumber())
      .stationName(stationEntity.getStationName())
      .battalion(stationEntity.getBattalion())
      .isVolunteer(stationEntity.isVolunteer())
      .address(stationEntity.getAddress())
      .city(stationEntity.getCity())
      .state(stationEntity.getState())
      .zipCode(stationEntity.getZipCode())
      .phoneNumber(stationEntity.getPhoneNumber())
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
      .stationNumber(stationListItemEntity.getStationNumber())
      .stationName(stationListItemEntity.getStationName())
      .battalion(stationListItemEntity.getBattalion())
      .build();
  }

}
