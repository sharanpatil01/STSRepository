package com.aashita.random;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aashita.random.csv.reader.CSVFileReaderUtil;
import com.aashita.random.model.Person;

@RestController
public class RandomPersons {
	private static List<CSVRecord> firstnames = new ArrayList<CSVRecord>();
	private static List<CSVRecord> lastnames =  new ArrayList<CSVRecord>();
	static int RPObjectCalled = 0;
	
	RandomPersons() throws IOException{
		firstnames = CSVFileReaderUtil.readFirstNames();
		lastnames =  CSVFileReaderUtil.readLastNames();
	}
	
	public static void main(String[] args) throws IOException {
		RandomPersons rp = new RandomPersons();
		
		List<Person> listp = new ArrayList<Person>();
		listp = rp.getPersons(20);
		
		for(Person p: listp) {
			System.out.println(p);
		}
	}

		

	  @GetMapping("/persons")
	  public @ResponseBody List<Person> getRandomPerson() throws IOException{
		  System.out.println("RPObjectCalled = " + RPObjectCalled++  +";  RandomPersons = "+ this.hashCode() + ";  " + this.firstnames.hashCode());
		  return getPersons(1);
	  }
	  

	  @GetMapping(path="/persons/{count}")
	  public @ResponseBody List<Person> getRandomPersonsByNum(@PathVariable (value = "count") int count ) throws IOException{
		  System.out.println("RPObjectCalled = " + RPObjectCalled++  +";  RandomPersons = "+ this.hashCode()+ ";  " + this.firstnames.hashCode());
		  return getPersons(count);
	  }
	  
	
	  private List<Person> getPersons(int num) throws IOException {
		
		String fname, lname;
		List<Person> lp = new ArrayList<Person>();
		Random random = new Random();
		CSVRecord fnrecord, lnrecord;
		int fnSize = RandomPersons.firstnames.size();
		int lnSize = RandomPersons.lastnames.size();
		
		Person person = new Person();
		
		for(int c=0; c<num; c++) {
			fnrecord = RandomPersons.firstnames.get(random.nextInt(fnSize));
			lnrecord = RandomPersons.lastnames.get(random.nextInt(lnSize));
			
			fname = fnrecord.get(CSVFileReaderUtil.FIRSTNAME_HEADER.firstnames);
			lname = lnrecord.get(CSVFileReaderUtil.LASTNAME_HEADER.lastnames);
			
		//	System.out.println(">>>>\tfirstname : "+  fname +" , lastname : "+  lname );
			 System.out.println("getPersons("+num +"): RPObjectCalled = " + RPObjectCalled  +";  RandomPersons = "+ this.hashCode()+ ";  " + this.firstnames.hashCode());
			 
			person = new Person(fname, lname);
			lp.add(person);
		}
		
		return lp;
	}
}
