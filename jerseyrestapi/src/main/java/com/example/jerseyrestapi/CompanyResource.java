/**
 * 
 */
package com.example.jerseyrestapi;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


/**
 * @author Rohit Kapade
 *
 */
@Path("/companyinfo")
public class CompanyResource {

	EmployeeRepository empRepo;
		
	public CompanyResource() {
		super();
		empRepo = new EmployeeRepository();
	}
	
	public EmployeeRepository getEmpRepo() {
		return empRepo;
	}
	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}
	
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String getText()
//	{
//		return "My Text from company info";
//	}
//	
//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	public Employee getEmployee()
//	{
//		System.out.println("getEmployee() function called ");
//		
//		Employee e1 = new Employee();
//		e1.setId(10);
//		e1.setName("Rohit Kapade");
//		e1.setSalary(12000);
//		
//		return e1;
//	}
	
//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	public List<Employee> getAllEmployees()
//	{
//		System.out.println("getAllEmployees() function called ");
//		
//		Employee e1 = new Employee();
//		e1.setId(10);
//		e1.setName("Rohit Kapade");
//		e1.setSalary(12000);
//		
//		Employee e2 = new Employee();
//		e2.setId(20);
//		e2.setName("Mahipal Singh");
//		e2.setSalary(10000);
//		
//		Employee e3 = new Employee();
//
//		List<Employee> eList = new ArrayList<Employee>();
//		
//		eList.add(e2);
//		eList.add(e1);
//		eList.add(e3);
//		
//		return eList;
//	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Employee> getAllEmployees()
	{
		System.out.println("getAllEmployees() resource function calls empRepository database object");
		return empRepo.getEmpList();
	}
	
	@GET
	@Path("employee/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})  // Server to Client
	public Employee getEmployee(@PathParam("id") int  id)
	{
		System.out.println("getEmployee() resource function calls empRepository database object");
		if( null == empRepo.getEmployee(id))
		{
			System.out.println("Error, Employee ID does not found !!");
			return null;
		}	
		return empRepo.getEmployee(id);
	}

	@POST
	@Path("employee")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})  //client to Server 
	public Employee createEmployee(Employee e) {
		System.out.println("Resource.createEmployee() called ...");
		empRepo.create(e);
		System.out.println("Resource -->Repository --> object");
		System.out.println(e);
		return e;
	}
	

	@PUT
	@Path("employee")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})  //client to Server 
	public Employee updateEmployee(Employee e) {
		System.out.println(e);
		if(null == empRepo.getEmployee(e.getId())) {
			System.out.println("****Warning******* can't update, Hence let's create new object");
			empRepo.create(e);
		}
		else {
			empRepo.update(e);	
		}
		return e;
	}
	
	@DELETE
	@Path("employee")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})  //client to Server 
	public Employee deleteEmployee(Employee e) {
		System.out.println(e);
		if(null == empRepo.getEmployee(e.getId())) {
			System.out.println("****Error******* can't delete the null object");
		}
		else {
			empRepo.delete(e);	
		}		
		return e;
	}
	
	
//	@GET
//	@Produces(MediaType.APPLICATION_XML)
//	public Department getDepartment()
//	{
//		System.out.println("getDepartment() called...");
//		
//		Department d1 = new Department();
//		d1.setId(1);
//		d1.setName("CS");
//		
//		return d1;
//	}
	
	
	
}
