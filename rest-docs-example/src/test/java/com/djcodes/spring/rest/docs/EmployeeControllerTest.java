package com.djcodes.spring.rest.docs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs
public class EmployeeControllerTest {

  @LocalServerPort private int serverPort;

  private RequestSpecification requestSpecification;

  @Autowired EmployeeRepository employeeRepository;

  @PostConstruct
  public void initRequestSpecification() {
    final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    requestSpecification = requestSpecBuilder.setBaseUri("http://localhost:" + serverPort).build();
  }

  @After
  public void cleanRepositories() {
    employeeRepository.deleteAll();
  }

  @Test
  public void getOneEmployee_valid_test() {
    Employee aEmployee = new Employee();
    aEmployee.setFname("test1");
    aEmployee.setLname("name");
    aEmployee.setSalary(130000.00);
    aEmployee.setGender("Male");
    aEmployee.setDob(new GregorianCalendar(1990, Calendar.FEBRUARY, 21));
    Employee savedEmployee = employeeRepository.save(aEmployee);
    final Employee result =
        RestAssured.given(requestSpecification)
            .when()
            .get("/getById/{id}", savedEmployee.getId())
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .response()
            .as(Employee.class);

    assertThat(result.getFname()).isEqualTo(savedEmployee.getFname());
  }

  @Test
  public void createTest() {
    Employee aEmployee = new Employee();
    aEmployee.setFname("test1");
    aEmployee.setLname("name");
    aEmployee.setSalary(130000.00);
    aEmployee.setGender("Male");
    aEmployee.setDob(new GregorianCalendar(1990, Calendar.FEBRUARY, 21));
    final String id =
        RestAssured.given(requestSpecification)
            .contentType("application/json")
            .body(aEmployee)
            .when()
            .post("/create")
            .then()
            .statusCode(HttpStatus.SC_CREATED)
            .extract()
            .response()
            .header("id");

    assertThat(aEmployee.getFname())
        .isEqualTo(employeeRepository.findById(Integer.parseInt(id)).get().getFname());
  }
}
