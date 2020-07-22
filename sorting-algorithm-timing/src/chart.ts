import { Chart, ChartConfiguration, ChartTooltipItem } from "chart.js";

function loadData(file: string) {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "data/" + file, false);
  xhr.send(null);
  return xhr.response;
}

function plot(data: object[], canvas: string) {
  var config: ChartConfiguration = {
    type: "line",
    data: {
      datasets: data,
    },
    options: {
      responsive: false,
      scales: {
        xAxes: [
          {
            display: true,
            type: "linear",
            scaleLabel: {
              display: true,
              labelString: "Number of Elements",
            },
            ticks: {
              callback: (value) => value / 1000 + "K",
            },
          },
        ],
        yAxes: [
          {
            display: true,
            type: "linear",
            scaleLabel: {
              display: true,
              labelString: "Milliseconds",
            },
          },
        ],
      },
      tooltips: {
        callbacks: {
          label: function (tooltipItem: ChartTooltipItem) {
            return parseFloat(tooltipItem.yLabel).toFixed(3) + " milliseconds";
          },
        },
      },
    },
  };

  var ctx: CanvasRenderingContext2D = <CanvasRenderingContext2D>(
    (<any>document.getElementById(canvas))
  );
  var chart = new Chart(ctx, config);
}

const colors: string[] = [
  "f48484",
  "84b4f4",
  "f4c584",
  "b184f4",
  "93f484",
  "f484ab",
];
var colorIndex = 0;

function assignColor(arr: object[]) {
  arr.forEach((o: any) => {
    o["fill"] = false;
    o["backgroundColor"] = "#" + colors[colorIndex];
    o["borderColor"] = "#" + colors[colorIndex];
    colorIndex++;
  });
}

function nlogn() {
  var arr: object[] = JSON.parse(loadData("nlogn.json"));
  assignColor(arr);
  plot(arr, "nlogn");
}

function nsq() {
  var arr: object[] = JSON.parse(loadData("nsq.json"));
  assignColor(arr);
  plot(arr, "nsq");
}

function resq() {
  var arr: object[] = JSON.parse(loadData("resq.json"));
  assignColor(arr);
  plot(arr, "resq");
}

nlogn();
nsq();
colorIndex = 0;
resq();
