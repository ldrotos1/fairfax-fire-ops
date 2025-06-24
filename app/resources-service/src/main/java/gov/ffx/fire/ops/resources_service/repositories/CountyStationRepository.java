package gov.ffx.fire.ops.resources_service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.ffx.fire.ops.resources_service.domain.entities.CountyStationEntity;

@Repository
public interface CountyStationRepository extends JpaRepository<CountyStationEntity, Integer> {

  Optional<CountyStationEntity> findByStationDesignator(Integer stationDesignator);
}
