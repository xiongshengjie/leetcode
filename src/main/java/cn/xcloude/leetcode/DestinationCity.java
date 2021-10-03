package cn.xcloude.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1436
 * 旅行终点站
 */
public class DestinationCity {
  public String destCity(List<List<String>> paths) {
    Set<String> citiesA = new HashSet<>(paths.size());
    for (List<String> path : paths) {
      citiesA.add(path.get(0));
    }

    for (List<String> path : paths) {
      if (!citiesA.contains(path.get(1))) {
        return path.get(1);
      }
    }

    return "";
  }
}
