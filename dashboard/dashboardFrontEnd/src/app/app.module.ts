import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule, NgOptimizedImage } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatSortModule } from '@angular/material/sort';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';
import { AppRoutingModule } from './app-routing.module';
import { RawDataComponent } from './components/rawData/rawData.component';
import { BarChartComponent } from './components/chart/bar-chart/bar-chart.component';
import { ScatterPlotComponent } from './components/chart/scatter-plot/scatter-plot.component';
import { HeatmapComponent } from './components/chart/heatmap/heatmap.component';
import { WordCloudComponent } from './components/chart/word-cloud/word-cloud.component';
import { PieChartComponent } from './components/chart/pie-chart/pie-chart.component';
import { FusionChartsModule } from 'angular-fusioncharts';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    RawDataComponent,
    BarChartComponent,
    ScatterPlotComponent,
    PieChartComponent,
    HeatmapComponent,
    WordCloudComponent,
  ],
  imports: [
    CommonModule,
    MatSortModule,
    BrowserAnimationsModule,
    BrowserModule,
    HttpClientModule,
    MatButtonModule,
    MatPaginatorModule,
    AppRoutingModule,
    FusionChartsModule,
    NgOptimizedImage,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
