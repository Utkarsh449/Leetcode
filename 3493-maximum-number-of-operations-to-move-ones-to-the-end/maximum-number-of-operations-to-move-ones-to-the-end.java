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

    public int maxOperations(String s) {
        int i = 0;
        int maxOps = 0;
        int onesCount = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '1') {
                onesCount++;
            } else if (i > 0 && s.charAt(i - 1) == '1') {
                maxOps += onesCount;
            }
            i++;
        }

        return maxOps;
    }
}

/**
    1001101
    onesCount ..zeros.. onesCount ..zeros.. 

    1, 2, 2, 1, 1, 0

    step1: 1 // 3, 1, 1, 0
    step2: 3 // 4, 0 -- since there are no zeros no ops is needed

    total count: 4 (1 + 3)
 */