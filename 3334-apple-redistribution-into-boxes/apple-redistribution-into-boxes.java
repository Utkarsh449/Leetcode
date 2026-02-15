class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int appleSum = 0;
        for (int packQty : apple) {
            appleSum += packQty;
        }

        int needBoxes = 0;
        int appleInBoxes = 0;
        int idxOfMaxBox;
        int freeBoxes = capacity.length;
        while (appleInBoxes < appleSum) {
            idxOfMaxBox = 0;
            for (int i = 0; i < freeBoxes; i++) {
                if (capacity[i] > capacity[idxOfMaxBox]) {
                    idxOfMaxBox = i;
                }
            }
            appleInBoxes += capacity[idxOfMaxBox];
            capacity[idxOfMaxBox] = capacity[freeBoxes - 1];
            freeBoxes--;
            needBoxes++;
        }
        return needBoxes;
    }
}