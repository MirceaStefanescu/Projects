import { Component, OnInit } from '@angular/core';
import { DashboardService } from '../../../service/dashboard-service.service';
import { Dashboard } from '../../../entity/dashboard';
import * as d3 from 'd3';
import * as d3Cloud from 'd3-cloud';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-word-cloud',
  templateUrl: './word-cloud.component.html',
  styleUrls: ['./word-cloud.component.css'],
})
export class WordCloudComponent implements OnInit {
  isLoading = false;
  wordCloudData: WordCloudModel[] = [];
  wordCloudLayout: Word[] = [];

  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    this.getDashboards();
  }

  getDashboards(): void {
    this.dashboardService
      .getAllDashboards()
      .pipe(
        map((dashboards) => this.filterDashboards(dashboards)),
        map((dashboards) => this.mapToWordCloudModel(dashboards))
      )
      .subscribe((wordCloudModels) => this.handleSubscription(wordCloudModels));
  }

  filterDashboards(dashboards: Dashboard[]): Dashboard[] {
    const uniqueDashboards: Dashboard[] = [];
    const citySet: Set<string> = new Set();

    dashboards.forEach((dashboard) => {
      if (
        dashboard.city !== null &&
        dashboard.likelihood !== null &&
        !citySet.has(dashboard.city)
      ) {
        citySet.add(dashboard.city);
        uniqueDashboards.push(dashboard);
      }
    });

    return uniqueDashboards;
  }

  mapToWordCloudModel(dashboards: Dashboard[]): WordCloudModel[] {
    return dashboards.map(
      (dashboard) =>
        ({
          city: dashboard.city,
          likelihood: dashboard.likelihood,
        } as WordCloudModel)
    );
  }

  handleSubscription(wordCloudModels: WordCloudModel[]): void {
    this.wordCloudData = wordCloudModels;
    this.wordCloudLayout = this.calculateLayout(this.wordCloudData);
    this.drawWordCloud(this.wordCloudLayout);
    this.isLoading = false;
  }

  calculateLayout(wordCloudModels: WordCloudModel[]): Word[] {
    const totalLikelihood = wordCloudModels.reduce(
      (total, model) => total + model.likelihood,
      0
    );

    const words: Word[] = wordCloudModels.map((model) => ({
      text: model.city,
      size: (model.likelihood / totalLikelihood) * 100,
    }));

    words.forEach((word, i) => {
      word['x'] = Math.random() * window.innerWidth;
      word['y'] = Math.random() * window.innerHeight;
      word['rotate'] = i * 5;
    });

    return words;
  }

  drawWordCloud(words: Word[]): void {
    const svg = d3
      .select('#word-cloud')
      .append('svg')
      .attr('width', window.innerWidth)
      .attr('height', window.innerHeight);

    const wordCloud = d3Cloud()
      .size([window.innerWidth, window.innerHeight])
      .words(words)
      .rotate((d) => d.rotate)
      .fontSize((d) => d.size)
      .on('end', draw);

    wordCloud.start();

    function draw(words: Word[]) {
      svg
        .append('g')
        .attr('transform', 'translate(500,250)')
        .selectAll('text')
        .data(words)
        .enter()
        .append('text')
        .style('font-size', (d) => `${d.size}px`)
        .style('fill', '#000')
        .attr('text-anchor', 'middle')
        .attr('transform', (d) => `translate(${[d.x, d.y]})rotate(${d.rotate})`)
        .text((d) => d.text);
    }
  }
}

export interface WordCloudModel {
  city: string;

  likelihood: number;
}

export interface Word {
  text: string;

  size: number;

  x?: number;

  y?: number;

  rotate?: number;
}
