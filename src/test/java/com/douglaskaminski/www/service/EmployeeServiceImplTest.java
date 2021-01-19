package com.douglaskaminski.www.service;

import com.douglaskaminski.www.model.Employee;
import com.douglaskaminski.www.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddEmployeeHappyPath() {
        Employee aMockContact = new Employee();
        aMockContact.setFirstName("Douglas");
        aMockContact.setLastName("Kaminski");

        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(aMockContact);

        Employee newEmployee = employeeService.saveEmployee(aMockContact);

        assertEquals("Douglas",newEmployee.getFirstName());

    }



}