import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';

import { ScatterPlotDataPoint } from '../../../entity/ScatterPlotDataPoint';
import { Dashboard } from '../../../entity/dashboard';
import { Observable } from 'rxjs';
import { DashboardService } from '../../../service/dashboard-service.service';

@Component({
  selector: 'app-scatter-plot',
  templateUrl: './scatter-plot.component.html',
  styleUrls: ['./scatter-plot.component.css'],
})
export class ScatterPlotComponent implements AfterViewInit {
  isLoading = true;
  @ViewChild('scatterPlotCanvas', { static: false })
  canvas!: ElementRef<HTMLCanvasElement>;

  constructor(private service: DashboardService) {}

  ngAfterViewInit(): void {
    this.correlationAnalysis().subscribe((dashboards: Dashboard[]) => {
      const data: ScatterPlotDataPoint[] =
        this.mapToScatterPlotData(dashboards);
      this.generateScatterPlotWithDelay(data);
    });
  }

  private mapToScatterPlotData(
    dashboards: Dashboard[]
  ): ScatterPlotDataPoint[] {
    return dashboards.map((dashboard: Dashboard) => {
      return {
        citylng: dashboard.cityLng,
        citylat: dashboard.cityLat,
        intensity: dashboard.intensity,
        impact: dashboard.impact,
        relevance: dashboard.relevance,
        likelihood: dashboard.likelihood,
      };
    });
  }

  private generateScatterPlotWithDelay(data: ScatterPlotDataPoint[]): void {
    setTimeout(() => {
      this.generateScatterPlot(data);
      this.isLoading = false;
    });
  }

  correlationAnalysis(): Observable<Dashboard[]> {
    return this.service.getAllDashboards();
  }

  generateScatterPlot(data: ScatterPlotDataPoint[]) {
    if (!this.canvas) {
      console.error('Canvas element is not defined');
      return;
    }

    const canvasElement = this.canvas.nativeElement;
    const context = canvasElement.getContext('2d');
    if (!context) {
      console.error('Failed to get 2D context');
      return;
    }

    this.clearCanvas(canvasElement, context);
    this.drawXAxis(canvasElement, context);
    this.drawYAxis(canvasElement, context);
    this.drawDataPoints(canvasElement, context, data);
  }

  clearCanvas(canvas: HTMLCanvasElement, context: CanvasRenderingContext2D) {
    context.clearRect(0, 0, canvas.width, canvas.height);
  }

  drawXAxis(canvas: HTMLCanvasElement, context: CanvasRenderingContext2D) {
    const xPadding = 50;
    context.beginPath();
    context.moveTo(xPadding, canvas.height - xPadding);
    context.lineTo(canvas.width, canvas.height - xPadding);
    context.stroke();
  }

  drawYAxis(canvas: HTMLCanvasElement, context: CanvasRenderingContext2D) {
    const xPadding = 50;
    context.beginPath();
    context.moveTo(xPadding, 0);
    context.lineTo(xPadding, canvas.height - xPadding);
    context.stroke();
  }

  drawDataPoints(
    canvas: HTMLCanvasElement,
    context: CanvasRenderingContext2D,
    data: ScatterPlotDataPoint[]
  ) {
    const xPadding = 50;
    const yPadding = 50;
    const xMax = Math.max(...data.map((point) => point.citylng)) + 10;
    const yMax = Math.max(...data.map((point) => point.citylat)) + 10;
    const xScale = (canvas.width - xPadding) / xMax;
    const yScale = (canvas.height - yPadding) / yMax;

    for (const point of data) {
      const x = xPadding + point.citylng * xScale;
      const y = canvas.height - yPadding - point.citylat * yScale;

      context.beginPath();
      context.arc(x, y, 5, 0, Math.PI * 2);
      context.fillStyle = 'blue';
      context.fill();
      context.stroke();
    }
  }
}
