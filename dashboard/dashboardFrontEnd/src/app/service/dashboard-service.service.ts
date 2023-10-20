import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Dashboard } from '../entity/dashboard';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private apiUrl = 'http://localhost:8080/dashboard';

  constructor(private http: HttpClient) {}

  getAllDashboards(): Observable<Dashboard[]> {
    return this.http.get<Dashboard[]>(`${this.apiUrl}`);
  }

  getDashboardById(id: number): Observable<Dashboard> {
    return this.http.get<Dashboard>(`${this.apiUrl}/${id}`);
  }

  createDashboard(dashboard: Dashboard): Observable<Dashboard> {
    return this.http.post<Dashboard>(`${this.apiUrl}`, dashboard);
  }

  updateDashboard(id: number, dashboard: Dashboard): Observable<Dashboard> {
    return this.http.put<Dashboard>(`${this.apiUrl}/${id}`, dashboard);
  }

  deleteDashboard(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  filterAndMapData(dashboards: Dashboard[]) {
    return dashboards
      .filter(
        (dashboard) =>
          dashboard.intensity !== null &&
          dashboard.country !== null &&
          dashboard.city !== null
      )
      .map((dashboard) => ({
        id: dashboard.id,
        endYear: dashboard.endYear,
        cityLng: dashboard.cityLng,
        cityLat: dashboard.cityLat,
        intensity: dashboard.intensity,
        sector: dashboard.sector,
        topic: dashboard.topic,
        insight: dashboard.insight,
        swot: dashboard.swot,
        url: dashboard.url,
        region: dashboard.region,
        startYear: dashboard.startYear,
        impact: dashboard.impact,
        added: dashboard.added,
        published: dashboard.published,
        city: dashboard.city,
        country: dashboard.country,
        relevance: dashboard.relevance,
        pestle: dashboard.pestle,
        source: dashboard.source,
        title: dashboard.title,
        likelihood: dashboard.likelihood,
      }));
  }
}
