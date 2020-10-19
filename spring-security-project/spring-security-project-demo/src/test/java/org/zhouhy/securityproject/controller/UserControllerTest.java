package org.zhouhy.securityproject.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockmvc;


    @Before
    public void setUp(){
        mockmvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void whenQuerySuccess() throws Exception {
        mockmvc.perform(MockMvcRequestBuilders.get("/users")
                .param("username","jojo")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void whenQuerySuccess1() throws Exception {
        mockmvc.perform(MockMvcRequestBuilders.get("/usersByCondition")
                .param("username","jojo")
                .param("age","19")
                .param("ageTo","88")
                .param("other","oh")
                .param("size","15")
                .param("page","3")
                .param("sort","age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void whenQueryDetailSuccess() throws Exception {
        mockmvc.perform(MockMvcRequestBuilders.get("/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Tom"));

    }

    @Test
    public void whenQueryDetailSuccess1() throws Exception {
        mockmvc.perform(MockMvcRequestBuilders.get("/users/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }


    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date();
        String requestContent = "{\"username\":\"Tom\",\"password\":\"123\",\"birthday\":"+date.getTime()+"}";
        String content = mockmvc.perform(MockMvcRequestBuilders.post("/users")
                .content(requestContent)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(content);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String requestContent = "{\"username\":\"Tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String content = mockmvc.perform(MockMvcRequestBuilders.put("/users")
                .content(requestContent)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(content);
    }

    @Test
    public void whenDeleteSuccess() throws Exception {
        mockmvc.perform(MockMvcRequestBuilders.delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenUploadSuccess() throws Exception {
       String result = mockmvc.perform(fileUpload("/file")
                .file(new MockMultipartFile("file","text.txt","multipart/form-data","hello world".getBytes()))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

       System.out.println(result);
    }
}
