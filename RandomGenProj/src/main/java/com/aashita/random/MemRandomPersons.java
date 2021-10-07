package com.aashita.random;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.aashita.random.data.FirstNames;
import com.aashita.random.data.LastNames;
import com.aashita.random.model.Person;

@RequestMapping("/api/mem/v1")
@RestController
public class MemRandomPersons {
	private static List<String> firstnames = new ArrayList<String>();
	private static List<String> lastnames = new ArrayList<String>();
	static int RPObjectCalled = 0;

	public MemRandomPersons() throws IOException {
		firstnames =  Arrays.asList(  FirstNames.firstnames);
		lastnames  =  Arrays.asList( LastNames.lastnames);
	}

	public static void main(String[] args) throws IOException {
		MemRandomPersons rp = new MemRandomPersons();
		List<Person> listp = new ArrayList<Person>();
		listp = rp.getPersons(20, true);

		for (Person p : listp) {
			System.out.println(p);
		}
	}

	@GetMapping("/persons")
	public @ResponseBody List<Person> getRandomPerson() throws IOException {
//		  System.out.println("RPObjectCalled = " + RPObjectCalled++  +";  RandomPersons = "+ this.hashCode() + ";  " + this.firstnames.hashCode());
		return getPersons(1, true);
	}

	@GetMapping(path = "/persons/{count}")
	public @ResponseBody List<Person> getRandomPersonsByNum(@PathVariable(value = "count") int count,
			@RequestParam Optional<String> randomId) throws IOException {

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
		int fnSize = MemRandomPersons.firstnames.size()-1;
		int lnSize = MemRandomPersons.lastnames.size()-1;

		Person person = new Person();

		for (int c = 0; c < num; c++) {
			
			fname = MemRandomPersons.firstnames.get(random.nextInt(fnSize));
			lname = MemRandomPersons.lastnames.get(random.nextInt(lnSize));

			person = new Person(fname, lname);
			if (randomIdGenerate)
				person.setId(random.nextInt(10000) + "");
			else
				person.setId((c + 1) + "");

//			System.out.println("getPersons(" + (c+1) + "): >>>>\tid : "+ person.getId() + ", firstname : " +  fname + ", lastname : "+  lname );
			lp.add(person);
		}

		return lp;
	}

}
