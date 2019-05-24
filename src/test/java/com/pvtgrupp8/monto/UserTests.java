package com.pvtgrupp8.monto;

import com.pvtgrupp8.monto.dao.RouteRepository;
import com.pvtgrupp8.monto.dao.UserRepository;
import com.pvtgrupp8.monto.entities.Route;
import com.pvtgrupp8.monto.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertSame;
import static junit.framework.TestCase.assertTrue;

import java.util.List;



@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testEmptyRepository(){
        List<User> users = userRepository.findAll();
        assertTrue(users.isEmpty());
    }

    @Test
    public void testAddToRepository() {
        User user1 = userRepository.save(new User ("Adam1","1hejsan@gmail.com",
            null, null, null));

        User user2 = userRepository.save(new User ("Adam2","2hejsan@gmail.com",
            null, null, null));

        User user3 = userRepository.save(new User ("Adam3","3hejsan@gmail.com",
            null, null, null));

        User user4 = userRepository.save(new User ("Adam3","4hejsan@gmail.com",
            null, null, null));

        assertSame(userRepository.findById(user1.getId()),user1);
        assertSame(userRepository.findById(user2.getId()),user2);
        assertSame(userRepository.findById(user3.getId()),user3);
        assertSame(userRepository.findById(user4.getId()),user4);
    }




}
