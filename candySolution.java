class Solution {
    public int candy(int[] ratings) {
        int totalCandies = 1;        // tracks total candies starting with the first child
        int currCandies = 1;      // Candies given to the current child
        int peakIdx = 0;           // Index of the last increasing peak
        int peakCandies = 1;         // Candies at the last increasing peak
        int descentLength = 0;       // Length of current descending slope

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                // Ascending: increase candies
                currCandies++;
                peakIdx = i;
                peakCandies = currCandies;
                descentLength = 0; // reset descent tracking
            } else if (ratings[i] < ratings[i - 1]) {
                // Descending: reset current candies to 1
                if (currCandies == 1) {
                    // Check if peak needs compensation
                    if (peakCandies - descentLength == 1) {
                        totalCandies += (i - peakIdx);
                        peakCandies++; // add 1 extra to peak if needed
                    } else {
                        totalCandies += (i - peakIdx - 1); // partial compensation
                    }
                }
                descentLength++;
                currCandies = 1;
            } else {
                // Equal ratings: reset everything
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
