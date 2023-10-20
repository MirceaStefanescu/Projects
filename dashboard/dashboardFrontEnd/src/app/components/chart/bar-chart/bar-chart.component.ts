import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ElementRef,
  ViewChild,
} from '@angular/core';
import { tap } from 'rxjs';
import {
  BarController,
  BarElement,
  CategoryScale,
  Chart,
  ChartConfiguration,
  Legend,
  LinearScale,
  Title,
  Tooltip,
} from 'chart.js';
import { DashboardService } from '../../../service/dashboard-service.service';
import { Dashboard } from '../../../entity/dashboard';
import { BarChartDashboard } from '../../../entity/BarChartDashboard';

Chart.register(
  BarController,
  BarElement,
  CategoryScale,
  LinearScale,
  Title,
  Tooltip,
  Legend
);

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.css'],
})
export class BarChartComponent implements AfterViewInit {
  @ViewChild('chartCanvas') chartCanvas!: ElementRef<HTMLCanvasElement>;
  dataPoints: BarChartDashboard[] = [];
  isLoading = true;

  constructor(
    private dashboardService: DashboardService,
    private cdr: ChangeDetectorRef
  ) {}

  ngAfterViewInit() {
    this.makeRequest();
  }

  makeRequest() {
    this.dashboardService
      .getAllDashboards()
      .pipe(
        tap((data: Dashboard[]) => {
          this.dataPoints = this.mapToBarChartDashboard(data);
        })
      )
      .subscribe(() => {
        this.isLoading = false;
        this.cdr.detectChanges();
        this.generateBarChart(this.dataPoints, 'chartCanvas');
      });
  }

  mapToBarChartDashboard(data: Dashboard[]): BarChartDashboard[] {
    return data.map((dashboard) => ({
      intensity: dashboard.intensity,
      endYear: dashboard.endYear,
      published: dashboard.published,
    }));
  }

  generateBarChart(data: BarChartDashboard[], canvasId: string) {
    const canvas = this.chartCanvas.nativeElement;
    const ctx = canvas.getContext('2d');

    if (!ctx) {
      console.error('Failed to get 2D context');
      return;
    }

    const labels = data.map((point) => point.endYear.toString());
    const intensities = data.map((point) => point.intensity);

    const config: ChartConfiguration = {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Intensity',
            data: intensities,
            backgroundColor: 'rgba(234,240,55,0.2)',
            borderColor: 'rgb(181,56,64)',
            borderWidth: 1,
          },
        ],
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    };

    new Chart(ctx, config);
  }

  changeView(event: Event) {
    const target = event.target as HTMLSelectElement;
    const value = target.value;
    switch (value) {
      case 'option1':
        // Code for option 1
        this.changeToLineChart();
        break;
      case 'option2':
        // Code for option 2
        this.changeToBarChart();
        break;
    }
  }

  changeToLineChart() {
    const canvas = this.chartCanvas.nativeElement;
    const ctx = canvas.getContext('2d');

    if (!ctx) {
      console.error('Failed to get 2D context');
      return;
    }

    const labels = this.dataPoints.map((point) => point.endYear.toString());
    const intensities = this.dataPoints.map((point) => point.intensity);

    const config: ChartConfiguration = {
      type: 'line',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Intensity',
            data: intensities,
            backgroundColor: 'rgba(234,240,55,0.2)',
            borderColor: 'rgb(181,56,64)',
            borderWidth: 1,
          },
        ],
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    };

    new Chart(ctx, config);
  }

  chart?: Chart;

  changeToBarChart() {
    const canvas = this.chartCanvas.nativeElement;
    const ctx = canvas.getContext('2d');

    if (!ctx) {
      console.error('Failed to get 2D context');
      return;
    }

    const labels = this.dataPoints.map((point) => point.endYear.toString());
    const intensities = this.dataPoints.map((point) => point.intensity);

    const config: ChartConfiguration = {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Intensity',
            data: intensities,
            backgroundColor: 'rgba(234,240,55,0.2)',
            borderColor: 'rgb(181,56,64)',
            borderWidth: 1,
          },
        ],
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    };

    if (this.chart) {
      this.chart.destroy();
    }

    this.chart = new Chart(ctx, config);
  }
}
