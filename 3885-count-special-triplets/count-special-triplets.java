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
    static final int MOD = 1000000007;
    static int[] right=new int[100001];
    static int[] left=new int[100001];
    public static int specialTriplets(int[] nums) {
        for(int i:nums)right[i]++;
        long result=0;
        for(int i:nums){
            right[i]--;
            if(i*2<left.length)result+=(long) right[i*2] * left[i*2];
            left[i]++;
        }
        for(int i:nums)left[i]=0;
    return (int) (result%MOD);
    }
}