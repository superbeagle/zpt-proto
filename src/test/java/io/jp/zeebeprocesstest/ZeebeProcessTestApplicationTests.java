package io.jp.zeebeprocesstest;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.command.DeployResourceCommandStep1;
import io.camunda.zeebe.client.api.response.*;
import io.camunda.zeebe.process.test.api.ZeebeTestEngine;
import io.camunda.zeebe.process.test.filters.RecordStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.camunda.zeebe.process.test.extension.ZeebeProcessTest;

import java.util.ArrayList;
import java.util.HashMap;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
@ZeebeProcessTest

class ZeebeProcessTestApplicationTests {

	private ZeebeTestEngine engine;
	private ZeebeClient client;
	private RecordStream recordStream;

	@Test
	void testDeployment() throws Exception {

		DeploymentEvent event =
				client
					.newDeployResourceCommand()
					.addResourceFromClasspath("testDateRule.dmn")
					.addResourceFromClasspath("sampleProcess.bpmn")
				    .send()
				    .join();
		//DeploymentAssert assertions = BpmnAssert.assertThat(event);

		// Prepare data input
		HashMap<String, Object> variables = new HashMap<>();
		variables.put("theDate","1950-03-12");

		// Evaluate a decision

		/*EvaluateDecisionResponse response =
                client
					.newEvaluateDecisionCommand() //
                    .decisionId("Decision_1601l8s")
                    .variables(variables) //
					.send()
					.re



		 */

		  ProcessInstanceResult processInstanceResult =
				client
						.newCreateInstanceCommand()
						.bpmnProcessId("Sample_Process")
						.latestVersion()
						.withResult()
						.send()
						.join();

		//engine.waitForIdleState(Duration.ofSeconds(1));
		//response.getDecisionOutput().equals("\"OK\"");


	}

}
