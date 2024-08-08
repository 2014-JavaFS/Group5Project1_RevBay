package com.revature.revbay.Users;

import com.revature.revbay.user.User;
import com.revature.revbay.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserSaved() {
        User user = new User();
        user.setFirstName("Steve");
        user.setLastName("Erwin");
        user.setEmail("erwin@mail.com");
        user.setPassword("Pass123!");
        user.setUserType(User.UserType.SELLER);

        User savedUser = userRepository.save(user);
        Assertions.assertEquals(1, savedUser.getUserId());
        Assertions.assertEquals("SELLER", savedUser.getUserType().name());
    }
}