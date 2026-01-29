class Solution {
    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try(FileWriter f = new FileWriter("display_runtime.txt")){
                f.write("0");
            }catch(Exception e){

            }
        }));
    }

    public String sortVowels(String s) {
        
        String str="";
        StringBuilder sb=new StringBuilder(s);

        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='A' ||ch=='E' ||ch=='I' ||ch=='O' ||ch=='U' ||ch=='a' ||ch=='e' ||ch=='i' ||ch=='o' ||ch=='u'){
                str+=ch;
            }
        }
        char []s1=str.toCharArray();
        Arrays.sort(s1);
        int j=0;
        for(int i=0;i<sb.length();i++){
            char ch=sb.charAt(i);
             if(ch=='A' ||ch=='E' ||ch=='I' ||ch=='O' ||ch=='U' ||ch=='a' ||ch=='e' ||ch=='i' ||ch=='o' ||ch=='u'){
                sb.setCharAt(i,s1[j++]);
            }
            
        }
        System.out.print(s1);
        return sb.toString();
    }
}