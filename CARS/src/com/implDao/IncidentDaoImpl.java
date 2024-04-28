package com.implDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.dao.IncidentDao;
import com.exception.ResourceNotFoundException;
import com.model.Incident;
import com.utility.DBConnection;

public class IncidentDaoImpl implements IncidentDao{

	@Override
	public int save(Incident incident) throws SQLException {
		//Insert incident record in DB
		Connection con =DBConnection.dbConnect();
		String sql="insert into incident (incident_id,incident_type,incident_date,location,description,status)"
				+ "VALUES(?,?,?,?,?,?)";
		// prepared the data
		PreparedStatement pstmt = con.prepareStatement(sql);
		//attach the data
		pstmt.setInt(1, incident.getIncidentID());
		pstmt.setString(2, incident.getIncidentType());
		pstmt.setString(3, incident.getIncidentDate());
		pstmt.setString(4, incident.getLocation());
		pstmt.setString(5, incident.getDescription());
		pstmt.setString(6, incident.getStatus());
		// execute the query
		int result =pstmt.executeUpdate();  //1 if all good,0 if op fails.
		
		DBConnection.dbClose();
		return result;
	}

	@Override
	public void deleteById(int id) throws SQLException, ResourceNotFoundException {
		Connection con = DBConnection.dbConnect();
		String sql = "delete from incident where incident_id = ?";
		//prepare the statement
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		DBConnection.dbClose();
		
	}

	@Override
	public void softDeleteById(int id) throws SQLException, ResourceNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(int id, Incident updateIncident) throws SQLException, ResourceNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Incident> findAll() throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = " SELECT * from incident where status IN ('open','Under Investigation') ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();
		List<Incident>list = new ArrayList<>();
		while(rst.next()== true) {
			int id = rst.getInt("Incident_id");
			String IncidentType = rst.getString("Incident_Type");
			String IncidentDate = rst.getString("Incident_Date");
			String Location = rst.getString("Location");
			String Description = rst.getString("Description");
			String Status = rst.getString("Status");
			Incident incident = new Incident(id,IncidentType,IncidentDate,Location,Description,Status);
			list.add(incident);
		}
		DBConnection.dbClose();
		
		return list;

	}

	@Override
	public Boolean findOne(int id) throws SQLException {
		Connection con = DBConnection.dbConnect();
	    String sql = "Select incident_id from incident where incident_id =?";
	  //prepare the statement
	  		PreparedStatement pstmt = con.prepareStatement(sql);
	  		pstmt.setInt(1, id);
	  		ResultSet rst = pstmt.executeQuery();
	  		boolean status = rst.next(); //true //false
	  		DBConnection.dbClose();
		return status;
		
	}

	@Override
	public void closeIncidentById(int id) throws SQLException, ResourceNotFoundException {
		Connection con = DBConnection.dbConnect();
		String sql = "update incident SET status = 'close' where incident_id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, id);
		pstmt.executeUpdate();
		DBConnection.dbClose();
		
	}

	@Override
	public List<Incident> getIncidentByOfficerId(int officerId) throws SQLException{
		Connection con = DBConnection.dbConnect();
		String sql = "  select * from incident i join incident_officers io ON i.incident_id = io.incident_id"
				+ " Join officers o On io.officers_id=o.officer_id "
				+ "where officer_id = ?; ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, officerId);
		ResultSet rst = pstmt.executeQuery();
		List<Incident>list = new ArrayList<>();
		while(rst.next()== true) {
			int id = rst.getInt("Incident_id");
			String IncidentType = rst.getString("Incident_Type");
			String IncidentDate = rst.getString("Incident_Date");
			String Location = rst.getString("Location");
			String Description = rst.getString("Description");
			String Status = rst.getString("Status");
			Incident incident = new Incident(id,IncidentType,IncidentDate,Location,Description,Status);
			list.add(incident);
		}
		DBConnection.dbClose();
		
		return list;
	}

	@Override
	public List<Incident> getIncidentBySuspectId(int suspectId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "  select * from incident i join suspect s "
				   + "ON i.incident_id = s.incident_id where suspect_id = ?; ";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, suspectId);
		ResultSet rst = pstmt.executeQuery();
		List<Incident>list = new ArrayList<>();
		while(rst.next()== true) {
			int id = rst.getInt("Incident_id");
			String IncidentType = rst.getString("Incident_Type");
			String IncidentDate = rst.getString("Incident_Date");
			String Location = rst.getString("Location");
			String Description = rst.getString("Description");
			String Status = rst.getString("Status");
			Incident incident = new Incident(id,IncidentType,IncidentDate,Location,Description,Status);
			list.add(incident);
		}
		DBConnection.dbClose();
		return list;
	}

}
