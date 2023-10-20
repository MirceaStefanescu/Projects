package com.blackcoffer.dashboard.service;

import com.blackcoffer.dashboard.entity.Dashboard;
import com.blackcoffer.dashboard.repository.DashboardRepository;
import com.blackcoffer.dashboard.service.exceptions.DashboardNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {
    private final DashboardRepository dashboardRepository;

    @Autowired
    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public List<Dashboard> getAllDashboards() {
        List<Dashboard> dashboards = dashboardRepository.findAll();
        if (dashboards.isEmpty()) {
            throw new DashboardNotFoundException("No dashboards found");
        }
        return dashboards;
    }

    public Dashboard getDashboardById(int id) {
        Optional<Dashboard> optionalDashboard = dashboardRepository.findById((long) id);
        if (optionalDashboard.isPresent()) {
            return optionalDashboard.get();
        } else {
            throw new DashboardNotFoundException("Dashboard with ID " + id + " not found");
        }
    }

    public Dashboard createDashboard(Dashboard dashboard) {
        List<Dashboard> dashboards = getAllDashboards();
        for (Dashboard existingDashboard : dashboards) {
            if (existingDashboard.getId() == dashboard.getId()) {
                throw new IllegalArgumentException("Dashboard with ID " + dashboard.getId() + " already exists");
            }
        }
        return dashboardRepository.save(dashboard);
    }

    public Dashboard updateDashboard(int id, Dashboard updatedDashboard) {
        Optional<Dashboard> optionalDashboard = dashboardRepository.findById((long) id);
        if (optionalDashboard.isPresent()) {
            Dashboard existingDashboard = optionalDashboard.get();
            // Update the fields of the existing dashboard with the new values
            existingDashboard.setEndYear(updatedDashboard.getEndYear());
            existingDashboard.setCityLng(updatedDashboard.getCityLng());
            existingDashboard.setCityLat(updatedDashboard.getCityLat());
            existingDashboard.setIntensity(updatedDashboard.getIntensity());
            existingDashboard.setSector(updatedDashboard.getSector());
            existingDashboard.setTopic(updatedDashboard.getTopic());
            existingDashboard.setInsight(updatedDashboard.getInsight());
            existingDashboard.setSwot(updatedDashboard.getSwot());
            existingDashboard.setUrl(updatedDashboard.getUrl());
            existingDashboard.setRegion(updatedDashboard.getRegion());
            existingDashboard.setStartYear(updatedDashboard.getStartYear());
            existingDashboard.setImpact(updatedDashboard.getImpact());
            existingDashboard.setAdded(updatedDashboard.getAdded());
            existingDashboard.setPublished(updatedDashboard.getPublished());
            existingDashboard.setCity(updatedDashboard.getCity());
            existingDashboard.setCountry(updatedDashboard.getCountry());
            existingDashboard.setRelevance(updatedDashboard.getRelevance());
            existingDashboard.setPestle(updatedDashboard.getPestle());
            existingDashboard.setSource(updatedDashboard.getSource());
            existingDashboard.setTitle(updatedDashboard.getTitle());
            existingDashboard.setLikelihood(updatedDashboard.getLikelihood());
            // Save the updated dashboard
            return dashboardRepository.save(existingDashboard);
        } else {
            throw new DashboardNotFoundException("Dashboard with ID " + id + " not found");
        }
    }

    public boolean deleteDashboard(int id) {
        Optional<Dashboard> optionalDashboard = dashboardRepository.findById((long) id);
        if (optionalDashboard.isPresent()) {
            dashboardRepository.deleteById((long) id);
            return true;
        } else {
            throw new DashboardNotFoundException("Dashboard with ID " + id + " not found");
        }
    }
}
