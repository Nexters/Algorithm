/*
- 거북이: https://www.acmicpc.net/problem/8911
  - 실수했던 건, getDirIdx()에서 reversed이면 +2 인데, 0, 3으로 방향 index를 계산했었다.

- ref
  - js 입출력: https://velog.io/@jakeseo_me/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%EB%A1%9C-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%A0%95%EB%A6%AC%ED%95%98%EA%B8%B0-1-%EB%B0%B1%EC%A4%80-nodejs-%ED%91%9C%EC%A4%80-%EC%9E%85%EC%B6%9C%EB%A0%A5-%EC%8A%A4%ED%83%9D
  - js 입출력 예시: https://www.acmicpc.net/source/21426895

- ex
  - 2차원 배열 만들기 in es6
    const board = Array.from(Array(N), () => new Array(N).fill(null));
*/

const rl = require("readline").createInterface({
  input: process.stdin,
  output: process.stdout,
  terminal: false, // to avoid duplicate stdin
});

let N;
const CMD = [];

rl.on("line", function (line) {
  if (N) {
    CMD.push(line);
  } else {
    N = Number(line);
  }
}).on("close", function () {
  solution();
  process.exit();
});

class Turtle {
  constructor() {
    this.y = 0;
    this.x = 0;
    this.minY = 0;
    this.minX = 0;
    this.maxY = 0;
    this.maxX = 0;
    this.cDir = 0;
  }

  // 북동남서
  static dir = [
    [-1, 0],
    [0, 1],
    [1, 0],
    [0, -1],
  ];

  move(isForward = true) {
    const currentDir = isForward ? this.cDir : this.getDirIdx(this.cDir, true);
    let [dy, dx] = Turtle.dir[currentDir];
    const ny = this.y + dy;
    const nx = this.x + dx;
    this.updatePos(ny, nx);
  }

  updatePos(ny, nx) {
    this.maxY = Math.max(this.maxY, ny);
    this.maxX = Math.max(this.maxX, nx);
    this.minY = Math.min(this.minY, ny);
    this.minX = Math.min(this.minX, nx);
    this.y = ny;
    this.x = nx;
  }

  getDirIdx(n, reversed = false) {
    if (reversed) return this.getDirIdx(n + 2);
    if (n >= 4) n %= 4;
    if (n < 0) n += 4;
    return n;
  }

  turnRight() {
    this.cDir = this.getDirIdx(this.cDir + 1);
  }

  turnLeft() {
    this.cDir = this.getDirIdx(this.cDir - 1);
  }

  calculateWidth() {
    return Math.abs(this.maxY - this.minY) * Math.abs(this.maxX - this.minX);
  }
}

function solution() {
  const results = CMD.map((cmd) => {
    const turtle = new Turtle();
    [...cmd].forEach((c) => {
      switch (c) {
        case "F":
          turtle.move();
          break;
        case "B":
          turtle.move((isForward = false));
          break;
        case "R":
          turtle.turnRight();
          break;
        case "L":
          turtle.turnLeft();
          break;
      }
    });
    return turtle.calculateWidth();
  });

  results.forEach((c) => console.log(c));
}

/*
  4
  FFLF
  FFRRFF
  FFFBBBRFFFBBB
  FFBBLFFBBLFFBBLFFBBL => 16
*/
