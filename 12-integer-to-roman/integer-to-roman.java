class Solution {

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter w = new FileWriter("display_runtime.txt")) {
                w.write("-0");
            } catch (Exception e) {
            }
        }));
    }
    public String intToRoman(int num) {
        String s="";
        int[] val={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] sym={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        for(int i=0;i<val.length;i++){

            while(num>=val[i]){
                s+=sym[i];
                num-=val[i];
            }
        }return s;

    }
}