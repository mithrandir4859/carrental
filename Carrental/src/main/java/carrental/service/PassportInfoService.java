package carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import carrental.domain.PassportInfo;
import carrental.repository.PassportInfoDao;

@Service
public class PassportInfoService {
	@Autowired private PassportInfoDao passportInfoDao;
	
	public void save(PassportInfo passportInfo, Integer userId){
		passportInfo.setUserId(userId);
		save(passportInfo);
	}
	
	public void save(PassportInfo passportInfo){
		Integer userId = passportInfo.getUserId();
		if (passportInfoDao.find(userId) == null)
			passportInfoDao.create(passportInfo);
		else
			passportInfoDao.update(passportInfo);
	}

}
