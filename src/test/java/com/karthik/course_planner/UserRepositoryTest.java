package com.karthik.course_planner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    public void testModuleLinksToCorrectUser() {
        User user = new User();
        user.setUsername("karthik");
        user.setPassword("placeholder");
        user = userRepository.save(user);

        Module module = new Module();
        module.setName("CSC2002S");
        module.setCreditWeight(24);
        module.setCategory("core");
        module.setUser(user);
        module = moduleRepository.save(module);

        assertEquals("karthik", module.getUser().getUsername());
    }

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("alice");
        user.setPassword("placeholder");
        userRepository.save(user);

        var found = userRepository.findByUsername("alice");

        assertTrue(found.isPresent());
        assertEquals("alice", found.get().getUsername());
    }

}
