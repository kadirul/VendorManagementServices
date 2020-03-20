package org.camunda.bpm.unittest;

/**
 * @author Kadirul Chowdhury
 *
 */

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class StartProcessTest {

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule();

	@Test
	@Deployment(resources = { "VendorManagementSprint2.bpmn" })
	public void shouldExecuteProcess() {
		//Process Instance key from modeler
		String PROCESS_KEY = "vendor-management-services";
		// create hashmap to put in variables for the process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("email", "jak@unil.com");

		//Start process engine services
		ProcessEngine processEngine = rule.getProcessEngine();
		
		
		//start a new process instance
		  ProcessInstance pins = processEngine
				  .getRuntimeService()
				  .startProcessInstanceByKey(PROCESS_KEY, variables);
		 
		// asserting that a process is started and waiting in message event
		  assertThat(pins).isStarted()
		  .isWaitingAt("MessageReceivedEvent");
		 
	}
}
