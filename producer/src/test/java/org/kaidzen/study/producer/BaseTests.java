package org.kaidzen.study.producer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerApp.class)
public class BaseTests {

    @Autowired
    private ProducerController controller;

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(controller);
    }

    @Test
    public void should_be_test_here() {

    }
}
