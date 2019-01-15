package com.mynotes.spring;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mynotes.spring.model.Employee;
import com.mynotes.spring.repo.EmployeeRepo;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest(classes = RestDocsApplication.class)
public class EmployeeControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    EmployeeRepo empRepo;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
            .build();
    }

    @AfterEach
    public void cleanUp() {
        empRepo.deleteAll();
    }

    @Test
    public void createEmployeeExample() throws Exception {
        Map<String, Object> input = new HashMap<>();
        input.put("name", "Jane");
        input.put("email", "jane@asd.asd");
        input.put("salary", 95000.0);

        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/employee")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(this.objectMapper.writeValueAsString(input)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("Jane"))
            .andExpect(jsonPath("$.email").value("jane@asd.asd"))
            .andExpect(jsonPath("$.salary").value("95000.0"))
            .andExpect(jsonPath("$.id").isNumber())
            .andDo(document("create-employee-example", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()), requestFields(getEmployeeFieldDescriptor()), responseFields(getEmployeeFieldDescriptorwithId())));

    }

    @Test
    public void getEmployeeById() throws Exception {
        Employee testEmp = createEmployee();
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/employee/{id}", testEmp.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(testEmp.getName()))
            .andExpect(jsonPath("$.email").value(testEmp.getEmail()))
            .andExpect(jsonPath("$.salary").value(testEmp.getSalary()))
            .andExpect(jsonPath("$.id").value(testEmp.getId()))
            .andDo(document("get-employee-by-id", preprocessResponse(prettyPrint()), pathParameters(parameterWithName("id").description("The id of the employee to be fetched")), responseFields(getEmployeeFieldDescriptorwithId())));
    }

    private FieldDescriptor[] getEmployeeFieldDescriptor() {
        return new FieldDescriptor[] { fieldWithPath("name").description("The name of the employee")
            .type(String.class.getSimpleName()),
                fieldWithPath("email").description("The email of the employee")
                    .type(String.class.getSimpleName()),
                fieldWithPath("salary").description("The salary of the employee")
                    .type(Double.class.getSimpleName()) };
    }

    private FieldDescriptor[] getEmployeeFieldDescriptorwithId() {
        return new FieldDescriptor[] { fieldWithPath("id").description("The unique id of the employee")
            .type(Long.class.getSimpleName()),
                fieldWithPath("name").description("The name of the employee")
                    .type(String.class.getSimpleName()),
                fieldWithPath("email").description("The email of the employee")
                    .type(String.class.getSimpleName()),
                fieldWithPath("salary").description("The salary of the employee")
                    .type(Double.class.getSimpleName()) };
    }

    private Employee createEmployee() {
        Employee emp = new Employee();
        emp.setEmail("test@email.com");
        emp.setName("john");
        emp.setSalary(10000.00);
        return empRepo.save(emp);
    }

}
