package com.pvtgrupp8.monto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pvtgrupp8.monto.dao.UserRepository;
import com.pvtgrupp8.monto.entities.Route;
import com.pvtgrupp8.monto.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserEndpointTest {

    private static final String BASE_PATH = "/controllers/users";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    private TestUtility testUtility= new TestUtility(mapper);


    @Before
    public void initTests() {
        if(userRepository.findAll().size()!=0){
            userRepository.deleteAll();
        }
        User user = new User();
        user.setUsername("The First User");
        user.setEmail("mail@mail.com");
        userRepository.save(user);
    }

    @Test
    public void contextLoads() {
        assertThat(jdbcTemplate).isNotNull();
        assertThat(mvc).isNotNull();
    }

    @Test
    public void shouldStartWithOneUser() throws Exception {
        MvcResult result = getAllUsers()
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].username", is("The First User")))
            .andReturn();
    }


    @Test
    public void createUser() throws Exception{
        User user = new User();
        user.setUsername(testUtility.getRandomString());
        user.setEmail(testUtility.getRandomString()+"@email.com");
        byte[] userJson = testUtility.toJson(user);
        MvcResult results = invokeCreateUser(userJson)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.username",is(user.getUsername())))
            .andReturn();
        User user2 = testUtility.fromJsonResult(results,User.class);

        invokeSearchUserByEmail(user2.getEmail())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username",is(user.getUsername())));
    }

    @Test
    public void shouldDeleteUser() throws Exception{
        User user = new User();
        user.setUsername(testUtility.getRandomString());
        user.setEmail(testUtility.getRandomString()+"@email.com");
        invokeCreateUser(testUtility.toJson(user))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.username",is(user.getUsername())));
        invokeSearchUserByEmail(user.getEmail())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username",is(user.getUsername())));
        invokeDeleteUser(user.getEmail())
            .andExpect(status().isOk());
        invokeSearchUserByEmail(user.getEmail())
            .andExpect(status().isNotFound());

    }

    @Test
    public void shouldBadRequestIfEmailExists() throws Exception{
        User user = new User();
        user.setUsername(testUtility.getRandomString());
        user.setEmail("user@mail.com");
        invokeCreateUser(testUtility.toJson(user))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.username",is(user.getUsername())));
        User user2 = new User();
        user.setUsername(testUtility.getRandomString());
        user.setEmail("user@mail.com");
        invokeCreateUser(testUtility.toJson(user))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldBadRequestIfInvalidEmail() throws Exception{
        User user = new User();
        user.setUsername(testUtility.getRandomString());
        user.setEmail(testUtility.getRandomString());
        invokeCreateUser(testUtility.toJson(user))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message[0]",is("Not a valid email address")));
        user.setUsername(testUtility.getRandomString());
        user.setEmail("");
        invokeCreateUser(testUtility.toJson(user))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message[0]",is("it must contain an email address")));
        user.setUsername(testUtility.getRandomString());
        user.setEmail(null);
        invokeCreateUser(testUtility.toJson(user))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message[0]",is("it must contain an email address")));
    }

    @Test
    public void shouldBadRequestIfInvalidUsername() throws Exception{
        User user = new User();
        user.setUsername("");
        user.setEmail(testUtility.getRandomString()+"@email.com");
        invokeCreateUser(testUtility.toJson(user))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message[0]",is("Username can't be empty")));
        user.setUsername(null);
        invokeCreateUser(testUtility.toJson(user))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message[0]",is("Username can't be empty")));

    }


    private ResultActions invokeCreateUser(byte[] userJson) throws Exception {
        return mvc.perform(post("/controllers/users/create")
            .content(userJson)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

    }


    private ResultActions getAllUsers() throws Exception {
        return mvc.perform(get(BASE_PATH+"/all").accept(MediaType.APPLICATION_JSON));
    }

    private ResultActions invokeSearchUserByEmail(String email) throws Exception{
        return mvc.perform(get(BASE_PATH+"/"+email).accept(MediaType.APPLICATION_JSON));
    }


    private ResultActions invokeDeleteUser(String email) throws Exception {
        return mvc.perform(delete(BASE_PATH+ "/delete-user-by-email/"+email)
            .accept(MediaType.APPLICATION_JSON));
    }

}
