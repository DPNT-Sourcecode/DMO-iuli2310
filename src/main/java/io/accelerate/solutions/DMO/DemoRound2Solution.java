package io.accelerate.solutions.DMO;

import java.util.ArrayList;
import java.util.List;

public class DemoRound2Solution {

    // 1. Array Sum
    public int arraySum(List<Integer> integerList) {
        if (integerList == null) return 0; // Handle null input
        return integerList.stream().mapToInt(Integer::intValue).sum();
    }

    // 2. Int Sequence Generation
    public List<Integer> intRange(int start, int end) {
        List<Integer> rangeList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            rangeList.add(i);
        }
        return rangeList;
    }

    // 3. Array Filtering
    public List<Integer> filterPass(List<Integer> integerList, int threshold) {
        if (integerList == null) return new ArrayList<>(); // Handle null input
        List<Integer> filteredList = new ArrayList<>();
        for (int value : integerList) {
            if (value >= threshold) {
                filteredList.add(value);
            }
        }
        return filteredList;
    }
}
