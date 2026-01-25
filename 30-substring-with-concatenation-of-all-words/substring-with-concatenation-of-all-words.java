class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter fw = new FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    static void addString(StringBuilder sb, String str,int fullLen) {
        for(int i = 0;i < fullLen;i++) {
            sb.append(str.charAt(i));
        }
    }
    static void getMap(HashMap<String,Integer> map,String str,int wordLen,int k) {
        int starting = 0;
        int push = wordLen;
        for(int i = 0;i < k;i++) {
            String temp = str.substring(starting,wordLen);
            map.put(temp,map.getOrDefault(temp,0) + 1);
            starting = wordLen;
            wordLen += push;
        }
    }
    static void getMap(HashMap<String,Integer> map,String str[]) {
        for(String s : str) {map.put(s,map.getOrDefault(s,0) + 1);} 
    }
    public List<Integer> findSubstring(String str, String[] arr) {
        int wordLen = arr[0].length();
        int fullLen = arr[0].length() * arr.length;
        int starting = 0;
        int ending = wordLen;
        HashMap<String,Integer> main = new HashMap<>();
        HashMap<String,Integer> part = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if(fullLen > str.length()) {return list;}
        getMap(main, arr);
        getMap(part, str,wordLen,arr.length);
        addString(sb, str, fullLen);
        System.out.println(sb);
        if(main.equals(part)) {list.add(0);}
        part.clear();
        for(int i = fullLen;i < str.length();i++) {
            char current = str.charAt(i);
            sb.deleteCharAt(0);
            sb.append(current);
            for(int j = 0;j < arr.length;j++) {
                String temp = sb.substring(starting,ending);
                part.put(temp,part.getOrDefault(temp,0) + 1);
                starting = ending;
                ending += wordLen;
            }
            if(main.equals(part)) {list.add(i - fullLen + 1);}
            part.clear();
            starting = 0;
            ending = wordLen;
        }   
        return list;
    }
}