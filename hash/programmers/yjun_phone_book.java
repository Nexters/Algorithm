import java.util.Collections;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;

/**
 * 전화번호 목록(42577)
 * https://programmers.co.kr/learn/courses/30/lessons/42577#
 * 전화번호 접두사 일치 여부 확인
**/

class Solution {
    public boolean solution(String[] phone_book) {
        // test#1
        // 1. 가장 짧은 길이의 문자부터 sorting
        Arrays.sort(phone_book, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                return Integer.compare(str1.length(), str2.length());
            }
        });
        
        for(int i = 0; i < phone_book.length; i++){
            String pre_str = phone_book[i];
            for (int j = i+1; j < phone_book.length; j++){
                String next_str = phone_book[j];
                next_str = next_str.substring(0, phone_book[i].length());
                if(pre_str.equals(next_str)) {
                    return false;
                }
            }
        }
        // test#2
        // 2. 가장 작은 숫자로 sorting
        /**
        ArrayList<String> phone_book_list = new ArrayList<>(Arrays.asList(phone_book));
        Collections.sort(phone_book_list);

        Iterator<String> itr = phone_book_list.iterator();

        while(itr.hasNext()) {
            String pre_str = itr.next();
            if(itr.hasNext()) {
                String next_str = itr.next();
                int str_len = pre_str.length();
                next_str = next_str.substring(0, str_len);
                System.out.println(next_str+" "+pre_str);
                if(pre_str.equals(next_str)){
                    return false;
                }
            }
        }
        **/

        return true;
    }
}