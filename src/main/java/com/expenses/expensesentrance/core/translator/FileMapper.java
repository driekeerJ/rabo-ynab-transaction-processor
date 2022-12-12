package com.expenses.expensesentrance.core.translator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReaderHeaderAware;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileMapper {

    public List<Map<String, String>> mapFile(MultipartFile file) throws IOException {
        final List<Map<String, String>> valueSeparatedList = new ArrayList<>();

        final InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(file.getBytes()));

        try (CSVReaderHeaderAware reader = new CSVReaderHeaderAware(inputStreamReader)) {
            Map<String, String> line;
            while ((line = reader.readMap()) != null) {
                valueSeparatedList.add(line);
            }
        }
        log.info("Received {} number of records", valueSeparatedList.size());
        return valueSeparatedList;
    }

}
