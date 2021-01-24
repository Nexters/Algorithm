/**
호텔 방 배정
- https://programmers.co.kr/learn/courses/30/lessons/64063

- category: hash, union find
- condition
  1. room_number 배열 각 원소들의 값은 1 이상 k 이하인 자연수입니다.
  2. 고객이 원하는 방이 이미 배정되어 있으면 원하는 방보다 번호가 크면서 비어있는 방 중 가장 번호가 작은 방을 배정합니다.
  3. room_number 배열은 모든 고객이 방을 배정받을 수 있는 경우만 입력으로 주어진다.
*/

function find(rooms, i) {
  if (!rooms.get(i)) {
    rooms.set(i, i + 1);
    return i;
  }
  const root = find(rooms, rooms.get(i));
  rooms.set(i, root);
  return root;
}

function solution(_, room_number) {
  const rooms = new Map();
  const answer = [];
  room_number.forEach((r) => answer.push(find(rooms, r)));
  return answer;
}
