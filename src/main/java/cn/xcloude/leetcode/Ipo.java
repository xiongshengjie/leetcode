package cn.xcloude.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode 502
 * IPO
 */
public class Ipo {
  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    PriorityQueue<Project> minCapitalHeap = new PriorityQueue<>(capital.length,
        Comparator.comparing(Project::getCapital));
    PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>(Comparator.reverseOrder());

    for (int index = 0; index < capital.length; ++index) {
      minCapitalHeap.offer(new Project(capital[index], profits[index]));
    }

    for (int count = 0; count < k; ++count) {
      Project project;
      while ((project = minCapitalHeap.peek()) != null && project.capital <= w) {
        minCapitalHeap.poll();
        maxProfitHeap.offer(project.getProfit());
      }

      Integer profit = maxProfitHeap.poll();
      if (profit == null) {
        break;
      }
      w += profit;
    }

    return w;
  }

  private static final class Project {
    private final int capital;
    private final int profit;

    private Project(int capital, int profit) {
      this.capital = capital;
      this.profit = profit;
    }

    private int getCapital() {
      return capital;
    }

    private int getProfit() {
      return profit;
    }
  }

  public static void main(String[] args) {
    new Ipo().findMaximizedCapital(2, 0, new int[] {1, 2, 3}, new int[] {0, 1, 1});
  }
}
