package com.aashita.random;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.aashita.random.model.Employee;

public class EmployeesApiTest {

	
	private static void deleteEmployee()
	{
	    final String uri = "http://localhost:8080/employees/1";
	    RestTemplate restTemplate = new RestTemplate();
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "2");
	     
	    restTemplate.delete ( uri,  params );
	}


	private static void updateEmployee() 
	{
	    final String uri = "http://localhost:8080/employees/2";
	    RestTemplate restTemplate = new RestTemplate();
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "2");
	     
	    Employee updatedEmployee = new Employee.Builder("1").age(44).name("Gilly").desig("contractor").sal(123456).build();
	    
//	    ObjectMapper mapper = new ObjectMapper();
	    String jsonString = "{\r\n" + 
	    		"\"id\" : 1,\r\n" + 
	    		"\"name\" : \"urmi mat\",\r\n" + 
	    		"\"age\": 44,\r\n" + 
	    		"\"designation\" : \"actress\",\r\n" + 
	    		"\"salary\": 123456.0,\r\n" + 
	    		"\"remark\": \"update this record\"\r\n" + 
	    		"}";

	    // Java object to JSON string
//	    try {
//			 jsonString = mapper.writeValueAsString(updatedEmployee);
//		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    restTemplate.put ( uri, jsonString, params );
	}
	
	private static void createEmployee()
	{
	    final String uri = "http://localhost:8080/api/v1/employees";
	    RestTemplate restTemplate = new RestTemplate();
	 
	    Employee newEmployee = new Employee.Builder("3").age(44).name("Billu").desig("contractor").sal(123456).build();
	    
	    Employee result = restTemplate.postForObject( uri, newEmployee, Employee.class);
	 
	    System.out.println(result);
	}
	
	private static void getEmployees()
	{
	    final String uri = "http://localhost:8080/api/v1/employees";
	    RestTemplate restTemplate = new RestTemplate();
	 
	    String response = restTemplate.getForObject(uri, String.class);
	 
	    System.out.println(response);
	    //Use the response.getBody()
	}
	public static void main(String[] args) {
//		createEmployee();
		getEmployees();
		//		updateEmployee();
//		deleteEmployee();
	}

}
