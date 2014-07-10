package carrental.repository.impl.jpa;

import java.util.List;

import org.joda.time.Interval;
import org.springframework.stereotype.Repository;

import carrental.domain.Vehicle;
import carrental.repository.VehicleDao;
import carrental.repository.impl.DefaultGenericCrudDao;

@Repository
public class JpaVehicleDaoImpl extends DefaultGenericCrudDao<Vehicle> implements VehicleDao {

	@Override
	public List<Vehicle> findAvailable(Interval interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interval> findWhenBooked(Integer vehicleId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
