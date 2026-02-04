class Solution {
    class PartitionState{
        int done;
        int mask;
        int distinct;

        PartitionState(int d, int m, int dist){
            done = d;
            mask = m;
            distinct = dist;
        }
    }
    public int maxPartitionsAfterOperations(String s, int k) {
        int n = s.length();
        PartitionState[] left = new PartitionState[n];
        PartitionState[] right = new PartitionState[n];

        int done = 0;
        int mask = 0;
        int distinct = 0;
        for (int i = 0; i < n; i++) {
            int curr = 1 << (s.charAt(i) - 'a');
            if ((curr | mask) != mask) {
                mask = curr | mask;
                distinct++;
                if (distinct > k) {
                    distinct = 1;
                    done++;
                    mask = curr;
                }
            }
            left[i] = new PartitionState(done, mask, distinct);
        }

        done = 0;
        mask = 0;
        distinct = 0;
        for (int i = n - 1; i >= 0; i--) {
            int curr = 1 << (s.charAt(i) - 'a');
            if ((curr | mask) != mask) {
                mask = curr | mask;
                distinct++;
                if (distinct > k) {
                    distinct = 1;
                    done++;
                    mask = curr;
                }
            }
            right[i] = new PartitionState(done, mask, distinct);
        }

        int ans = 1;
        
        for (int i = 0; i < n; i++) {
            int lDone = 0;
            int lMask = 0;
            int lDistinct = 0;
            if (i > 0) {
                lDone = left[i - 1].done;
                lMask = left[i - 1].mask;
                lDistinct = left[i - 1].distinct;
            }
            
            int rDone = 0;
            int rMask = 0;
            int rDistinct = 0;
            if (i < n - 1) {
                rDone = right[i + 1].done;
                rMask = right[i + 1].mask;
                rDistinct = right[i + 1].distinct;
            }

            int curr = lDone + rDone;
            mask = lMask | rMask;
            distinct = Integer.bitCount(mask);
            if (lDistinct == k && rDistinct == k && distinct < 26) {
                curr += 3;
            } else if (distinct < k || k == 26){
                curr += 1;
            } else {
                curr += 2;
            }
            ans = Math.max(ans, curr);
        }
        return ans;
    }
}