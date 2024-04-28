package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.IncidentDao;
import com.implDao.IncidentDaoImpl;
import com.exception.ResourceNotFoundException;
import com.model.Incident;

public class IncidentService {

	IncidentDao dao = new IncidentDaoImpl();
	
	public int insert(Incident incident) throws SQLException {
		
	return dao.save(incident);
	
	}

	public void deleteByid(int id) throws SQLException, ResourceNotFoundException {
		boolean isIdValid =dao.findOne(id);
		if(!isIdValid)
			throw new ResourceNotFoundException("Id Given is invalid");
		
			dao.deleteById(id);
			
		
	}

	public void closeIncidentByid(int id) throws SQLException, ResourceNotFoundException {
		boolean isIdValid = dao.findOne(id);
		if(!isIdValid)
			throw new ResourceNotFoundException("Id given is Invalid !!");
		
		dao.closeIncidentById(id);
		
	}

	public List<Incident> findAll() throws SQLException {
		
		return dao.findAll();
	}

	public List<Incident> getIncidentByOfficerId(int officerId) throws SQLException {
		
		return dao.getIncidentByOfficerId(officerId);
	}

	public List<Incident> getIncidentBySuspectId(int suspectId) throws SQLException {
		
		return dao.getIncidentBySuspectId(suspectId);
	}

}
