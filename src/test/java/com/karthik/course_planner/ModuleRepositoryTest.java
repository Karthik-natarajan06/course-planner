package com.karthik.course_planner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ModuleRepositoryTest {

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    public void testSaveAndRetrieveModule() {
        Module module = new Module();
        module.setName("CSC2002S");
        module.setCreditWeight(24);
        module.setCategory("core");

        Module saved = moduleRepository.save(module);

        assertNotNull(saved.getId());

        Optional<Module> found = moduleRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("CSC2002S", found.get().getName());
        assertEquals(24, found.get().getCreditWeight());
        assertEquals("core", found.get().getCategory());
    }

}
