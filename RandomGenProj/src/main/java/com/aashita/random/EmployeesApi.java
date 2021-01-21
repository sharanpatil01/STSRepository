package com.aashita.random;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aashita.random.model.Employee;
import com.aashita.random.model.Person;

@RestController
public class EmployeesApi {

	private static ArrayList<Employee> empList = new ArrayList<Employee>();
	private ArrayList<Employee> empArr = new ArrayList<Employee>();

	private enum DESIGNATIONS {
		manager, techlead, lead, developer, srdeveloper, qa, pmo, architect, scrummaster, ba
	}

	public EmployeesApi() {
		Random ran = new Random();

		try {
			RandomPersons rp = new RandomPersons();
			Optional<String> randomId = Optional.of("true-not");

			List<Person> listPerson = rp.getRandomPersonsByNum(10, randomId);
			Employee emp = null;
			int idx;
			int c = 1;

			for (Person p : listPerson) {
				idx = ran.nextInt(9);
				String randesig = "" + DESIGNATIONS.values()[idx];

				emp = new Employee.Builder(p.getId()).age(ran.nextInt(59))
						.name(p.getfName().concat(" ").concat(p.getlName())).desig(randesig).sal(ran.nextInt(200000))
						.build();
				empList.add(emp);
				// System.out.println(c++ + " : " + emp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/employees")
	public @ResponseBody ArrayList<Employee> getAllEmployee() {
		return empList;
	}


	@GetMapping(path = "/employees/{id}")
	public ResponseEntity<ArrayList<Employee>> getEmployee(@PathVariable int id) {
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

		empList.add(employee);
		
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
			empList.remove(id);
			
		}
		
		System.out.println("DELETEing ("+ id +") method invoked on Employee  : " + employee  + ";   company headcount = "+ empList.size());
	    return new ResponseEntity<String>("emp count : "+ empList.size(),  HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/employees/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		if (empList == null || empList.isEmpty()) {
			this.empList.add(employee) ;
		}
		id = Math.abs(id);
		
		if (id < empList.size()) {
			
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
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}else {
			employee.setRemark("employee record not UPDATED!!!");
			return new ResponseEntity<Employee>(employee, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public static void main(String[] args) {

		new EmployeesApi();

	}

}
