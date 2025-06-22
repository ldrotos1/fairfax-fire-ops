package gov.ffx.fire.ops.resources_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.ffx.fire.ops.resources_service.domain.entities.CountyStationListItemEntity;

@Repository
public interface CountyStationListItemRepository extends JpaRepository<CountyStationListItemEntity, Integer>  {
  
}
