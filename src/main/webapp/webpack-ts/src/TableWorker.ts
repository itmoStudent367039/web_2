export class TableWorker {
  private tBody: HTMLElement | null;

  constructor() {
    this.tBody = document.getElementById(
      "tableData",
    ) as HTMLTableSectionElement;
  }

  innerRows(tableRows: string): void {
    this.tBody.innerHTML = tableRows;
  }

  getPointsAsXml(): string {
    return this.tBody.innerHTML;
  }

  getData() {
    let rows = document.querySelector("table").rows;
    const array = [];
    for (let i = 1; i < rows.length; i++) {
      const row = rows[i];
      const requestTime = row.cells[0].innerText;
      const currentTime = row.cells[1].innerText;
      const inRange = row.cells[2].innerText;
      const x = row.cells[3].innerText;
      const y = row.cells[4].innerText;
      const r = row.cells[5].innerText;
      array.push({
        requestTime: requestTime,
        currentTime: currentTime,
        inRange: inRange,
        x: x,
        y: y,
        r: r,
      });
    }

    return array;
  }

  deleteAllRows(): void {
    this.tBody.innerHTML = "";
  }
}
