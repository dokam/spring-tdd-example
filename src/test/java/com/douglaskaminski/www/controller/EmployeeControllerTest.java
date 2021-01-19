package com.douglaskaminski.www.controller;

import com.douglaskaminski.www.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @Autowired
    EmployeeController employeeController;

    @Test
    public void testAddEmployeeHappyPath(){
        Employee aEmployee = new Employee();
        aEmployee.setFirstName("Test");
        aEmployee.setLastName("TEST LAST");

        String outcome = employeeController.saveEmployee(aEmployee);

        assertThat(outcome,is(equalTo("redirect:/")));

    }

    @Test
    public void testAddEmployeeFirstNameMissing() {
        Employee aEmployee = new Employee();

        // POST our CustomerContact form bean to the controller; check the outcome
        String outcome = employeeController.saveEmployee(aEmployee);

        // Assert THAT the outcome is as expected
        assertThat(outcome, is(equalTo("failure")));

    }

}