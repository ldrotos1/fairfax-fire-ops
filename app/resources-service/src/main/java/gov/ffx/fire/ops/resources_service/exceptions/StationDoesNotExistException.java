package gov.ffx.fire.ops.resources_service.exceptions;

public class StationDoesNotExistException extends Exception {

  public StationDoesNotExistException(Integer stationNumber) {
    super("Station number " + stationNumber.toString() + " does not exist");
  }
}
