package com.camunda.surge;

/**
 * @author Kadirul Chowdhury
 *
 */

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorProcess {
	// auto wiring process engine to execute process services, it is required
	@Autowired
	private ProcessEngine process;

	// invoke the variables from the customer form using delegate
	String VENDOR = "";
	String EMAIL = "";
	String ADDRESS = "";

	@RequestMapping("/startVendorProcess")
	public void startVendorProcess(String vendor, String email, String address) {

		// assign variables in a map
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(VENDOR, vendor);
		variables.put(EMAIL, email);
		variables.put(ADDRESS, address);

		// starting a process from process engine with process key and variables
		process.getRuntimeService().startProcessInstanceByKey("vendor-management-services",
				variables);

	}

}
