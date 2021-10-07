package com.aashita.random;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.csv.CSVRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aashita.random.csv.reader.CSVFileReaderUtil;
import com.aashita.random.model.Person;

@RequestMapping("/api/v1")
@RestController
public class RandomPersons {
	private static List<CSVRecord> firstnames = new ArrayList<CSVRecord>();
	private static List<CSVRecord> lastnames =  new ArrayList<CSVRecord>();
	static int RPObjectCalled = 0;
	
	public RandomPersons() throws IOException{
		firstnames = CSVFileReaderUtil.readFirstNames();
		lastnames =  CSVFileReaderUtil.readLastNames();
	}
	
	public static void main(String[] args) throws IOException {
		RandomPersons rp = new RandomPersons();
		
		List<Person> listp = new ArrayList<Person>();
		listp = rp.getPersons(20, true);
		
		for(Person p: listp) {
			System.out.println(p);
		}
	}

		

	  @GetMapping("/persons")
	  public @ResponseBody List<Person> getRandomPerson() throws IOException{
//		  System.out.println("RPObjectCalled = " + RPObjectCalled++  +";  RandomPersons = "+ this.hashCode() + ";  " + this.firstnames.hashCode());
		  return getPersons(1, true);
	  }
	  

	  @GetMapping(path="/persons/{count}")
	  public @ResponseBody List<Person> getRandomPersonsByNum(@PathVariable (value = "count") int count, @RequestParam  Optional<String> randomId ) throws IOException{
		
		
		boolean randomIdGenerate = false;
		randomIdGenerate = randomId.orElse("false").equalsIgnoreCase("true");
//		System.out.println( "randomIdGenerate  = " + randomIdGenerate);
		
//		System.out.println("RPObjectCalled = " + RPObjectCalled++  +";  RandomPersons = "+ this.hashCode()+ ";  " + this.firstnames.hashCode());
		return getPersons(count, randomIdGenerate);
	  }
	  
	
	  private List<Person> getPersons(int num, boolean randomIdGenerate) throws IOException {
		
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
			
			  
			person = new Person(fname, lname);
			if (randomIdGenerate)
				person.setId(random.nextInt(10000) + "");
			else
				person.setId((c+1)+"");
			
//			System.out.println("getPersons(" + (c+1) + "): >>>>\tid : "+ person.getId() + ", firstname : " +  fname + ", lastname : "+  lname );
			lp.add(person);
		}
		
		return lp;
	}
}
