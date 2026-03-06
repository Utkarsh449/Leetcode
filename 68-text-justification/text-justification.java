class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0, n = words.length;

        while (i < n) {

            // Find how many words fit into this line
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < n && lineLen + 1 + words[j].length() <= maxWidth) {
                lineLen += 1 + words[j].length();  // 1 space before next word
                j++;
            }

            int noOfWords = j - i;
            int totalChars = 0;
            for (int k = i; k < j; k++) totalChars += words[k].length();

            StringBuilder line = new StringBuilder();

            // Case 1: Last line OR only one word → Left Justify
            if (j == n || noOfWords == 1) {

                // Put words with a single space between them
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k != j - 1) line.append(" ");
                }

                // Fill remaining spaces at the end
                while (line.length() < maxWidth) line.append(" ");
            }

            // Case 2: Middle line → Fully Justify
            else {
                int totalSpace = maxWidth - totalChars;      // Total spaces to distribute
                int gap = noOfWords - 1;                     // Number of gaps between words
                int extraSpace = totalSpace % gap;           // Extra spaces to put on left gaps

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k != j - 1) {
                        int space = totalSpace / gap;        // Minimum spaces per gap
                        while (space-- > 0) line.append(" ");

                        if (extraSpace > 0) {                // Distribute leftover spaces
                            line.append(" ");
                            extraSpace--;
                        }
                    }
                }
            }

            res.add(line.toString());
            i = j;  // Move to next set of words
        }

        return res;
    }
}