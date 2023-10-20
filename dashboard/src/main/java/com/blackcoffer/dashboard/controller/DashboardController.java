package com.blackcoffer.dashboard.controller;

import com.blackcoffer.dashboard.entity.Dashboard;
import com.blackcoffer.dashboard.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public List<Dashboard> getAllDashboards() {
        return dashboardService.getAllDashboards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dashboard> getDashboardById(@PathVariable int id) {
        Dashboard dashboard = dashboardService.getDashboardById(id);
        if (dashboard != null) {
            return ResponseEntity.ok(dashboard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Dashboard> createDashboard(@RequestBody Dashboard dashboard) {
        Dashboard createdDashboard = dashboardService.createDashboard(dashboard);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDashboard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dashboard> updateDashboard(@PathVariable int id, @RequestBody Dashboard dashboard) {
        Dashboard updatedDashboard = dashboardService.updateDashboard(id, dashboard);
        if (updatedDashboard != null) {
            return ResponseEntity.ok(updatedDashboard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDashboard(@PathVariable int id) {
        boolean deleted = dashboardService.deleteDashboard(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}