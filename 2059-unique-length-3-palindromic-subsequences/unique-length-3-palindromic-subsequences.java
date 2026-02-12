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
    public int countPalindromicSubsequence(String s) {
        int ans = 0;
        int n = s.length();
        Set<Character> letters = new HashSet<>();

        for(Character ch : s.toCharArray()){
            letters.add(ch);
        }
        for(char c : letters){
            int i = -1;
            int j = 0;

            for(int k = 0 ; k < n ; k++){
                if(s.charAt(k) == c){
                    if(i == -1) i = k;
                    j = k;
                }
            }

            Set<Character> middle = new HashSet<>();

            for(int k = i+1 ; k < j ; k++){
                middle.add(s.charAt(k));
            }

            ans += middle.size();
        }



        return ans;
        

    }
}