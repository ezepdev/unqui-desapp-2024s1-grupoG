package ar.edu.unq.desapp.grupoG.backendapicryptoexchange.API.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionIntentionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
    }

  /*  @Test
    void getAllTransactionIntentions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/transaction_intentions"))
                .andExpect(status()
                        .isOk());
    }
*/
    @Test
    void createTransactionIntention() {
    }
}