package com.csvimporter.utils;

import com.csvimporter.database.DatabaseConnection;

import org.apache.commons.csv.*;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVImporter {
    private static final String CSV_FILE_PATH = "src/main/resources/employees.csv";

    public static void importCSV() {
        String sql = "INSERT INTO employees (name, email, salary) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             Reader reader = new FileReader(CSV_FILE_PATH);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            conn.setAutoCommit(false);
            int batchSize = 0;

            for (CSVRecord record : csvParser) {
                pstmt.setString(1, record.get("name"));
                pstmt.setString(2, record.get("email"));
                pstmt.setDouble(3, Double.parseDouble(record.get("salary")));
                pstmt.addBatch();
                batchSize++;

                if (batchSize % 1000 == 0) {
                    pstmt.executeBatch();
                    conn.commit();
                    batchSize = 0;
                }
            }

            pstmt.executeBatch();
            conn.commit();
            System.out.println("CSV Data Imported Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
