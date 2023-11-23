package com.comrade;

import com.comrade.entity.CommandResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper =new ObjectMapper();

    @Test
    public void putTest() throws Exception {
        this.mockMvc.perform(post("/put")
                             .param("key","1")
                             .param("value","B")
                             .contentType(MediaType.APPLICATION_JSON))
                             .andExpect(status().isOk());
    }
    @Test
    public void getTest() throws Exception {
        this.mockMvc.perform(get("/get").param("key","1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void commandResponseTest() throws Exception {
        this.mockMvc.perform(get("/getval/{val}","1")
                        .content("application/json"))
                        .andDo(print())
                        .andExpect(status().isOk());
    }

    @Test
    public void saveDataTest() throws Exception {
        CommandResponse commandResponse = new CommandResponse("Test");
        String jsonData = mapper.writeValueAsString(commandResponse);
        this.mockMvc.perform(post("/add")
                .contentType("application/json")
                .content(jsonData))
                .andExpect(status().isOk());
    }

}
