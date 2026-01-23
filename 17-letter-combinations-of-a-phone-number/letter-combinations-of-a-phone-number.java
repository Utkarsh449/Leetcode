class Solution {
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) return new ArrayList<>();
        
        String[] digitToLetters = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
        };
        List<String> result = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(),result, digitToLetters);
        return result;
    }
    private void backtrack(String digits, int index, StringBuilder currentCombination,
                            List<String> result, String[] digitToLetters){
            if(index == digits.length()){
                result.add(currentCombination.toString());
                return ;
            }
            String letters = digitToLetters[digits.charAt(index)-'0'];
            for(char letter : letters.toCharArray()){
                currentCombination.append(letter);
                backtrack(digits, index+1, currentCombination, result, digitToLetters);
                currentCombination.deleteCharAt(currentCombination.length()-1);
            }
        }
}