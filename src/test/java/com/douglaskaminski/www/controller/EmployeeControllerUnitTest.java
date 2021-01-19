package com.douglaskaminski.www.controller;

import com.douglaskaminski.www.model.Employee;
import com.douglaskaminski.www.service.EmployeeService;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddEmployeeHappyPath() throws Exception{
        //setup mock Employee returned the mock service component
        Employee mockEmployee = new Employee();
        mockEmployee.setFirstName("Fred");

        when(employeeService.saveEmployee(any(Employee.class)))
                .thenReturn(mockEmployee);

        //simulate the form bean that would POST from the web page
        Employee aEmployee = new Employee();
        aEmployee.setFirstName("Fred");
        aEmployee.setEmail("fred@myemail.com");
        aEmployee.setLastName("Harrys");

        //simulate form submit (POST)
        mockMvc
                .perform(post("/addEmployee",aEmployee))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testAddEmployeeBizServiceRuleNotSatisfied() throws Exception{

        //setup a mock response of NULL object returned from the mock service component
        when(employeeService.saveEmployee(any(Employee.class)))
                .thenReturn(null);

        //simulate the form bean that would POST from the web page
        Employee aEmployee = new Employee();
        aEmployee.setLastName("Harrys");

        //simulate the form submit (POST)
        mockMvc
                .perform(post("/addEmployee",aEmployee))
                .andExpect(status().is(302))
                .andReturn();
    }

}