// src/components/SkillList.tsx

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { SkillDto } from '../dto/SkillDto.ts'; // Импортируем SkillDto
import { Link } from 'react-router-dom';

const SkillList: React.FC = () => {
  const [skills, setSkills] = useState<SkillDto[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchSkills = async () => {
      try {
        const response = await axios.get<SkillDto[]>('http://localhost:8080/api/skills');
        setSkills(response.data);
      } catch (error) {
        setError('Error fetching skills');
        console.error('Error fetching skills:', error);
      }
    };
    fetchSkills();
  }, []);

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Skill List</h2>
      <ul>
        {skills.map(skill => (
          <li key={skill.skillId}>
            {skill.skillName}
            <Link to={`/skills/${skill.skillId}`}>Details</Link>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SkillList;
