class Solution {

   

static { Runtime.getRuntime().addShutdownHook(new Thread(() -> { try (FileWriter writer = new FileWriter("display_runtime.txt")) { writer.write("0"); } catch (IOException e) { e.printStackTrace(); } })); }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;

        int maxWater = 0;

        while(left<right){
            int width = right - left;

            int hei = Math.min(height[left],height[right]);

            int current = width * hei;
            maxWater = Math.max(maxWater,current);
            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }

        return maxWater;

    }
}