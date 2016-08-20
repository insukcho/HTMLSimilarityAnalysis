package com.chris.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.chris.queue.ArticleQueue;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ApiControllerTest {

    private MockMvc subject;

    @Before
    public void setUp() throws Exception {
        subject = MockMvcBuilders.standaloneSetup(new ApiController()).build();
    }

    @Test
    public void testCreateNEnqueueArticle() throws Exception {
        subject.perform(get("/?topic=wow.com").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk()).andDo((result) -> {
            Assert.assertEquals("Create and enqueu Article sucessfully!", result.getResponse().getContentAsString());
        });
        
    	subject.perform(get("/?topic=wowwow.com").accept(MediaType.TEXT_HTML))
    	.andExpect(status().isOk()).andDo((result) -> {
    		Assert.assertEquals("Create and enqueu Article sucessfully!", result.getResponse().getContentAsString());
    	});
    	Assert.assertEquals(2, ArticleQueue.getInstance().getRequestQueue().size());
    }
}
