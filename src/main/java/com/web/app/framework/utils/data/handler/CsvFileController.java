package com.web.app.framework.utils.data.handler;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class CsvFileController {
	
	public static List<String[]>  fetchAllDataInCsv(String fileName) {
		  CSVReader reader;
		  List<String[]>  rowRecords = null;
		  try {
			  reader = new CSVReaderBuilder(
					   new FileReader(System.getProperty("user.dir")+"/data/"+fileName+".csv"))
			          .withSkipLines(1).build();
			rowRecords = reader.readAll();
		} catch (IOException | CsvException e) {
			e.printStackTrace();
		}
		return rowRecords;		  
	}

}