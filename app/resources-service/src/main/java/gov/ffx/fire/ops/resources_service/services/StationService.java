package gov.ffx.fire.ops.resources_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.ffx.fire.ops.resources_service.domain.entities.StationEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.StationListItemEntity;
import gov.ffx.fire.ops.resources_service.domain.mappers.StationMapper;
import gov.ffx.fire.ops.resources_service.domain.models.Station;
import gov.ffx.fire.ops.resources_service.domain.models.StationListItem;
import gov.ffx.fire.ops.resources_service.repositories.StationListItemRepository;
import gov.ffx.fire.ops.resources_service.repositories.StationRepository;

@Service
public class StationService {

  @Autowired
  private StationRepository stationRepo;

  @Autowired
  private StationListItemRepository stationListItemRepository;

  /**
   * Get a single station by station number
   * 
   * @param stationNumber
   * @return The station
   */
  public Station getStation(int stationNumber) {
    StationEntity stationEntity = stationRepo.findByStationNumber(stationNumber);
    return StationMapper.stationEntityToStation(stationEntity);
  }

  /**
   * Get a list of all stations
   * 
   * @return A list of stations
   */
  public List<StationListItem> getStationList() {
    List<StationListItemEntity> stationItemEntities = stationListItemRepository.findAll();
    return stationItemEntities.stream()
      .map(StationMapper::stationListItemEntityToStationListItem)
      .collect(Collectors.toList());
  }
}
