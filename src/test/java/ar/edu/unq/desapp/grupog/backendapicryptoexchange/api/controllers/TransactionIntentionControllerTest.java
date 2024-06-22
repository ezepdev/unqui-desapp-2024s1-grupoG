package ar.edu.unq.desapp.grupog.backendapicryptoexchange.api.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

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