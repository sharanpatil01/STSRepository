package com.aashita.random.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aashita.random.health.HealthCheck;
import com.aashita.random.model.Employee;
import com.aashita.random.model.Person;

@RequestMapping("/api/v1/")
@RestController
public class EmployeesApi {
	private static final int MAX_EMPLOYEES = 300;
	Optional<String> randomId = Optional.of("true-not");

	
	private static ArrayList<Employee> empList =  new ArrayList<Employee>();
	
	private ArrayList<Employee> empArr = new ArrayList<Employee>();

	private enum DESIGNATIONS {
		manager, techlead, lead, developer, srdeveloper, qa, pmo, architect, scrummaster, ba
	}

	private EmployeesApi() {
		 getEmpList();  //create and populate list with employees.
	}
	
	
	private void getEmpList(){
		Random ran = new Random();

		try {
			//CsvRandomPersons rp = new CsvRandomPersons();
			MemRandomPersons rp = new MemRandomPersons();
			if(randomId.get().matches("true"))
				randomId = Optional.of("true-not");
			else
				randomId = Optional.of("true");
			
			List<Person> listPerson = rp.getRandomPersonsByNum(MAX_EMPLOYEES, randomId);
			Employee emp = null;
			int idx;
			int c = 1;

			 synchronized(empList) {
				 empList.clear();
				 for (Person p : listPerson) {
					 idx = ran.nextInt(9);
					 String randesig = "" + DESIGNATIONS.values()[idx];
					 
					 emp = new Employee.Builder(p.getId()).age(ran.nextInt(59))
							 .name(p.getfName().concat(" ").concat(p.getlName())).desig(randesig).sal(ran.nextInt(200000))
							 .build();
 					 empList.add(emp);
					 // System.out.println(c++ + " : " + emp);
				 }
			 }
		  
			System.err.println("Employee List Size  : " + empList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@GetMapping("/employees")
	public @ResponseBody ArrayList<Employee> getAllEmployee() {
		HealthCheck.appDuration();
		return empList;
	}

	@GetMapping("/employees/renew")
	public @ResponseBody ArrayList<Employee> getNewEmpList(){
		getEmpList();
		return empList;
	}

	@GetMapping(path = "/employees/{id}")
	public ResponseEntity<ArrayList<Employee>> getEmployee(@PathVariable int id) {
		HealthCheck.appDuration();
		empArr = new ArrayList<Employee>();
		if (empList == null || empList.isEmpty()) {
			System.out.println("emplist is empty or null!");
			
			
			return new ResponseEntity<ArrayList<Employee>>(empArr, HttpStatus.NOT_FOUND);
		} else {
			id = Math.abs(id);
			if (id >= empList.size()) {
				return new ResponseEntity<ArrayList<Employee>>(empArr, HttpStatus.NOT_FOUND);
			}
			empArr.clear();
			empArr.add(empList.get(id));

			 return new ResponseEntity<ArrayList<Employee>>(empArr, HttpStatus.OK);

		}

	}

	@PostMapping(path = "/employees", consumes=MediaType.APPLICATION_JSON_VALUE  ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArrayList<Employee>> addMemberV2(@RequestBody Employee employee) {
		
		synchronized (empList) {
			empList.add(employee);			
		}
		
		empArr.clear();
		empArr.add(employee);

		System.out.println("POST method invoked on Employee  : " + employee  + ";   company headcount = "+ empList.size());
		return new ResponseEntity<ArrayList<Employee>>(empArr, HttpStatus.CREATED);

	}

	
	@DeleteMapping(value = "/employees/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) 
	{
		Employee employee = null;
		id = Math.abs(id);
		if (id > empList.size()  ) {
			id = empList.size();
		}
		
		
		if (!empList.isEmpty()) {
			employee = empList.get(id);

			synchronized (empList) {
				empList.remove(id);
			}
			
		}
		
		System.out.println("DELETEing ("+ id +") method invoked on Employee  : " + employee  + ";   company headcount = "+ empList.size());
	    return new ResponseEntity<String>("emp count : "+ empList.size(),  HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/employees/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		if (empList == null || empList.isEmpty()) {
			synchronized (empList) {
				empList.add(employee);			
			}
		}
		id = Math.abs(id);
		
		if (id < empList.size()) {
			
			synchronized (empList) {
				Employee emp = empList.get(id);
				emp.setId(employee.getId());
				emp.setName(employee.getName());
				emp.setAge(employee.getAge());
				emp.setDesignation(employee.getDesignation());
				
				try {
					Float sal = Float.parseFloat(employee.getSalary());
					emp.setSalary(sal.floatValue());
				}catch (NumberFormatException e) {
					System.err.println(e.getMessage());
				}
			}
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}else {
			employee.setRemark("employee record not UPDATED!!!");
			return new ResponseEntity<Employee>(employee, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public static void main(String[] args) {

		new EmployeesApi();

	}
	
	//========random phone number generator=================
	@GetMapping("/phonenumber")
	public @ResponseBody Long getRandomPhoneNum(){
		return PhoneNumbers.getPhoneNumber();
	}

	
	@GetMapping("/usphonenumber")
	public @ResponseBody String getUSRandomPhoneNum(){
		return PhoneNumbers.getUSPhoneNumber();
	}

	@GetMapping("/usphonenumbers")
	public @ResponseBody ArrayList<String> getUSPhoneList(){
		ArrayList<String> phonenumlist =new ArrayList<String>();
		phonenumlist.add(PhoneNumbers.getUSPhoneNumber());
		phonenumlist.add(PhoneNumbers.getUSPhoneNumber());
		phonenumlist.add(PhoneNumbers.getUSPhoneNumber());
		phonenumlist.add(PhoneNumbers.getUSPhoneNumber());
		phonenumlist.add(PhoneNumbers.getUSPhoneNumber());
			return phonenumlist;
	}

	@GetMapping("/phonenumbers")
	public @ResponseBody ArrayList<Long> getPhoneList(){
		ArrayList<Long> phonenumlist =new ArrayList<Long>();
		phonenumlist.add(PhoneNumbers.getPhoneNumber());
		phonenumlist.add(PhoneNumbers.getPhoneNumber());
		phonenumlist.add(PhoneNumbers.getPhoneNumber());
		phonenumlist.add(PhoneNumbers.getPhoneNumber());
		phonenumlist.add(PhoneNumbers.getPhoneNumber());
			return phonenumlist;
	}
	
	@GetMapping("")
	public @ResponseBody String home(){
		return "Welcome to Random Api";
	}
	
	
	@GetMapping("/ping")
	public @ResponseBody String ping() {
		return "ping is successfull!";
	}
}
