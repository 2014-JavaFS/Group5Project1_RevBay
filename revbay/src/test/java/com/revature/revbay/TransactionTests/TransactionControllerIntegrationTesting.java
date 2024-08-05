package com.revature.revbay.TransactionTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.revbay.transactions.Transactions;
import com.revature.revbay.transactions.TransactionsRepository;
import com.revature.revbay.transactions.TransactionsService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerIntegrationTesting {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransactionsService transactionsService;

    @MockBean
    private TransactionsRepository transactionsRepository;

    @Mock
    private RestTemplate restTemplate;

    @Captor
    private ArgumentCaptor<Transactions> transactionsArgumentCaptor;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUserWithMockMvc() throws Exception{
        String transactionJson =
                " {\n" +
                "        \"productId\": \"1\",\n" +
                "        \"name\": \"Dell Keyboard\",\n" +
                "        \"category\": \"ELECTRONICS\",\n" +
                "        \"user\":{\n" +
                "        \"userId\": 1,\n" +
                "        \"firstName\": \"May\",\n" +
                "        \"lastName\": \"Joon\",\n" +
                "        \"email\": \"joon3@email.com\",\n" +
                "        \"password\": \"securePass3\",\n" +
                "        \"userType\": \"SELLER\"\n" +
                "    },\n" +
                "    \"quantity\":5,\n" +
                "    \"price\":10.5\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .content(transactionJson)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201));
    }
}
