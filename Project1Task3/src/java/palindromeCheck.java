/**
* Author: Keaton Watts
* Last Modified: Jan 30, 2015
*
* This program will check if a string is a palindrome.
* User will have an input field and a submit button.
* Program will ignore case and punctuation.
* Program will display result.
*/

public class palindromeCheck {

    private String searchWord;
    private String searchResult;

    //method will capture word from servlet
    //sourced code from: http://www.programmingsimplified.com/java/source-code/java-program-check-palindrome
    public String isPalindrome(String word) {
        //remove punctuation and ignore case
        searchWord = word.toLowerCase().replaceAll("\\W", "");
        String reverse = "";

        //determine length of searchWord
        int length = searchWord.length();

        //loop through word and store it, but reversed
        for (int i = length - 1; i >= 0; i--) {
            reverse = reverse + searchWord.charAt(i);
        }
        
        //if the reverse word and original word match, then palindrome.
        if (searchWord.equalsIgnoreCase(reverse)) {
            return searchResult = " is a palindrome.";
        } 
        //otherwise words are not palindrome.
        else {
            return searchResult = " is not a palindrome.";
        }

    }

    //return result
    public String getSearchResult() {
        return searchResult;
    }

}
