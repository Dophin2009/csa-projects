export module Sorting {
  export function generateRandomIntegerList(
    terms: number,
    max: number
  ): number[] {
    var nums: number[] = new Array<number>(terms);
    for (let i = 0; i < nums.length; i++) {
      nums[i] = Math.floor(Math.random() * max);
    }
    return nums;
  }

  export function generateDescendingIntegerList(
    terms: number,
    max: number
  ): number[] {
    var nums: number[] = new Array<number>(terms);
    for (let i = 0; i < nums.length; i++) {
      var gen = Math.floor(Math.random() * (max * 0.2) + max * 0.8);
      nums[i] = gen;
      max = gen;
    }
    return nums;
  }

  function swap(a: number[], i: number, j: number): void {
    var temp: number = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  export function bubbleSort(a: number[]): void {
    for (let i = 0; i < a.length; i++) {
      for (let j = 1; j < a.length - i; j++) {
        if (a[j] < a[j - 1]) {
          swap(a, j, j - 1);
        }
      }
    }
  }

  export function selectionSort(a: number[]): void {
    for (let i = 0; i < a.length; i++) {
      let min = i;
      for (let j = i; j < a.length; j++) {
        if (a[j] < a[min]) {
          min = j;
        }
      }
      swap(a, i, min);
    }
  }

  export function insertionSort(a: number[]): void {
    for (let i = 1; i < a.length; i++) {
      var key = a[i];
      var j = i - 1;

      while (j >= 0 && a[j] > key) {
        a[j + 1] = a[j];
        j--;
      }
      a[j + 1] = key;
    }
  }

  export function quickSort(a: number[]): void {
    quick(a, 0, a.length - 1);
  }

  function quick(a: number[], p: number, q: number): void {
    var i = p,
      j = q;
    if (i >= q) {
      return;
    }

    if (i == j - 1) {
      if (a[i] > a[j]) {
        swap(a, i, j);
      }
      return;
    }

    var pivot = i;
    while (i != j) {
      while (a[j] > a[pivot] && i != j) {
        j--;
      }
      while (a[i] <= a[pivot] && i != j) {
        i++;
      }

      if (i == j) {
        swap(a, i, pivot);
      } else {
        swap(a, i, j);
      }
    }

    quick(a, p, i - 1);
    quick(a, i + 1, q);
  }

  export function mergeSort(a: number[]): void {
    mergeS(a, 0, a.length - 1);
  }

  function mergeS(a: number[], p: number, q: number): void {
    if (p < q) {
      var middle: number = Math.floor((p + q) / 2);
      mergeS(a, p, middle);
      mergeS(a, middle + 1, q);

      merge(a, p, middle, q);
    }
  }

  function merge(a: number[], p: number, m: number, q: number): void {
    var low: number[] = new Array<number>(m - p + 1);
    var high: number[] = new Array<number>(q - m);

    for (let i = 0; i < low.length; i++) {
      low[i] = a[p + i];
    }
    for (let i = 0; i < high.length; i++) {
      high[i] = a[m + i + 1];
    }

    var i = 0,
      j = 0;
    var k = p;
    while (i < low.length && j < high.length) {
      if (low[i] <= high[j]) {
        a[k] = low[i];
        i++;
      } else {
        a[k] = high[j];
        j++;
      }
      k++;
    }

    while (i < low.length) {
      a[k] = low[i];
      i++;
      k++;
    }

    while (j < high.length) {
      a[k] = high[j];
      j++;
      k++;
    }
  }

  export function heapSort(a: number[]): void {
    heapS(a, a.length);
  }

  function heapS(a: number[], size: number) {
    for (let i = Math.floor(size / 2 - 1); i >= 0; i--) {
      heapify(a, size, i);
    }

    for (let i = size - 1; i >= 0; i--) {
      swap(a, 0, i);
      heapify(a, i, 0);
    }
  }

  // Bubble the greatest element to the top of the heap
  function heapify(a: number[], size: number, i: number): void {
    var largest: number = i;
    var left = 2 * i + 1;
    var right = 2 * i + 2;

    if (left < size && a[left] > a[largest]) {
      largest = left;
    }
    if (right < size && a[right] > a[largest]) {
      largest = right;
    }

    if (largest != i) {
      swap(a, i, largest);
      heapify(a, size, largest);
    }
  }

  export function treeSort(a: number[]): void {
    class Node {
      _value: number;
      _left: Node;
      _right: Node;

      constructor(value: number, left?: Node, right?: Node) {
        this._value = value;
        this._left = left;
        this._right = right;
      }

      get value(): number {
        return this._value;
      }

      set value(v: number) {
        this._value = v;
      }

      get left(): Node {
        return this._left;
      }

      set left(left: Node) {
        this._left = left;
      }

      get right(): Node {
        return this._right;
      }

      set right(right: Node) {
        this._right = right;
      }
    }

    class Tree {
      _root: Node;
      _numNodes: number;
      private sorted: number[];

      constructor(root: Node) {
        this._root = root;
        this._numNodes = 1;
      }

      insert(value: number, n: Node): void {
        if (n.value < value) {
          if (n.right == null) {
            n.right = new Node(value);
            this._numNodes++;
          } else {
            this.insert(value, n.right);
          }
        } else {
          if (n.left == null) {
            n.left = new Node(value);
            this._numNodes++;
          } else {
            this.insert(value, n.left);
          }
        }
      }

      sort(): number[] {
        sorted = [];
        this.inOrderTraversal(root);
        return sorted;
      }

      inOrderTraversal(n: Node) {
        if (n == null) {
          return;
        }
        this.inOrderTraversal(n.left);
        sorted.push(n.value);
        this.inOrderTraversal(n.right);
      }

      get root(): Node {
        return this._root;
      }
    }

    var root: Node = new Node(a[0]);
    var t: Tree = new Tree(root);
    for (let i = 0; i < a.length; i++) {
      t.insert(a[i], t.root);
    }
    var sorted: number[] = t.sort();

    for (let i = 0; i < a.length; i++) {
      a[i] = sorted[i];
    }
  }

  var a: number[] = generateRandomIntegerList(15, 15);
  treeSort(a);
  console.log(a);
}
