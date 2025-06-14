package gov.ffx.fire.ops.resources_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.ffx.fire.ops.resources_service.domain.models.Station;
import gov.ffx.fire.ops.resources_service.domain.models.StationListItem;
import gov.ffx.fire.ops.resources_service.services.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/station", produces = "application/json")
@Tag(name = "Station API", description = "Operations on stations")
public class StationController {

  @Autowired
  private StationService stationService;

  @GetMapping(value = "/{stationNumber}")
  @Operation(summary = "Get a single station", description = "Get a single station by its number")
  public Station getStation(@PathVariable int stationNumber) {
    return stationService.getStation(stationNumber);
  }

  @GetMapping(value = "/list")
  @Operation(summary = "Get a list of stations", description = "Get a list of all stations")
  public List<StationListItem> getStationList() {
    return stationService.getStationList();
  }
}
