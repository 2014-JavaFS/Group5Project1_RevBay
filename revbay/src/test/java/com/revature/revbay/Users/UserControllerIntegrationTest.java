package com.revature.revbay.Users;

import com.revature.revbay.user.UserController;
import com.revature.revbay.user.UserRepository;
import com.revature.revbay.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestUsersWithValidUserThenCorrectResponse() throws Exception {
        String userJson =
                "{\n" +
                        "    \"firstName\": \"John\"\n" +
                        "    \"lastName\": \"Mae\"\n" +
                        "    \"email\": \"testemail@email.com\"\n" +
                        "    \"password\": \"securePass1\"\n" +
                        "    \"userType\": \"SELLER\"\n" +
                        "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(userJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    public void whenGetRequestUserByValidUserIdThenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void whenPutRequestUserByValidUserThenCorrectResponse() throws Exception {
        String userJson = """ 
                {
                    "userId": 1,
                 	"firstName": "John",
                 	"lastName": "Mae",
                 	"email": "test@email.com",
                 	"password": "securePass3",
                 	"userType": "SELLER"
                 }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/users"))
                .andExpect(MockMvcResultMatchers.status().is(220));
    }
}