package gov.ffx.fire.ops.resources_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.ffx.fire.ops.resources_service.domain.models.CountyStation;
import gov.ffx.fire.ops.resources_service.domain.models.CountyStationListItem;
import gov.ffx.fire.ops.resources_service.exceptions.StationDoesNotExistException;
import gov.ffx.fire.ops.resources_service.services.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/station", produces = "application/json")
@Tag(name = "Station API", description = "Operations on stations")
public class StationController {

  @Autowired
  private StationService stationService;

  @GetMapping(value = "/{stationDesignator}")
  @Operation(summary = "Get a single county station", description = "Get a single county station by its designator")
  public CountyStation getCountyStation(@PathVariable int stationDesignator) throws StationDoesNotExistException {
    return stationService.getCountyStation(stationDesignator);
  }

  @GetMapping(value = "/list")
  @Operation(summary = "Get a list of county stations", description = "Get a list of all county stations")
  public List<CountyStationListItem> getCountyStationList() {
    return stationService.getCountyStationList();
  }
}
