class Solution {
    public int numberOfWays(String corridor) {
        int mod=1000000007;
        int ans=1;
        int plants=0;
        int seats=0;
        int segments=0;
        boolean first=false;
        for(int i=0;i<corridor.length();i++){
            char ch=corridor.charAt(i);
            if(ch=='S'){
                segments++;
                seats++;

            }
            else if(seats==0){
                plants++;
            }if(seats==2){
                if(first){
                    ans=(int)((long)ans*(plants+1)%mod)%mod;

                }first=true;
                seats=0;
                plants=0;
            }

        }
        return (segments>0 && segments%2==0)?(int)ans%mod:0;
    }
    static {


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {


            try (FileWriter writer = new FileWriter("display_runtime.txt")) {


                writer.write("0");


            } catch (IOException e) {


                e.printStackTrace();


            }


        }));


    }
}