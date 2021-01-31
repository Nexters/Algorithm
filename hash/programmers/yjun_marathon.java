import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 완주하지 못한 선수
 * https://programmers.co.kr/learn/courses/30/lessons/42576
 * 마라톤 경기에서 완주하지 못한 선수를 return
**/
class Solution {
    public String solution(String[] participant, String[] completion) {
        ArrayList<String> partList = new ArrayList<>(Arrays.asList(participant));
        ArrayList<String> completList = new ArrayList<>(Arrays.asList(completion));
        
        Collections.sort(completList);
        Collections.sort(partList);
        
        int index = 0;
        for(String person : completList) {
            if (!partList.get(index).equals(person))
                return partList.get(index);
            index++;
        }
        return partList.get(partList.size() - 1);
    }
}