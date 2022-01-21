package com.tendo.survey.service;

import com.tendo.survey.model.Bundle;
import com.tendo.survey.repository.BundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BundleServiceImpl implements BundleService {

    @Autowired
    private BundleRepository repository;

    @Override
    public Bundle addBundle(Bundle bundle) {
        return repository.save(bundle);
    }
    @Override
    public Bundle getBundleById(String bundleId) {
        return repository.findById(bundleId).get();
    }
    @Override
    public List<Bundle> getAllBundle(){
        return repository.findAll();
    }

    @Override
    public void updateBundle(Bundle bundle) {
        Optional<Bundle> bundleDB = repository.findById(bundle.getId());
        repository.save(bundle);
    }

    @Override
    public void deleteBundleById(String bundleId) {
        try {
            repository.deleteById(bundleId);
        }catch(DataAccessException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
