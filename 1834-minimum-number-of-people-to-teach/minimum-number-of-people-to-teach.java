class Solution {
    //basically choose the language which is most common among people who can't communicate, and teach that language to the people who don't know the language among who can't communicate
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        boolean[] peopleCant = new boolean[501];
        int peopleCount = 0;
        for (int[] friendship : friendships) {
            int u = friendship[0];
            int v = friendship[1];
            boolean canCommunicate = false;
            boolean[] commonLanguages = new boolean[n + 1];
            for (int l : languages[u - 1])
                commonLanguages[l] = true;
            for (int l : languages[v - 1]) {
                if (commonLanguages[l]) {
                    canCommunicate = true;
                    break;
                }
            }
            if (!canCommunicate) {
                peopleCant[u - 1] = true;
                peopleCant[v - 1] = true;
            }
        }
        int maxPeopleWithCommonLanguage = 0;
        int[] countMostUsedLanguage = new int[n + 1];
        for (int i = 0; i < peopleCant.length; i++) {
            if (peopleCant[i]) {
                peopleCount++;
                for (int l : languages[i]) {
                    countMostUsedLanguage[l]++;
                    maxPeopleWithCommonLanguage = Math.max(maxPeopleWithCommonLanguage, countMostUsedLanguage[l]);
                }
            }
        }
        return peopleCount - maxPeopleWithCommonLanguage;
    }
}