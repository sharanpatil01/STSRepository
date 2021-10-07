package com.aashita.random.model;

import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.aashita.random.CsvRandomPersons;

public class Employee implements Serializable {

	// "id":24,
	// "employee_name":"Doris Wilder",
	// "employee_salary":85600,
	// "employee_age":23

	private String id, name, designation;
	private int  age;
	private float salary = 100000.59f;
	private String remark="";

	NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "in"));
	
	public static class Builder {
		private String id;
		private int age;
		private float sal;
		private String name, desig;


		public Builder(String id) {
			this.id = id;
		}


		public Builder age(int ag) {
			this.age = ag;
			return this;
		}

		public Builder sal(int sl) {
			this.sal = sl;
			return this;
		}

		public Builder name(String n) {
			this.name = n;
			return this;
		}

		public Builder desig(String d) {
			this.desig = d;
			return this;
		}

		public Employee build() {
			return new Employee(this);
		}
	}

	public Employee() {
		
	}
	
	public Employee(Builder b) {
		this.id = b.id;
		this.age = b.age;
		this.salary = b.sal;
		this.name = b.name;
		this.designation = b.desig;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	private float getSalaryAmount() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", age=" + age + ", name=" + name + ", designation=" + designation + ", salary="
				+ getSalary() + "]";
	}

	public String getSalary() {
		String moneyString = formatter.format(salary);
		return moneyString;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static void main(String... args) throws IOException {
		CsvRandomPersons rp = new CsvRandomPersons();
		Employee emp = null;
		List<Employee> empList = new ArrayList<Employee>();
		Optional<String> randomId = Optional.of("true");

		List<Person> lp = rp.getRandomPersonsByNum(200, randomId);

		 emp = new Employee.Builder("S10").age(22).name("Raj").desig("Manager").sal(125000).build();
		 
		 System.out.println(emp);
	}

}
