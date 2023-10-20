import { Component, OnInit, ViewChild } from '@angular/core';
import { state, style, trigger } from '@angular/animations';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DashboardService } from '../../service/dashboard-service.service';
import { Dashboard } from '../../entity/dashboard';
import { Options } from '../../entity/Options';

@Component({
  selector: 'app-dashboard',
  templateUrl: './rawData.component.html',
  styleUrls: ['./rawData.component.css'],
  animations: [trigger('arrowPosition', [state('start', style({}))])],
})
export class RawDataComponent implements OnInit {
  options: Options = {
    page: 1,
    size: 5,
  };

  dataSource: MatTableDataSource<Dashboard> =
    new MatTableDataSource<Dashboard>();
  @ViewChild(MatSort) sort!: MatSort;
  isLoading = false;
  pagedDashboards: Dashboard[] = [];

  constructor(private dashboardService: DashboardService) {}

  ngOnInit(): void {
    this.makeRequest();
    this.getAllDashboards();
  }

  makeRequest() {
    this.isLoading = true;
  }

  getAllDashboards(): void {
    this.isLoading = true; // Show the loading spinner
    this.dashboardService.getAllDashboards().subscribe({
      next: (dashboards: Dashboard[]) => {
        this.dataSource = new MatTableDataSource<Dashboard>(dashboards);
        // apply the sorting behavior
        if (this.sort) {
          this.dataSource.sort = this.sort;
        }
        this.updatePagedDashboards();
        this.isLoading = false; // Hide the loading spinner
      },
      // rest of the code
    });
  }

  updatePagedDashboards(): void {
    const startIndex = (this.options.page - 1) * this.options.size;
    const endIndex = startIndex + this.options.size;
    this.pagedDashboards = this.dataSource.data.slice(startIndex, endIndex);
  }

  size(size: number) {
    this.options.size = size;
    this.options.page = 1;
    this.getAllDashboards();
  }

  sortData(event: Sort) {
    const data = this.pagedDashboards.slice();
    if (!event.active || event.direction === '') {
      return;
    }

    function compare(a: number | string, b: number | string, isAsc: boolean) {
      return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
    }

    this.pagedDashboards = data.sort((a, b) => {
      const isAsc = event.direction === 'asc';
      switch (event.active) {
        case 'id':
          return compare(a.id, b.id, isAsc);
        case 'end_year':
          return compare(a.endYear, b.endYear, isAsc);
        case 'citylng':
          return compare(a.cityLng, b.cityLng, isAsc);
        case 'citylat':
          return compare(a.cityLat, b.cityLat, isAsc);
        case 'intensity':
          return compare(a.intensity, b.intensity, isAsc);
        default:
          return 0;
      }
    });
  }

  getDashboardById(id: number): void {
    this.dashboardService.getDashboardById(id).subscribe({
      next: (dashboard: Dashboard) => {
        console.log(dashboard);
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }

  createDashboard(dashboard: Dashboard): void {
    this.dashboardService.createDashboard(dashboard).subscribe({
      next: (createdDashboard: Dashboard) => {
        console.log(createdDashboard);
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }

  updateDashboard(id: number, dashboard: Dashboard): void {
    this.dashboardService.updateDashboard(id, dashboard).subscribe({
      next: (updatedDashboard: Dashboard) => {
        console.log(updatedDashboard);
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }

  deleteDashboard(id: number): void {
    this.dashboardService.deleteDashboard(id).subscribe({
      next: () => {
        console.log('Dashboard deleted successfully.');
      },
      error: (error: any) => {
        console.log(error);
      },
    });
  }
}
