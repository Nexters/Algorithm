import java.util.*;

/**
 * 완전탐색
 * 프로그래머스 #42840 모의고사
 * https://programmers.co.kr/learn/courses/30/lessons/42840
 * 
 * 3가지 패턴 중 input과 가장 많이 일치하는 경우를 return
 * 중복 시 오름차순 정렬
**/

class Solution {
    public ArrayList<Integer> solution(int[] answers) {
        // 패턴
        // 1, 2, 3, 4, 5
        // 2, 1, 2, 3, 2, 4, 2, 5
        // 3, 3, 1, 1, 2, 2, 4, 4, 5, 5
        int[] pattern_01 = {1, 2, 3, 4, 5};
        int[] pattern_02 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern_03 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int l1 = pattern_01.length;
        int l2 = pattern_02.length;
        int l3 = pattern_03.length;
        int i = 0;
        int j = 0;
        int k = 0;
        int[] count = {0, 0, 0};
        
        for  (int answer : answers) {
            if (answer == pattern_01[i]) count[0]++;
            if (answer == pattern_02[j]) count[1]++;
            if (answer == pattern_03[k]) count[2]++;
            i = (i+1)%l1;
            j = (j+1)%l2;
            k = (k+1)%l3;
        }
        
        ArrayList<Integer> answerList = new ArrayList<>();
        
        if (count[0] < count[1]) {
            if (count[1] < count[2]) {
                answerList.add(3);
            }
            else if(count[1] == count[2]) {
                answerList.add(2);
                answerList.add(3);
            }
            else{
                answerList.add(2);
            }
        } else if (count[0] == count[1]) {
            if (count[0] < count[2]) {
                answerList.add(3);
            }
            else if (count[1] == count[2]) {
                answerList.add(1);
                answerList.add(2);
                answerList.add(3);
            }
            else {
                answerList.add(1);
                answerList.add(2);
            }
        } else {
            if (count[0] < count[2]) {
                answerList.add(3);
            } else if (count[0] == count[2]) {
                answerList.add(1);
                answerList.add(3);
            } else {
                answerList.add(1);
            }
        }

        return answerList;
    }
}