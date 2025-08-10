package CodingTestStudy.SkillTree;

import java.util.*;
import java.lang.Math;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String skill_tree : skill_trees) {
            Set<Character> unavailable = new HashSet<>(); // 습득 불가능한 스킬
            Queue<Character> skillQue = new ArrayDeque<>(); // 선행스킬 큐

            skillQue.add(skill.charAt(0));
            for (int i = 1; i < skill.length(); i++) {
                char c = skill.charAt(i);
                skillQue.add(c);
                unavailable.add(c);
            }
            char firstSkill = skillQue.poll();
            for (int i = 0; i < skill_tree.length(); i++) {
                char currSkill = skill_tree.charAt(i);
                if (unavailable.contains(currSkill)) break;
                if (currSkill == firstSkill && !skillQue.isEmpty()) {
                    firstSkill = skillQue.poll();
                    unavailable.remove(firstSkill);
                }
                if (unavailable.isEmpty() || i == skill_tree.length() - 1) {
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }
}