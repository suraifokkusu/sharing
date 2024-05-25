// Описание интерфейса для DTO навыка
export interface SkillDto {
  skillId: number | null; // Идентификатор навыка, может быть null для новых навыков
  skillName: string; // Название навыка
}
