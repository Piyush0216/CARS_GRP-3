package com.controller;
import java.sql.SQLException;
import java.util.*;

import com.exception.ResourceNotFoundException;
import com.model.Incident;
import com.service.IncidentService;


public class IncidentController {
	
	public static void main(String[] args) {
			IncidentService incidentService = new IncidentService();
			Scanner sc = new Scanner(System.in);
			while(true) {
				System.out.println("----------Incident Ops-------------");
				System.out.println("Press 1. Insert Incident ");
				System.out.println("Press 2. Delete Incident ");
				System.out.println("Press 3. close Incident  ");
				System.out.println("Press 4. Display active Incidents");
				System.out.println("press 5. Display Incident for officer Id");
				System.out.println("press 6. Display Incident for suspect Id");
				System.out.println("Press 0. to Exit");
				int input = sc.nextInt();
				if(input == 0) {
					System.out.println("Exiting Incident Ops..");
					break; 
				
				}
				switch(input) {
				case 1:
					
					// System.out.println(UUID.randomUUID()); -- auto generating for ID
					Random random = new Random();
					int randomNumber = random.nextInt();
					int id = randomNumber<0?randomNumber*-1:randomNumber;
					System.out.println("Enter Incident Type");
					sc.nextLine();
					String incidentType = sc.nextLine();
					System.out.println("Enter Incident Date (yyyy-MM-dd): ");
		            String incidentDate = sc.nextLine();
		            System.out.println("Enter Location: ");
		            String location = sc.nextLine();
		            System.out.println("Enter Description: ");
		            String description = sc.nextLine();
		            System.out.println("Enter Status: ");
		            String status = sc.nextLine();
		           
		            /*Attach values to object*/
		            
					Incident incident = new Incident(id,incidentType,incidentDate,location,description,status);
					try {
						int result = incidentService.insert(incident);
						if (result == 1)
							System.out.println("Incident added to DB");
						else
							System.out.println("Insert op failed");
					} catch (SQLException e) {
						
						System.out.println("SQL structure Invalid");
					}
				break;	
				
				case 2:
					try {
						List<Incident>a = incidentService.findAll();
						for(Incident i : a) 
							System.out.println(i);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Enter Incident ID");
					try {
					incidentService.deleteByid(sc.nextInt());
					System.out.println("Incident record deleted");
					} catch(SQLException e) {
						System.out.println(e.getMessage());
					} catch (ResourceNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
					break;
				case 3: 
					try {
						List<Incident>a = incidentService.findAll();
						for(Incident i : a) 
							System.out.println(i);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					System.out.println("Enter Incident ID");
				try {
					incidentService.closeIncidentByid(sc.nextInt());
					System.out.println("Incident closed");
				} catch(ResourceNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
	            break;
	            
				case 4:
					try {
						List<Incident> list = incidentService.findAll();
						for(Incident i : list) {
							System.out.println(i);
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					
					break;
					
				case 5:
					System.out.println("Enter officer id");
					int officerId = sc.nextInt();
					
					
					try {
						List<Incident> list =incidentService.getIncidentByOfficerId(officerId);
						for(Incident i : list) {
							System.out.println(i);}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					break;
				case 6 :
					
					System.out.println("Enter suspect id");
					int suspectId = sc.nextInt();
					
					 
					try {
						List<Incident>list = incidentService.getIncidentBySuspectId(suspectId);
						for(Incident i : list) {
							System.out.println(i);}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					break;
					
					default :
						System.out.println("Invalid choice Please try again");
				}					
	}

			sc.close();
	}

	
}

