import { Component, AfterViewInit } from '@angular/core';
import { Chart } from 'chart.js/auto';

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements AfterViewInit {
  public chart1: any; // Référence pour le premier graphique
  public chart2: any; // Référence pour le deuxième graphique

  ngAfterViewInit(): void {
    this.createChart1();
    this.createChart2();
  }

  createChart1(): void {
    this.chart1 = new Chart('myChart1', {
      type: 'bar',
      data: {
        labels: ['Article 1', 'Article 2', 'Article 3', 'Article 4', 'Article 5'],
        datasets: [
          {
            label: 'Nombre de vues',
            data: [120, 95, 80, 65, 50],
            backgroundColor: ['#e7cccc', '#c1cfa1', '#eaaaaa', '#515c37', '#ede8dc'],
            borderColor: ['#e7cccc', '#c1cfa1', '#eaaaaa', '#515c37', '#ede8dc'],
            borderWidth: 1
          }
        ]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'top'
          }
        }
      }
    });
  }

  createChart2(): void {
    this.chart2 = new Chart('myChart2', {
      type: 'pie',
      data: {
        labels: ['Category 1', 'Category 2', 'Category 3', 'Category 4', 'Category 5'],
        datasets: [
          {
            label: 'Proportions',
            data: [30, 25, 20, 15, 10],
            backgroundColor: ['#e7cccc', '#c1cfa1', '#eaaaaa', '#515c37', '#ede8dc'],
            borderColor: ['#e7cccc', '#c1cfa1', '#eaaaaa', '#515c37', '#ede8dc'],
            borderWidth: 1
          }
        ]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'top'
          }
        }
      }
    });
  }
}
