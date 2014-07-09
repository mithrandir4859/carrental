package carrental.repository;

import java.util.List;

import org.joda.time.Interval;

import carrental.domain.Vehicle;


public interface VehicleDao extends GenericCrudDao<Vehicle>{
	List<Vehicle> findAvailable(Interval interval);
	List<Interval> findWhenBooked(Integer vehicleId) throws IllegalArgumentException;
}
