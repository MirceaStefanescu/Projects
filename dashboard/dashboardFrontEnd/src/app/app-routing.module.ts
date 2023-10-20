import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RawDataComponent } from './components/rawData/rawData.component';
import { BarChartComponent } from './components/chart/bar-chart/bar-chart.component';
import { HeatmapComponent } from './components/chart/heatmap/heatmap.component';
import { PieChartComponent } from './components/chart/pie-chart/pie-chart.component';
import { ScatterPlotComponent } from './components/chart/scatter-plot/scatter-plot.component';
import { WordCloudComponent } from './components/chart/word-cloud/word-cloud.component';

const routes: Routes = [
  { path: 'raw', component: RawDataComponent }, // Define a route for RawDataComponent
  { path: 'bar-chart', component: BarChartComponent },
  { path: 'heatmap', component: HeatmapComponent },
  { path: 'pie-chart', component: PieChartComponent },
  { path: 'scatter-plot', component: ScatterPlotComponent },
  { path: 'word-cloud', component: WordCloudComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
