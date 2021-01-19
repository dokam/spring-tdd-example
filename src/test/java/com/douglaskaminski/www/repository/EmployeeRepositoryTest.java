package com.douglaskaminski.www.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import com.douglaskaminski.www.model.Employee;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class EmployeeRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	
	@Test
    public void testFindByEmail() {

	    Employee newEmployee = new Employee();
	    newEmployee.setEmail("testing@mytest.com");
	    entityManager.persist(newEmployee);

        // Find an inserted record
        Employee foundEmployee = employeeRepository.findByEmail("testing@mytest.com");
        
        MatcherAssert.assertThat(foundEmployee.getEmail(),is(equalTo("testing@mytest.com")));
    }
	

}
