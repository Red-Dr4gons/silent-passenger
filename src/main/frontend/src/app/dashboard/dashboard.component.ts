import {Component, OnInit} from '@angular/core';
import * as Chart from "chart.js";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  constructor() {
  }

  private drawChart(){
    const canvas: any = document.getElementById('chartId');
    const ctx: any = canvas.getContext('2d');

    const chart = new Chart(ctx, {
      type: 'doughnut',
      data: {
        datasets: [{
          data: [5, 3],
          backgroundColor: [
            'rgb(255, 99, 132)',
            'rgb(54, 162, 235)'
          ]
        }],
        labels: ['Delivered packages', 'Not delivered packages']
      },
      options: {
        maintainAspectRatio: false,
        tooltips: {
          mode: 'index',
          intersect: false
        },
        title: {
          display: true,
          text: 'Delivery success rate'
        },
        responsive: true,
      }
    });

    const canvas2: any = document.getElementById('chartCumulative');
    const ctx2: any = canvas2.getContext('2d');

    const chart2 = new Chart(ctx2, {
      type: 'bar',
      data: {
        datasets: [{
          label: '',
          data: [0, 1.5, 3.66, 6.42, 11.98, 27.66, 40.21],
          backgroundColor: 'rgb(54, 162, 235)'
        }],
        labels: ['November 2020', 'December 2020', 'January 2021', 'February 2021', 'March 2021', 'April 2021', 'May 2021']
      },
      options: {
        maintainAspectRatio: false,
        tooltips: {
          mode: 'index',
          intersect: false
        },
        legend: {
          display: false
        },
        title: {
          display: true,
          text: 'Cumulative CO2 emissions graph in tons'
        },
        responsive: true,
      }
    });
  }

  ngOnInit() {
    this.drawChart();
  }
}
