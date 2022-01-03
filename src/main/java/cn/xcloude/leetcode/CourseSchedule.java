package cn.xcloude.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * leetcode 207, 210
 * 课程表, 课程表 ii
 */
public class CourseSchedule {
  // 课程表
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    return findOrder(numCourses, prerequisites).length != 0;
  }

  // 课程表 ii
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    // 当前下标课程要求的先修课程数量
    int[] prerequisitesCount = new int[numCourses];
    // 每个元素代表当前下标的课程是 value 这些课程的先修课程
    Map<Integer, List<Integer>> prerequisites2Courses = new TreeMap<>();

    for (int[] inner : prerequisites) {
      ++prerequisitesCount[inner[0]];
      prerequisites2Courses.computeIfAbsent(inner[1], e -> new LinkedList<>()).add(inner[0]);
    }

    // 无先修课程的课程集合
    List<Integer> noPrerequisitesCourses = new LinkedList<>();
    for (int course = 0; course < numCourses; ++course) {
      if (prerequisitesCount[course] == 0) {
        noPrerequisitesCourses.add(course);
      }
    }

    int[] result = new int[numCourses];
    int index = 0;
    while (!noPrerequisitesCourses.isEmpty()) {
      Integer learning = noPrerequisitesCourses.remove(0);
      result[index++] = learning;
      List<Integer> courses = prerequisites2Courses.get(learning);
      if (courses == null) {
        continue;
      }

      for (Integer course : courses) {
        if (prerequisitesCount[course] == 1) {
          noPrerequisitesCourses.add(course);
        } else {
          --prerequisitesCount[course];
        }
      }
    }

    return index == numCourses ? result : new int[0];
  }
}
