import java.util.HashMap;
import java.util.Map;

/**
 * 호텔 방 배정
 * https://programmers.co.kr/learn/courses/30/lessons/64063
 * 고객의 호텔 방을 원하는 방 && 순서대로 배정
 */

class Solution {
    // Hash
    Map<Long, Long> map = new HashMap<>();
    
    public long[] solution(long k, long[] roomNumber) {
        // Map<현재 방 번호 : 다음 방 번호>
        int n = roomNumber.length;
        long[] answer = new long[n];
        
        for (int i = 0; i < n; i++) {
            answer[i] = findEmptyRoom(roomNumber[i]);
         }
        return answer;
    }
    // 빈방 찾는 함수
    long findEmptyRoom(long room) {
        if (!map.containsKey(room)) {
            map.put(room, room + 1);
            return room;
        }
        
        long nextRoom = map.get(room);
        long emptyRoom = findEmptyRoom(nextRoom);
        map.put(room, emptyRoom);
        return emptyRoom;
    }
}