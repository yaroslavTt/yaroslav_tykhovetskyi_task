package com.example.yaroslav_tykhovetskyi_tech_task;

import com.example.yaroslav_tykhovetskyi_tech_task.controller.UserController;
import com.example.yaroslav_tykhovetskyi_tech_task.dto.UserResponse;
import com.example.yaroslav_tykhovetskyi_tech_task.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.net.URI;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    private static final String HOST = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private UserController controller;

    @Test
    public void contextLoads() throws Exception{}

    @Test
    public void testThatControllerIsInjected(){
        assertThat(controller).isNotNull();
    }

    @Test
    public void testAddValidUser() throws Exception {
        URI uri = new URI(HOST + randomServerPort + "/users");
        User user = new User(null, "Yaroslav", "Tykhovetskyi", LocalDate.of(2003, 9, 20));

        ResponseEntity<UserResponse> result = restTemplate.postForEntity(uri, user, UserResponse.class);

        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    public void shouldReturnDtoWithValidAge() throws Exception{
        URI uri = new URI(HOST + randomServerPort + "/users");
        User user = new User(null, "Yaroslav", "Tykhovetskyi", LocalDate.of(2003, 9, 20));

        ResponseEntity<UserResponse> result = restTemplate.postForEntity(uri, user, UserResponse.class);

        Assert.assertEquals(Integer.valueOf(19), result.getBody().getAge());

    }

    @Test
    public void shouldNotAcceptNullUserPropertiesExceptID() throws Exception{
        URI uri = new URI(HOST + randomServerPort + "/users");
        User user = new User(null, null, "Tykhovetskyi", LocalDate.of(2003, 9, 20));

        ResponseEntity<UserResponse> result = restTemplate.postForEntity(uri, user, UserResponse.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    public void returnUserWithValidId() throws Exception{
        StringBuilder baseUrl = new StringBuilder(HOST + randomServerPort + "/users/");
        URI postUri = new URI(baseUrl.toString());
        URI getUri = new URI(baseUrl.append("1").toString());

        User user = new User(null, "Yaroslav", "Tykhovetskyi", LocalDate.of(2003, 9, 20));
        restTemplate.postForEntity(postUri, user, UserResponse.class);
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(getUri, UserResponse.class);
        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void tryToGetUserWithInvalidId() throws Exception{
        URI uri = new URI(HOST + randomServerPort + "/users/999");

        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}
