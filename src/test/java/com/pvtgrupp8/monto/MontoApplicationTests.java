/*package com.pvtgrupp8.monto;

import com.pvtgrupp8.monto.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Java6Assertions.fail;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MontoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void userEmailTest(){
        User testUser = new User();
        System.out.println("testing email in user");
        testUser.setEmail("test@email.com");
        assertTrue(testUser.getEmail()=="test@email.com");
    }

   @Test
    public void testInvalidEmail() {
        String[] invalidEmails = {"h"};
       User testUser = new User();
       System.out.println("Testing invalid email");
       testUser.setEmail("h");
       assertFalse(testUser.getEmail()=="h");

   }


 */
