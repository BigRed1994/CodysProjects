package com.jobboard.controllers;

import com.jobboard.dao.SkillDao;
import com.jobboard.model.Skill;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillDao skillDao;

    public SkillController(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillDao.getAllSkills();
    }

    @PostMapping
    public void createSkill(@RequestBody Skill skill) {
        skillDao.createSkill(skill);
    }
}
