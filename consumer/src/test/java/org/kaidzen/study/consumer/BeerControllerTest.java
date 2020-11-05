package org.kaidzen.study.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kaidzen.study.consumer.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureWebClient
@AutoConfigureWireMock(port = 8090)
@AutoConfigureJsonTesters
//@AutoConfigureStubRunner(ids = "org.kaydzen.study:producer:+:stub:8090")
public class BeerControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private JacksonTester<Person> json = new JacksonTester<>(getClass(), ResolvableType.forClass(Person.class), objectMapper);

    @Before
    public void setUp() {
        JacksonTester.initFields(json, objectMapper);
    }

    @Test
    public void should_give_me_a_beer_when_old_enough() throws Exception {

        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/check"))
                .willReturn(WireMock.aResponse().withBody("OK").withStatus(200)));

        mockMvc.perform(MockMvcRequestBuilders.post("/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(new Person("serg", 22)).getJson()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("THERE YOU GO"));

    }

    @Test
    public void should_reject_a_beer_when_too_young() throws Exception {
        WireMock.stubFor(WireMock.post(WireMock.urlEqualTo("/check"))
                .willReturn(WireMock.aResponse().withBody("NOT_OK").withStatus(200)));

        mockMvc.perform(MockMvcRequestBuilders.post("/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.write(new Person("serg", 17)).getJson()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("GET LOST"));
    }
}