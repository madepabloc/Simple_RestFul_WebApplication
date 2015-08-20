package com.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.dao.MessageDao;
import com.model.Message;

public class CSVUtil {
	

	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final Object [] FILE_HEADER = {"userId","message"};
	
	public static void writeCsvFile(String fileName, ArrayList<Message>messages) {
        PrintWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            //initialize FileWriter object
            fileWriter = new PrintWriter(fileName);
            //initialize CSVPrinter object
            csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
            //Create CSV file header
            csvFilePrinter.printRecord(FILE_HEADER);
            //Write a new student object list to the CSV file
            for (Message m : messages) {
                List messagesList = new ArrayList();
                messagesList.add(String.valueOf(m.getUser_id()));
                messagesList.add(String.valueOf(m.getText()));
                csvFilePrinter.printRecord(messagesList);
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvFilePrinter.close();
//                File f = new File(fileName);
//                f.delete();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
 

