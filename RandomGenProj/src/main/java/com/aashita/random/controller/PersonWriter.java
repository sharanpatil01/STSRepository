package com.aashita.random.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aashita.random.model.Person;

public class PersonWriter {

	public List<Person> getPersons(final int count) {
		String fname, lname, city, state;
		RandomUtil ru = new RandomUtil();
		List<Person> personsList = new ArrayList<Person>();
		Person prsn;
		ru.println("Testing random Person names");
		for (int i = 0; i < count; i++) {
			fname = ru.getRandomFName();
			lname = ru.getRandomLName();
			city = ru.getRandomCity();
			state = ru.getRandomState();
			prsn = new Person(fname, lname, city, state);
			prsn.setId(ru.getRandomId());
			prsn.setZipcode(ru.getRandomZipCode());

			personsList.add(prsn);
			ru.println(ru.getNDigitNo(i, 2)+ " : " +prsn);
		}
		return personsList;
	}

	public String getInsertQuery(Person prsn) {
		StringBuilder query = new StringBuilder("");
		query.append("INSERT INTO SRNPERSONS VALUES ('");
		query.append(prsn.getId()).append("','");
		query.append(prsn.getfName()).append("','");
		query.append(prsn.getlName()).append("','");
		query.append(prsn.getAddress()).append("','");
		query.append(prsn.getCity()).append("','");
		query.append(prsn.getState()).append("','");
		query.append(prsn.getZipcode()).append("')");

		return query.toString();
	}

	public void insertPerson(Person prsn, Connection con) throws SQLException {
		String sql = getInsertQuery(prsn);
		Statement st = con.createStatement();
		st.execute(sql);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person p1 = new Person();

		p1.setId("100");
		p1.setfName("Sharan");
		p1.setlName("Patil");
		p1.setCity("Hyd");
		p1.setState("AP");
		p1.setZipcode("50030");

		System.out.println(p1);

		PersonWriter pw = new PersonWriter();
		pw.getPersons(40);
	}

}
