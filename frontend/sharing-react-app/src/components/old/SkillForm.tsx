// src/components/SkillForm.tsx

import React, { useState } from 'react';
import axios from 'axios';
import { SkillDto } from '../dto/SkillDto.ts'; // Импортируем SkillDto

const SkillForm: React.FC = () => {
  const [skill, setSkill] = useState<SkillDto>({
    skillId: null,
    skillName: ''
  });
  const [error, setError] = useState<string | null>(null);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSkill({ ...skill, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/skills', skill);
      console.log('Skill created:', response.data);
    } catch (error) {
      setError('Error creating skill');
      console.error('Error creating skill:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Skill Name:</label>
        <input type="text" name="skillName" value={skill.skillName} onChange={handleChange} />
      </div>
      {error && <div>{error}</div>}
      <button type="submit">Submit</button>
    </form>
  );
};

export default SkillForm;
