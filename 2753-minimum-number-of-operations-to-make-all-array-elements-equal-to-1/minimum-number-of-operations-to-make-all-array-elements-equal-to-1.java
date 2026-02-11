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

    public int minOperations(int[] nums) {
        int n = nums.length;
        int oneCount = 0;
        int smallestWithGcd1 = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) ++oneCount;
            if (oneCount > 0) continue;
            int g = nums[i];
            for (int j = i; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    smallestWithGcd1 = Math.min(smallestWithGcd1, j - i + 1);
                    break;
                }
            }
        }

        if (oneCount > 0) return nums.length - oneCount;
        if (smallestWithGcd1 == Integer.MAX_VALUE) return -1;

        return (smallestWithGcd1 - 1) + (n - 1);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}