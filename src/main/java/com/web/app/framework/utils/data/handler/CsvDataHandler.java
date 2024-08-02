package com.web.app.framework.utils.data.handler;

import static com.web.app.framework.utils.data.handler.CsvFileController.*;

import java.util.List;

public class CsvDataHandler {
	
	public static String[][] readDataFromCsv(String fileName) {
		String[][] data = null;
		List<String[]> csvRecords = fetchAllDataInCsv(fileName);
		data = new String[csvRecords.size()][csvRecords.get(0).length];
		for (int i = 0; i < csvRecords.size(); i++) {
			for (int j = 0; j < csvRecords.get(i).length; j++) {
				data[i][j] = csvRecords.get(i)[j];
			}			
		}
		return data;
	}

}