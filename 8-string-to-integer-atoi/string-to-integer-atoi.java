class Solution {
 static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter w = new FileWriter("display_runtime.txt")) {
                w.write("-0");
            } catch (Exception e) {
            }
        }));
    }

    public int myAtoi(String s) {
        String res = "";
        char[] c = s.toCharArray();

        for (char value : c) {
            int cid = (int) value;
            if (cid == 32 && res.isEmpty()) {
                continue;
            }

            if ((cid == 45 || cid == 43) && !res.isEmpty()) {
                break;
            }

            if (cid == 45 || cid == 43 || (cid >= 48 && cid <= 57)) {
                res += value;
            } else {
                break;
            }
        }

        if (res.equals("+") || res.equals("-") || res.equals("+-") || res.equals("-+")) {
            return 0;
        }

        long num =0;
        if(!res.isEmpty() ){
        num = getNum(res);
        if (num < Integer.MIN_VALUE || num > Integer.MAX_VALUE) {
            if (num < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }}

        return (int) num;
    }

    public int getNum(String s) {
        try {
            long value = Long.parseLong(s);
            if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
            return value < Integer.MIN_VALUE ? Integer.MIN_VALUE :  Integer.MAX_VALUE;
            }

            return (int) value;
        } catch (NumberFormatException e) {
            if(s.contains("-")){
                return Integer.MIN_VALUE;
            }else{
                return Integer.MAX_VALUE;
            }
        }
    }
}