import java.util.HashMap;

/**
 * 2021.01.31 06:00
 * https://programmers.co.kr/learn/courses/30/lessons/42576
 */
public class riflockle7_a_player_who_could_not_finish {
    public static void main(String[] args) {
        System.out.println("leo".equals(solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"})));
        System.out.println("vinko".equals(solution(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"})));
        System.out.println("mislav".equals(solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"})));
    }

    public static String solution(String[] participant, String[] completion) {
        StringBuilder answer = new StringBuilder();

        HashMap<String, Integer> table = new HashMap<>();
        // 참가자 목록을 기반으로 hashmap 만들기
        for (String participantPerson : participant) {
            if (table.get(participantPerson) == null)
                table.put(participantPerson, 1);
            else {
                Integer newNumber = table.get(participantPerson) + 1;
                table.put(participantPerson, newNumber);
            }
        }

        // 완주자 목록을 참고하여 참가자 목록에서 한 명씩 삭제
        for (String completePerson : completion) {
            Integer remains = table.get(completePerson);
            if (remains != 1) {
                table.put(completePerson, remains - 1);
            } else
                table.remove(completePerson);
        }
        // 남은 데이터 모두 answer 에 넣음
        for (String failPerson : table.keySet()) {
            answer.append(failPerson);
        }
        return answer.toString();
    }
}
