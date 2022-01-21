package com.tendo.survey.service;

import com.tendo.survey.model.Bundle;

import java.util.List;

public interface BundleService {

    Bundle addBundle(Bundle bundle);
    Bundle getBundleById(String bundleId);
    void updateBundle(Bundle bundle);
    void deleteBundleById(String bundleId);
    List<Bundle> getAllBundle();
}
