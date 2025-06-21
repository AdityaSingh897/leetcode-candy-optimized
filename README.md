# leetcode-candy-optimized
Single-pass, O(1) space greedy solution to Leetcode's Candy problem using peak tracking and inline compensation.

# 🍬 Leetcode 135: Candy (Single-Pass, O(1) Space Solution)

This repository contains an optimized **single-pass**, **O(1) space** greedy solution to [Leetcode 135 - Candy](https://leetcode.com/problems/candy/), written in Java.

> 🎯 Most standard solutions use a two-pass array-based method.
> This approach solves the problem **in a single forward pass** without extra space, using **slope tracking and peak compensation**.

---

## 💡 Problem Summary

Each child must get at least one candy. Children with a higher rating than their neighbors must get **more** candies than them.

---

## 🔥 Key Highlights of This Solution

✅ **Single loop — O(n)**  
✅ **No extra arrays — O(1) space**  
✅ Handles increasing, decreasing, and equal rating sequences  
✅ Peak compensation during descending slopes ensures correctness  
✅ Custom-written approach — not from editorial or top solutions

---

## 🧠 Intuition Behind the Approach

- Track **ascending slopes** using `currCandies` and mark the **peak** (index + candy count).
- When descending, reset the current child’s candies to 1.
- If the descent gets too long and the peak isn't high enough to cover it, **compensate the peak** by adding extra candies inline.
- If ratings are equal, reset the slope.

This strategy mimics what two passes normally achieve but does it **on-the-fly**.

---

## 🧪 Edge Case Handling

- Strictly decreasing ratings (e.g., `[5,4,3,2,1]`)
- Flat sections (e.g., `[1,2,2,1]`)
- Mixed slopes (e.g., `[1,2,3,5,4,3,2,1,4,3,2,1]`)

All passed ✅

---

## ✅ Code

```java
class Solution {
    public int candy(int[] ratings) {
        int totalCandies = 1;        // Total candies, starting with first child
        int currCandies = 1;         // Candies given to the current child
        int peakIdx = 0;             // Index of the last peak in an increasing slope
        int peakCandies = 1;         // Candies at the peak
        int descentLength = 0;       // Number of steps we've been descending

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                // Ascending
                currCandies++;
                peakIdx = i;
                peakCandies = currCandies;
                descentLength = 0;
            } else if (ratings[i] < ratings[i - 1]) {
                // Descending
                if (currCandies == 1) {
                    if (peakCandies - descentLength == 1) {
                        totalCandies += (i - peakIdx);
                        peakCandies++;
                    } else {
                        totalCandies += (i - peakIdx - 1);
                    }
                }
                descentLength++;
                currCandies = 1;
            } else {
                // Flat
                peakIdx = i;
                currCandies = 1;
                peakCandies = 1;
                descentLength = 0;
            }

            totalCandies += currCandies;
        }

        return totalCandies;
    }
}

