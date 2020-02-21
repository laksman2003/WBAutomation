package com.wallethub.automation.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

public class TestDataUtil {

	/**
	 * This method will get the value of particular Test case Id(row) and column name (column)
	 * @param testcaseid
	 * @param columnnname
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static String readCSVFile(String testcaseid, String columnname) throws FileNotFoundException
	{
		String value = "";
		String datapath = System.getProperty("user.dir")+File.separator+"TestData.csv";
		FileReader reader = new FileReader(datapath);
		
		CsvParserSettings settings = new CsvParserSettings();
		settings.getFormat().setLineSeparator("\n");		
		settings.selectFields("TestCaseId", columnname);
		CsvParser parser = new CsvParser(settings);
		List<String[]> rows = parser.parseAll(reader);
		
        for (String[] row: rows)
        {   
        	for(String columnvalue : row)
        	{   
        		if(columnvalue!=null)
        		{
        			if(columnvalue.toLowerCase().equals(testcaseid.toLowerCase()))
            		{
            			value = row[1];
            			break;
            		}
        		}
        		
        	}
        }
		return value;
	}
	
}
