const ENDPOINT_SENTENCE_LENG = "http://localhost:5503/sent?s=",
  ENDPOINT_SENTENCE = "http://localhost:5503/sent-per";

function updateLabel() {
  document.getElementById("lengthDisplay").innerHTML = (<HTMLInputElement>(
    document.getElementById("lengthSlider")
  )).value;
}

function generateSentence() {
  const loadingMessage: HTMLElement = document.getElementById("loading");
  const generatedList = document.getElementById("present");

  // const lengthSlider: HTMLInputElement = <HTMLInputElement> document.getElementById("lengthSlider");
  // const lengthQuery = lengthSlider.value;

  const xhr: XMLHttpRequest = new XMLHttpRequest();
  // xhr.open("GET", ENDPOINTS.SENTENCE_LENG + lengthQuery);
  xhr.open("GET", ENDPOINT_SENTENCE);
  xhr.onreadystatechange = () => {
    if (xhr.readyState == 4 && xhr.status == 200) {
      const sentence =
        xhr.responseText.substr(0, 1).toUpperCase() +
        xhr.responseText.substr(1);

      const h = document.createElement("H2");
      h.innerHTML = sentence;
      generatedList.prepend(h);
      //loadingMessage.innerHTML = "";
    } else {
      //loadingMessage.innerHTML = "Oh no, error code " + xhr.status + "!"
    }
  };
  xhr.send();
}
