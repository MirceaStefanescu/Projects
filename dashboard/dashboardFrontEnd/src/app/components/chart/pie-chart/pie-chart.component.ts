import { Component, OnInit } from '@angular/core';
import { Dashboard } from '../../../entity/dashboard';
import { DashboardService } from '../../../service/dashboard-service.service';
import { D3Service } from './D3Service';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css'],
})
export class PieChartComponent implements OnInit {
  isLoading = true;
  pieData: SimpleDataModel[] = [];
  textColor: string = '#ffffff';
  isPercentage: boolean = false;
  enablePolylines: boolean = false;

  public chartId;
  private svg;
  private margin = 25;
  private width = 750;
  private height = 450;

  private radius = Math.min(this.width, this.height) / 2 - this.margin;

  private colors;

  constructor(
    private d3: D3Service,
    private dashboardService: DashboardService
  ) {
    this.chartId = this.d3.generateId(5);
  }

  ngOnInit(): void {
    this.dashboardService
      .getAllDashboards()
      .subscribe((dashboards: Dashboard[]) => {
        this.pieData = dashboards
          .filter(
            (dashboard) =>
              dashboard.intensity !== null &&
              dashboard.country !== null &&
              dashboard.city !== null
          )
          .map((dashboard) => ({
            topic: dashboard.city,
            intensity: dashboard.intensity,
            country: dashboard.country,
          }));
        this.createSvg();
        this.createColors();
        this.drawChart();
        this.isLoading = false;
      });
  }

  createSvg(): void {
    this.svg = this.d3.d3
      .select('figure#pie')
      .append('svg')
      .attr('viewBox', `0 0 ${this.width} ${this.height}`)
      .append('g')
      .attr(
        'transform',
        'translate(' + this.width / 2 + ',' + this.height / 2 + ')'
      );
  }

  createColors(data = this.pieData): void {
    this.colors = this.d3.d3
      .scaleOrdinal()
      .domain(data.map((d) => d.country.toString()))
      .range([
        '#6773f1',
        '#32325d',
        '#6162b5',
        '#6586f6',
        '#8b6ced',
        '#1b1b1b',
        '#212121',
      ]);
  }

  drawChart(data = this.pieData): void {
    // Compute the position of each group on the pie
    const pie = this.d3.d3.pie<any>().value((d: any) => Number(d.value));
    const data_ready = pie(data);

    // The radius of the pieplot is half the width or half the height (smallest one). I subtract a bit of margin.
    let radius = Math.min(this.width, this.height) / 2 - this.margin;

    let outerArc = this.d3.d3
      .arc()
      .innerRadius(radius * 0.9)
      .outerRadius(radius * 0.9);

    let arc = this.d3.d3
      .arc()
      .innerRadius(radius * 0.5)
      .outerRadius(radius * 0.8);

    this.svg
      .selectAll('pieces')
      .data(data_ready)
      .enter()
      .append('path')
      .attr('d', this.d3.d3.arc().innerRadius(0).outerRadius(this.radius))
      .attr('fill', (d, i) => (d.data.color ? d.data.color : this.colors(i)))
      .attr('stroke', '#121926')
      .style('stroke-width', '1px');
    const labelLocation = this.d3.d3
      .arc()
      .innerRadius(50)
      .outerRadius(this.radius);
    let dy = 0;
    let index = 0;
    this.svg
      .selectAll('pieces')
      .data(pie(data))
      .enter()
      .append('text')
      .text((d) => {
        if (
          ((d.endAngle - d.startAngle) / (2 * Math.PI)) * 100 > 5 ||
          !this.enablePolylines
        ) {
          return (
            d.data.name +
            ' (' +
            d.data.value +
            (this.isPercentage ? '%' : '') +
            ')'
          );
        }
      })
      .attr('transform', (d) => 'translate(' + labelLocation.centroid(d) + ')')
      .style('text-anchor', 'middle')
      .style('font-size', 28)
      .attr('fill', this.textColor);
    if (this.enablePolylines) {
      this.svg
        .selectAll('allLabels')
        .data(data_ready)
        .enter()
        .append('text')
        .text((d) => {
          if (((d.endAngle - d.startAngle) / (2 * Math.PI)) * 100 > 5) {
            return null;
          } else {
            return (
              d.data.name +
              ' (' +
              d.data.value +
              (this.isPercentage ? '%' : '') +
              ')'
            );
          }
        })
        .style('font-size', '28px')
        .attr('dy', (d) => {
          if (((d.endAngle - d.startAngle) / (2 * Math.PI)) * 100 > 5) {
            return null;
          } else {
            let value = 0.35;
            if (index != 0) dy = dy + 1;
            index++;
            value = value + dy;
            return value.toString() + 'em';
          }
        })
        .attr('transform', (d) => {
          if (((d.endAngle - d.startAngle) / (2 * Math.PI)) * 100 > 5) {
            return null;
          } else {
            let pos = outerArc.centroid(d);
            let midangle = d.startAngle + (d.endAngle - d.startAngle) / 2;
            pos[0] = radius * 0.99 * (midangle < Math.PI ? 1 : -1);
            return 'translate(' + pos + ')';
          }
        })
        .style('text-anchor', (d) => {
          let midangle = d.startAngle + (d.endAngle - d.startAngle) / 2;
          return midangle < Math.PI ? 'start' : 'end';
        });
      index = 0;
      let addTo = 5;
      this.svg
        .selectAll('allPolylines')
        .data(data_ready)
        .enter()
        .append('polyline')
        .attr('stroke', 'black')
        .style('fill', 'none')
        .attr('stroke-width', 1)
        .attr('points', (d) => {
          if (((d.endAngle - d.startAngle) / (2 * Math.PI)) * 100 > 5) {
            return null;
          } else {
            let posA = arc.centroid(d); // line insertion in the slice
            let posB = outerArc.centroid(d); // line break: we use the other arc generator that has been built only for
            // that
            let posC = outerArc.centroid(d); // Label position = almost the same as posB
            let midangle = d.startAngle + (d.endAngle - d.startAngle) / 2; // we need the angle to see if the X position
            // will be at the extreme right or extreme
            // left
            posC[0] = radius * (midangle < Math.PI ? 1 : -1); // multiply by 1 or -1 to put it on the right or on the left
            posC[0] = posC[0] + addTo;
            posC[1] = posC[1] + addTo;
            addTo = addTo + 10;
            return [posA, posB, posC];
          }
        });
    }
  }
}

export interface SimpleDataModel {
  topic: string;

  intensity: number;

  country: string;

  color?: string;
}
