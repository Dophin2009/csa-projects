export module Searching {
  export function linearSearch(a: number[], v: number): number {
    for (let i = 0; i < a.length; i++) {
      if (a[i] == v) {
        return i;
      }
    }
    return -1;
  }

  export function binarySearch(sortedList: number[], v: number): number {
    return binSearch(sortedList, v, 0, sortedList.length);
  }

  function binSearch(a: number[], v: number, p: number, q: number): number {
    if (p >= q) {
      return a[p] == v ? p : -1;
    }

    var m = Math.floor((p + q) / 2);
    if (v < a[m]) {
      return binSearch(a, v, p, m - 1);
    }
    if (v > a[m]) {
      return binSearch(a, v, m + 1, q);
    }
    return m;
  }
}
