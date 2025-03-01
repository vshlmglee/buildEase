package com.examly.springapp;

import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.lang.reflect.Field;  // Import the Field class

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {
	
	 @Autowired
	    private MockMvc mockMvc;
	 
		@Test
		@Order(1)
	    public void backend_testGetAllMaterial() throws Exception {
	        mockMvc.perform(get("/api/material")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(print())
	        .andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$").isArray())
		.andReturn();
	    }
	    
		
	    @Test
		@Order(2)
	    public void backend_testGetAllFeedBack() throws Exception {

	        mockMvc.perform(get("/api/feedback")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(print())
	        .andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$").isArray())
		.andReturn();
	    }
	    
	    @Test
	    public void backend_testFeedbackHasManyToOneAnnotation() {
	        try {
	            // Use reflection to get the Class object for the Course class
	            Class<?> courseClass = Class.forName("com.examly.springapp.model.Feedback");

	            // Get all declared fields in the Course class
	            Field[] declaredFields = courseClass.getDeclaredFields();

	            // Check each field for the @OneToOne annotation
	            boolean hasOneToOne = false;
	            for (Field field : declaredFields) {
	                if (field.isAnnotationPresent(ManyToOne.class)) {
	                	hasOneToOne = true;
	                    break; // Stop checking once we find one field with @OneToMany
	                }
	            }
		
		
	            // If no field with @OneToMany is found, fail the test
	            if (!hasOneToOne) {
	                fail("No field with @ManyToOne annotation found in Feedback class.");
	            }

	        } catch (ClassNotFoundException e) {
	            // If the class is not found, fail the test
	            fail("Class not found: " + e.getMessage());
	        }
	    }
	 
    @Test
    public void backend_testMaterialRequestHasManyToOneAnnotation() {
        try {
            // Use reflection to get the Class object for the Course class
            Class<?> courseClass = Class.forName("com.examly.springapp.model.MaterialRequest");

            // Get all declared fields in the Course class
            Field[] declaredFields = courseClass.getDeclaredFields();

            // Check each field for the @OneToMany annotation
            boolean hasManyToMany = false;
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(ManyToOne.class)) {
                    hasManyToMany = true;
                    break; // Stop checking once we find one field with @OneToMany
                }
            }
	
	
            // If no field with @OneToMany is found, fail the test
            if (!hasManyToMany) {
                fail("No field with @ManyToOne annotation found in Request class.");
            }

        } catch (ClassNotFoundException e) {
            // If the class is not found, fail the test
            fail("Class not found: " + e.getMessage());
        }
    }

    @Test
    public void backend_testMaterialInterfaceAndImplementation() {
        try {
            Class<?> interfaceClass = Class.forName("com.examly.springapp.service.MaterialService");
            Class<?> implementationClass = Class.forName("com.examly.springapp.service.MaterialServiceImpl");

            assertTrue(interfaceClass.isInterface(), "The specified class is not an interface");
            assertTrue(interfaceClass.isAssignableFrom(implementationClass), "Implementation does not implement the interface");
        } catch (ClassNotFoundException e) {
            fail("Interface or implementation not found");
        }
    }

    @Test
    public void backend_testMaterialRequestInterfaceAndImplementation() {
        try {
            Class<?> interfaceClass = Class.forName("com.examly.springapp.service.MaterialRequestService");
            Class<?> implementationClass = Class.forName("com.examly.springapp.service.MaterialRequestServiceImpl");

            assertTrue(interfaceClass.isInterface(), "The specified class is not an interface");
            assertTrue(interfaceClass.isAssignableFrom(implementationClass), "Implementation does not implement the interface");
        } catch (ClassNotFoundException e) {
            fail("Interface or implementation not found");
        }
    }

    
    

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

	 @Test
     public void backend_testFeedbackControllerClassExists() {
       checkClassExists("com.examly.springapp.controller.FeedbackController");
     }
	 
	 @Test
	   public void backend_testMaterialControllerClassExists() {
	       checkClassExists("com.examly.springapp.controller.MaterialController");
	   }

	   @Test
	   public void backend_testMaterialRequestControllerClassExists() {
	       checkClassExists("com.examly.springapp.controller.MaterialRequestController");
	   }

	   @Test
	   public void backend_testAuthControllerClassExists() {
	       checkClassExists("com.examly.springapp.controller.AuthController");
	   }

	 
	 @Test
	   public void backend_testFeedbackModelClassExists() {
	       checkClassExists("com.examly.springapp.model.Feedback");
	   }
	 
	 @Test
	   public void backend_testMaterialModelClassExists() {
	       checkClassExists("com.examly.springapp.model.Material");
	   }
	 
	 @Test
	   public void backend_testUserModelClassExists() {
	       checkClassExists("com.examly.springapp.model.User");
	   }
	 
	 @Test
	   public void backend_testMaterialRequestModelClassExists() {
	       checkClassExists("com.examly.springapp.model.MaterialRequest");
	   }

}
