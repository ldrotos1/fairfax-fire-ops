package gov.ffx.fire.ops.resources_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.ffx.fire.ops.resources_service.domain.entities.CountyStationEntity;
import gov.ffx.fire.ops.resources_service.domain.entities.CountyStationListItemEntity;
import gov.ffx.fire.ops.resources_service.domain.mappers.StationMapper;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStation;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStationListItem;
import gov.ffx.fire.ops.resources_service.repositories.CountyStationListItemRepository;
import gov.ffx.fire.ops.resources_service.repositories.CountyStationRepository;

@Service
public class StationService {

  @Autowired
  private CountyStationRepository countyStationRepo;

  @Autowired
  private CountyStationListItemRepository countyStationListItemRepository;

  /**
   * Get a single county station by station designator
   * 
   * @param stationDesignator
   * @return The station
   */
  public CountyStation getCountyStation(int stationDesignator) {
    CountyStationEntity stationEntity = countyStationRepo.findByStationDesignator(stationDesignator);
    return StationMapper.stationEntityToStation(stationEntity);
  }

  /**
   * Get a list of all county stations
   * 
   * @return A list of county stations
   */
  public List<CountyStationListItem> getCountyStationList() {
    List<CountyStationListItemEntity> stationItemEntities = countyStationListItemRepository.findAll();
    return stationItemEntities.stream()
      .map(StationMapper::stationListItemEntityToStationListItem)
      .collect(Collectors.toList());
  }
}
