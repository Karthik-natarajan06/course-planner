package com.karthik.course_planner;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<ModuleDto> getAllModules(@RequestHeader("X-User-Id") Long userId) {
        List<Module> modules = moduleRepository.findByUserId(userId);
        return modules.stream().map(ModuleDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public ModuleDto createModule(@RequestHeader("X-User-Id") Long userId, @RequestBody Module module) {
        User user = userRepository.findById(userId).orElseThrow();
        module.setUser(user);
        Module saved = moduleRepository.save(module);
        return new ModuleDto(saved);
    }

    @GetMapping("/{moduleId}/average")
    public double getModuleAverage(@PathVariable Long moduleId) {
        return moduleService.calculateAverage(moduleId);
    }

    @GetMapping("/{moduleId}/mark-needed")
    public double getMarkNeeded(@PathVariable Long moduleId, @RequestParam double target) {
        return moduleService.calculateMarkNeeded(moduleId, target);
    }

}