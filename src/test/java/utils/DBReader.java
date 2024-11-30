package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBReader {

    public static List<Map<String, String>> fetch(String query) {

        String dbUrl = ConfigReader.read("dbUrl");
        String dbUserName = ConfigReader.read("dbUserName");
        String dbPassword = ConfigReader.read("dbPassword");

        List<Map<String, String>> tableData = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);) {

            ResultSetMetaData rsm = resultSet.getMetaData();

            while (resultSet.next()) {

                Map<String, String> rowMap = new LinkedHashMap<>();

                for (int i = 1; i <= rsm.getColumnCount(); i++) {
                    String key = rsm.getColumnName(i);
                    String value = resultSet.getString(i);
                    rowMap.put(key, value);
                }
                tableData.add(rowMap);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL Exception occured while fetching data: "
                     + sqlException.getMessage());
            sqlException.printStackTrace();
        }
        return tableData;
    }
}