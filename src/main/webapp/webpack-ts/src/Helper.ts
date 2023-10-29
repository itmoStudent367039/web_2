import { InputValidator } from "./InputValidator";
import { TableWorker } from "./TableWorker";
import { Graph } from "./Graph";
import {alertError, alertSuccess} from "./SweetAlert";

const graph = new Graph();
const tableWorker = new TableWorker();
const inputValidator = new InputValidator();

export async function clearTable() {
  const response: Response | null = await sendRequest(
    {
      invalidate: "session",
    },
    "./controller",
    "DELETE",
  );
  if (response && response.ok) {
    const info = await response.json();
    if (info?.status === "successfully") {
      tableWorker.deleteAllRows();
      const radius: number | null = findAndReturnSelectedRadius();
      if (radius !== null) {
        graph.redrawAll(radius);
      }
      await alertSuccess("Table was cleared");
      return;
    }
  }
  await alertError("Table wasn't cleared");
}

export async function addRadiusChangeListener() {
  const radius: number | null = findAndReturnSelectedRadius();
  if (radius !== null) {
    graph.redrawAll(radius);
  }
  drawAllPoints(graph);
}

function findAndReturnSelectedRadius(): number | null {
  const selectedElement = document.querySelector("input[name='R']:checked");
  if (selectedElement instanceof HTMLInputElement) {
    return +selectedElement.value;
  }
  return null;
}

export async function addCheckButtonListener() {
  if (inputValidator.validateX() && inputValidator.validateY()) {
    await sendPoint(
      inputValidator.getX,
      inputValidator.getY,
      findAndReturnSelectedRadius().toString(),
    );
  }
}

async function sendPoint(x: string, y: string, r: string) {
  const response: Response | null = await sendRequest(
    {
      x: x,
      y: y,
      r: r,
    },
    "./controller",
    "GET",
  );
  if (response && response.ok) {
    const info: string | null = await response.text();
    if (info) {
      tableWorker.innerRows(info);
      const radius = findAndReturnSelectedRadius();
      if (radius) {
        graph.redrawAll(radius);
      }
      drawAllPoints(graph);
    }
  }
}

function drawAllPoints(canvasPrinter: Graph): void {
  const data = tableWorker.getData();
  data.forEach((point) => {
    canvasPrinter.drawPoint(
      +point?.x,
      +point?.y,
      (point?.inRange?.toLowerCase() ?? "") === "true",
    );
  });
}

async function sendRequest(
  parameters: { [key: string]: string | number },
  url: string,
  method: string,
): Promise<Response | null> {
  try {
    const headers = new Headers();
    for (let parametersKey in parameters) {
      headers.append(parametersKey, parameters[parametersKey].toString());
    }
    return await fetch(url, {
      method: method,
      headers: headers,
    });
  } catch (e) {
    console.log(e);
  }
  return null;
}

document
  .getElementById("graph")
  .addEventListener("click", async function (event) {
    const rect = this.getBoundingClientRect();
    const x: string = (
      (7 / graph.SIZE) *
      (event.clientX - rect.left - graph.SIZE / 2)
    ).toString();
    const y: string = (
      (7 / graph.SIZE) *
      (graph.SIZE / 2 - event.clientY + rect.top)
    ).toString();

    const radius: string = findAndReturnSelectedRadius()?.toString();
    const regex: RegExp = inputValidator.FROM_MINUS_THREE_TILL_THREE;

    if (radius && regex.test(x) && regex.test(y)) {
      await sendPoint(x, y, radius);
    }
  });

document
  .getElementById("checkButton")
  .addEventListener("click", addCheckButtonListener);

document.getElementById("clearButton").addEventListener("click", clearTable);

document
  .getElementById("radio-radius")
  .addEventListener("change", addRadiusChangeListener);

document.addEventListener("DOMContentLoaded", async function () {
  const radius: number | null = findAndReturnSelectedRadius();
  if (radius !== null) {
    graph.redrawAll(radius);
  }
  drawAllPoints(graph);
});
