import React, { useEffect, useState } from 'react';
import { getAllSkills, createSkill, updateSkill, deleteSkill } from '../services/skillService';
import { SkillDto } from '../dto/SkillDto';

const SkillsPage: React.FC = () => {
  const [skills, setSkills] = useState<SkillDto[]>([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchSkills = async () => {
      try {
        const data = await getAllSkills();
        setSkills(data);
      } catch (err) {
        setError('Failed to fetch skills.');
      }
    };

    fetchSkills();
  }, []);

  const handleDelete = async (id: number | null) => {
    if (id === null) return;
    try {
      await deleteSkill(id);
      setSkills(skills.filter(skill => skill.skillId !== id));
    } catch (err) {
      setError('Failed to delete skill.');
    }
  };

  return (
    <div>
      <h2>Skills Page</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <ul>
        {skills.map(skill => (
          <li key={skill.skillId}>
            <p>{skill.skillName}</p>
            <button onClick={() => handleDelete(skill.skillId)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default SkillsPage;
