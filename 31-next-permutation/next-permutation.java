class Solution {
    public void nextPermutation(int[] nums) {
        // Set index to -1
    //     int index = -1;

    //     // Dry run

    //     // Find the first decreasing element from end
    //     for(int i=nums.length-2; i>=0;i--){ // i = 3-2; i>=0;i--
    //         // if smaller found
    //         if(nums[i] < nums[i+1]){    // 2<3
    //             index = i;  // index = 1
    //             break;
    //         }
    //     }

    //     // If no index found
    //     if (index == -1) {
    //         // Reverse the entire array
    //         reverseArray(nums, 0, nums.length - 1);
    //         return;
    //     }

    //     // find just larger element
    //     for(int i=nums.length-1;i> index;i--){  // i = 2
    //         if(nums[i]>nums[index]){    // 3 > 2
    //             swap(nums, i, index);   // swap(nums, 2, 1)
    //             break;
    //         }
    //     }
    //     reverseArray(nums, index + 1, nums.length - 1);
    

        // Another Approach         // [1,2,3]
        if(nums.length <=1) return; 
        int i=nums.length-2;                        // i = 1
        while(i>=0 && nums[i]>=nums[i+1]) i--;      // i=1>=0 && 2>=3 ; i=1
        if(i>=0){
            int j=nums.length-1;                    // j = 2;
            while(nums[j]<=nums[i]) j--;            // 3<=2 ; j =2
            swap(nums, i, j);                       // swap(nums 1, 2) - [1,3,2]
        }
        reverseArray(nums, i+1, nums.length-1);     // reve(nums, 1+1, 2)
    }

    // helper to swap array
    private void reverseArray(int[] arr, int st, int end){
        while(st < end){
            swap(arr, st, end);
            st++;
            end--;
        }
    }

    // helper to swap array
    private void swap(int[] arr, int st, int end){
        int temp = arr[st];
        arr[st] = arr[end];
        arr[end] = temp;
    }
}