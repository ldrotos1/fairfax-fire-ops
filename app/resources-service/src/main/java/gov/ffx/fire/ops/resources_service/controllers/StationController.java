package gov.ffx.fire.ops.resources_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.ffx.fire.ops.models.station.Station;
import gov.ffx.fire.ops.models.station.StationListItem;
import gov.ffx.fire.ops.resources_service.exceptions.StationDoesNotExistException;
import gov.ffx.fire.ops.resources_service.services.StationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/station", produces = "application/json")
@Tag(name = "Station API", description = "Operations on stations")
public class StationController {

  @Autowired
  private StationService stationService;

  @GetMapping(value = "/{stationDesignator}")
  @Operation(summary = "Get a single station", description = "Get a single station by its designator")
  public Station getStation(@PathVariable @Min(401) @Max(444) Integer stationDesignator) throws StationDoesNotExistException {
    return stationService.getStation(stationDesignator);
  }

  @GetMapping(value = "/list")
  @Operation(summary = "Get a list of stations", description = "Get a list of all stations")
  public List<StationListItem> getStationList() {
    return stationService.getStationList();
  }
}
