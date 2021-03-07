/**
 * 전광판의 숫자  16159
 * 생각보다 시간이 너무 걸려 nextPermutation 코드는 leetcode에서 가져왔습니다.
 * ref
 *  - [leetcode]  https://leetcode.com/problems/next-permutation/discuss/903596/O(n)-JS-Solution-with-explanation
 *  - [js prototype programming] https://www.freecodecamp.org/news/removing-javascripts-this-keyword-makes-it-a-better-language-here-s-why-db28060cc086/

 */

function Solution(boards) {
  const ROW = 6;
  const COL = 7;
  const NUMBERS = [
    ["000000", "001100", "010010", "010010", "010010", "001100", "000000"],
    ["000000", "000100", "001100", "000100", "000100", "000100", "000000"],
    ["000000", "011110", "000010", "011110", "010000", "011110", "000000"],
    ["000000", "011100", "000010", "000100", "000010", "011100", "000000"],
    ["000000", "000100", "001100", "010100", "111110", "000100", "000000"],
    ["000000", "011110", "010000", "011100", "000010", "010010", "001100"],
    ["000000", "010000", "010000", "011110", "010010", "011110", "000000"],
    ["000000", "011110", "000010", "000100", "000100", "000100", "000000"],
    ["000000", "011110", "010010", "011110", "010010", "011110", "000000"],
    ["011110", "010010", "010010", "011110", "000010", "000010", "000010"],
  ];

  function decodeNumber(x) {
    let number;
    NUMBERS.some((encodedNumber, num) => {
      const notMatched = encodedNumber.some((row, y) => {
        return row !== boards[y].slice(x, x + ROW);
      });

      if (!notMatched) {
        number = num;
        return true;
      }
    });

    return number;
  }

  function encodeNumbers(numbers) {
    const encodedNumbers = [...Array(COL).keys()].map((y) => {
      let row = "";
      numbers.forEach((num) => {
        row += NUMBERS[num][y];
      });

      return row;
    });

    return encodedNumbers.join("\n");
  }

  function failed() {
    return "The End";
  }

  function nextPermutation(nums) {
    let pivotIdx = -1;
    for (let i = nums.length - 1; i > 0; i--) {
      if (nums[i - 1] < nums[i]) {
        pivotIdx = i - 1;
        break;
      }
    }

    if (pivotIdx == -1) {
      // the array is in descending order
      nums.reverse();
      return nums;
    }

    for (let i = nums.length - 1; i > pivotIdx; i--) {
      if (nums[pivotIdx] < nums[i]) {
        [nums[pivotIdx], nums[i]] = [nums[i], nums[pivotIdx]];
        break;
      }
    }

    // Now, we need to reverse the subarray after our pivotIdx.
    let left = pivotIdx + 1,
      right = nums.length - 1;

    while (left < right) {
      [nums[left], nums[right]] = [nums[right], nums[left]];
      left++, right--;
    }
    return nums;
  }

  function start() {
    const n = boards[0].length / ROW;
    if (n <= 1) return failed();

    const decodedNumbers = [...Array(n).keys()].map((i) =>
      decodeNumber(i * ROW)
    );

    const nextNumbers = nextPermutation([...decodedNumbers]);
    return nextNumbers > decodedNumbers ? encodeNumbers(nextNumbers) : failed();
  }

  return Object.freeze({
    start,
  });
}

(function init() {
  const lines = [];
  require("readline")
    .createInterface({
      input: process.stdin,
      output: process.stdout,
      terminal: false, // to avoid duplicate stdin
    })
    .on("line", function (line) {
      lines.push(line);
    })
    .on("close", function () {
      const result = Solution(lines).start();
      console.log(result);
      process.exit();
    });
})();
