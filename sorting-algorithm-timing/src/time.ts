import { Sorting } from "./sorting";
import { ChartDataSets } from "chart.js";
const fs = require("fs");
var moment = require("moment");

function time(
  elems: number[],
  sortAlgo: (arr: number[]) => void,
  trials: number
) {
  var times: number[] = [];

  for (let i = 0; i < elems.length; i++) {
    var terms = elems[i];

    var t: number[] = [];
    for (let i = 0; i < trials; i++) {
      var nums: number[] = Sorting.generateDescendingIntegerList(terms, terms);

      var t0 = moment();
      sortAlgo(nums);
      var t1 = moment();

      t.push(t1 - t0);
    }
    var sum = t.reduce((a, b) => a + b);
    times.push(sum / trials);
    console.log(terms + ", " + sum / trials);
  }
  return times;
}

function generateData(
  algorithms: ((a: number[]) => void)[],
  elemMult: number,
  trials: number
) {
  var elems: number[] = [];
  for (let i = 1; i < 21; i++) {
    elems[i - 1] = i * elemMult;
  }

  var data: ChartDataSets[] = [];
  for (let i = 0; i < algorithms.length; i++) {
    var values: number[] = time(elems, algorithms[i], trials);
    var pairs: { x: number; y: number }[] = [];
    for (let j = 0; j < elems.length; j++) {
      pairs.push({ x: elems[j], y: values[j] });
    }
    data.push({
      label: algorithms[i].name,
      data: pairs,
    });
  }
  return data;
}

var resq = generateData([Sorting.quickSort, Sorting.mergeSort], 300, 200);
fs.writeFile(
  "data/resq.json",
  JSON.stringify(resq, null, 4),
  "UTF-8",
  function (err: any) {
    if (err) {
      throw err;
    }
  }
);

// var nlogn = generateData([Sorting.quickSort, Sorting.mergeSort, Sorting.heapSort], 50000, 35);
// fs.writeFile("data/nlogn.json", JSON.stringify(nlogn, null, 4), 'UTF-8', function (err: any) {
//     if (err) {
//         throw err;
//     }
// });

// var nsq = generateData([Sorting.bubbleSort, Sorting.insertionSort, Sorting.selectionSort], 1500, 25);
// fs.writeFile("data/nsq.json", JSON.stringify(nsq, null, 4), 'UTF-8', function (err: any) {
//     if (err) {
//         throw err;
//     }
// });
