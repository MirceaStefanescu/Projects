import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Dashboard } from '../../../entity/dashboard';
import { DashboardService } from '../../../service/dashboard-service.service';
import FusionCharts from 'fusioncharts';

@Component({
  selector: 'app-heatmap',
  templateUrl: './heatmap.component.html',
  styleUrls: ['./heatmap.component.css'],
})
export class HeatmapComponent implements OnInit {
  isLoading = true;
  heatMapData: Dashboard[] = [];
  private salesHMChart: FusionCharts.FusionCharts | undefined;

  constructor(
    private dashboardService: DashboardService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.dashboardService
      .getAllDashboards()
      .subscribe((dashboards: Dashboard[]) => {
        this.heatMapData = this.dashboardService.filterAndMapData(dashboards);
        this.renderChart();
        this.isLoading = false;
        this.cdr.detectChanges();
      });
  }

  renderChart() {
    FusionCharts.ready(() => {
      this.salesHMChart = new FusionCharts({
        type: 'heatmap',
        renderAt: 'chart-container',
        width: '700',
        height: '350',
        dataFormat: 'json',
        dataSource: {
          chart: {
            caption: 'Top Smartphone Ratings',
            subcaption: 'By Features',
            xAxisName: 'Features',
            yAxisName: 'Model',
            showplotborder: '1',
            xAxisLabelsOnTop: '1',
            plottooltext:
              "<div id='nameDiv' style='font-size: 12px; " +
              'border-bottom: 1px dashed #666666; font-weight:bold; ' +
              'padding-bottom: 3px; margin-bottom: 5px; display: inline-block; ' +
              "color: #888888;' >$rowLabel :</div>{br}Rating : " +
              '<b>$dataValue</b>{br}$columnLabel : <b>$tlLabel</b>{br}<b>$trLabel</b>', //Cosmetics
            showValues: '1',
            showBorder: '0',
            bgColor: '#ffffff',
            showShadow: '0',
            usePlotGradientColor: '0',
            toolTipColor: '#ffffff',
            toolTipBorderThickness: '0',
            toolTipBgColor: '#000000',
            toolTipBgAlpha: '80',
            toolTipBorderRadius: '2',
            toolTipPadding: '5',
            theme: 'fusion',
          },
          dataset: [
            {
              data: this.heatMapData.map((dashboard) => ({
                rowid: dashboard.id,
                columnid: dashboard.sector,
                value: dashboard.intensity,
              })),
            },
          ],
        },
      });
      this.salesHMChart.render();
    });
  }
}
