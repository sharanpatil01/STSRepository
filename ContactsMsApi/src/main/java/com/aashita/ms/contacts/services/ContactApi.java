package com.aashita.ms.contacts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aashita.ms.contacts.model.Contact;


@RestController
@RequestMapping("/api")
public class ContactApi {

	Logger logger = Logger.getLogger("com.aashita.ms.contacts.services.ContactApi");
	private Random ran = new Random();
	private static ConcurrentHashMap<String, Contact> contactBook = new ConcurrentHashMap<String, Contact>();

	@GetMapping("/{id}")
	public Contact getContact(@PathVariable String id) {
		logger.fine("getContact() called!");
		Contact con = new Contact();
		con = contactBook.get(id);
		return con;
	}

	@GetMapping("/")
	public List<Contact> getContacts() {
		logger.fine("getContacts() called!");
		return new ArrayList<Contact>( contactBook.values());
	}

	@PostMapping("/")
	public void addContact(@RequestBody Contact contact) {

		logger.fine("addContact() called!");
		
		//+"__"+ran.nextInt()%10000
		contactBook.put(contact.getId(), contact);
	}

}
