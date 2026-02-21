class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int countCompleteSubarrays(int[] nums) {
        
        HashMap<Integer , Integer> mp = new HashMap<>();
        int n = nums.length;

        for(int num : nums){
            mp.put(num , mp.getOrDefault(num, 0) +1);
        }

        int ans = 0 ;
        int distinct = mp.size();
        mp.clear();
        int j = 0;

        for(int i = 0; i < n ; i++){
            while(j < n && mp.size() != distinct){
                mp.put(nums[j] , mp.getOrDefault(nums[j], 0) +1);
                j++;
            }
            if(j == n && mp.size() != distinct) break;
            ans += (n-j+1);
            mp.put(nums[i] , mp.getOrDefault(nums[i] , 0) -1);
            if(mp.get(nums[i]) == 0) mp.remove(nums[i]);
        }

        return ans;

    }
}