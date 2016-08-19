package com.chris.controller;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ApiControllerTest {

    private MockMvc subject;

    @Before
    public void setUp() throws Exception {
        subject = MockMvcBuilders.standaloneSetup(new ApiController()).build();
    }

    @Test
    public void serveContent() throws Exception {
        subject.perform(get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk()).andDo((result) -> {
            assertThat(result.getResponse().getContentAsString(), equalTo("Hello, world!"));
        });
    }
}
