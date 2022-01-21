package com.tendo.survey.parser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tendo.survey.model.Bundle;
import com.tendo.survey.repository.BundleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.persistence.PersistenceException;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class JSONParser {

    final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    private BundleRepository repository;

    public void parseJSON() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:patient-feedback.json");
        InputStream inputStream = resource.getInputStream();
        String data= "";
        try
        {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            data = new String(bdata, StandardCharsets.UTF_8);
            LOGGER.info("=== raw data ===");
            LOGGER.info(data);
            LOGGER.info("=== raw data ===");

            ObjectMapper objectMapper = new ObjectMapper();
            Bundle bundle = objectMapper.readValue(data, Bundle.class);
            LOGGER.info(bundle.toString());
            repository.save(bundle);
        }
        catch (IOException e)
        {
            LOGGER.error("IOException: ", e);
        }
        catch (PersistenceException pe) {
            LOGGER.error("Persistence Exception: ", pe);
        }
    }

}
