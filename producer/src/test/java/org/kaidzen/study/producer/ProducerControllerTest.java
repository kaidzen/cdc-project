package org.kaidzen.study.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 0)
public class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(ProducerController.class).build();
    }

    @Test
    public void check() throws Exception {
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/check"))
                .willReturn(WireMock.aResponse().withBody("OK").withStatus(200)));

        mockMvc.perform(MockMvcRequestBuilders.post("/check")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsBytes(new PersonToCheck(22))))
        .andExpect(status().isOk());
    }
}