package com.jobboard.dao;

import com.jobboard.model.Skill;

import java.util.List;

public interface SkillDao {
    Skill getSkillById(int skillId);
    List<Skill> getAllSkills();
    Skill createSkill(Skill skill);
    void updateSkill(Skill skill);
    void deleteSkill(int skillId);
}

