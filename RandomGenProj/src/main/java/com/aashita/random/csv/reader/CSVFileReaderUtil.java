package com.aashita.random.csv.reader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


public class CSVFileReaderUtil {


	enum StockFileHeaders {
		stock, tradedate, open, high, low, close, volume
	}
	public enum FIRSTNAME_HEADER {  
		firstnames
	}
	public enum LASTNAME_HEADER {  
		lastnames
	}
	enum SALES_FILE_HEADER{
		region,country,itemtype,saleschannel,orderpriority,orderdate,orderid,shipdate,unitssold,unitprice,unitcost,totalrevenue,totalcost,totalprofit
	}

	static String[] HEADERS = { "stock", "tradedate", "open", "high", "low", "close", "volume" };
	static String[] SALES_FILE_HDR_ARRAY = { "region", "country", "itemtype", "saleschannel", "orderpriority", "orderdate", "orderid", 
											 "shipdate", "unitssold", "unitprice", "unitcost", "totalrevenue", "totalcost", "totalprofit" };
	
	
	public static void main(String[] args) throws IOException {
		
//		readFirstNames();
//		readLastNames();

		readSalesDataFile();
	
	}
	
	
	
	public static List<CSVRecord> readFirstNames() throws IOException {
		
		String filename = "C:\\Users\\SHARANABASAPPAPATIL\\data\\firstnames.csv";
		String[] HEADERS1 = {"firstnames"};
		 Reader in = new StringReader("NoData");
		 CSVParser parser = CSVFormat.DEFAULT.withHeader(HEADERS1).parse(in);
		 
		List<CSVRecord> list = parser.getRecords();
		
		if (!isFileExists(filename)) {
			System.err.println("1.File Not exists [" + filename + "], please verify  ");
			return list;
		}

		Reader inputfile = new FileReader(filename);
		list = CSVFormat.DEFAULT.withHeader(HEADERS1).parse(inputfile).getRecords();
		// .withFirstRecordAsHeader(

		Set<String> set1 = new HashSet<String>();
		String fn = "";
		
		for (CSVRecord record : list) {
			fn = record.get(FIRSTNAME_HEADER.firstnames);
			set1.add(fn);
//			System.out.println(fn);
		}
		
		System.out.println("File read : " + filename + "; it has FirstName-records : "+ set1.size());
		return list;

	}
	
	public static List<CSVRecord> readLastNames() throws IOException {
		
		String filename = "C:\\Users\\SHARANABASAPPAPATIL\\data\\lastnames.csv";
		String[] HEADERS1 = {"lastnames"};
		 Reader in = new StringReader("NoData");
		 CSVParser parser = CSVFormat.DEFAULT.withHeader(HEADERS1).parse(in);
		 
		 
		List<CSVRecord> list = parser.getRecords();
		
		if (!isFileExists(filename)) {
			System.err.println("1.File Not exists [" + filename + "], please verify  ");
			return list;
		}

		Reader inputfile = new FileReader(filename);
		list = CSVFormat.DEFAULT.withHeader(HEADERS1).parse(inputfile).getRecords();
		// .withFirstRecordAsHeader(


		Set<String> set1 = new HashSet<String>();
		String ln = "";
		
		for (CSVRecord record : list) {
			ln = record.get(LASTNAME_HEADER.lastnames);
			set1.add(ln);
//			System.out.println(ln);
		}
		
		
		System.out.println("File read : " + filename + "; it has LastName - records : "+ set1.size());
		return list;

	}
	
	

	public static List<CSVRecord> readSalesDataFile() throws IOException {
		
		String filename = "C:\\Users\\SHARANABASAPPAPATIL\\data\\1000SalesRecords.csv";
		 Reader in = new StringReader("NoData");
		 CSVParser parser = CSVFormat.DEFAULT.withHeader(SALES_FILE_HDR_ARRAY).withIgnoreHeaderCase().parse(in);
		 
		List<CSVRecord> list = parser.getRecords();
		
		if (!isFileExists(filename)) {
			System.err.println("1.File Not exists [" + filename + "], please verify  ");
			return list;
		}

		Reader inputfile = new FileReader(filename);
		list = CSVFormat.DEFAULT.withHeader(SALES_FILE_HDR_ARRAY)				
				.withFirstRecordAsHeader()
				.parse(inputfile).getRecords();

		String fn = "";
		
		for (CSVRecord record : list) {
			fn = record.get(SALES_FILE_HEADER.region); 
			fn = record.get(SALES_FILE_HEADER.country); 
			fn = record.get(SALES_FILE_HEADER.itemtype); 
			fn = record.get(SALES_FILE_HEADER.saleschannel); 
			fn = record.get(SALES_FILE_HEADER.orderpriority); 
			fn = record.get(SALES_FILE_HEADER.orderdate); 
			fn = record.get(SALES_FILE_HEADER.orderid); 
			fn = record.get(SALES_FILE_HEADER.shipdate); 
			fn = record.get(SALES_FILE_HEADER.unitssold); 
			fn = record.get(SALES_FILE_HEADER.unitprice); 
			fn = record.get(SALES_FILE_HEADER.unitcost); 
			fn = record.get(SALES_FILE_HEADER.totalrevenue); 
			fn = record.get(SALES_FILE_HEADER.totalcost); 
			fn = record.get(SALES_FILE_HEADER.totalprofit);

			System.out.println(record);
		}
		
		System.out.println("File read : " + filename + "; it has FirstName-records : "+ list.size());
		return list;

	}

		static boolean isFileExists(String filePath) throws IOException {
			File f = new File(filePath);

			// Check if the specified file, Exists or not
			if (f.exists()) {
				System.out.println(filePath + "	Exists");
				return true;
			} else {
				System.out.println(filePath + "	Does not Exists");
				return false;
			}

		}

		@SuppressWarnings("unused")
		private static void writeFiles(String filename, String outfilename) throws IOException {
			String stock, volume, tradedate, open, high, low, close;
			int ctr = 0;

			FileWriter outWriter = new FileWriter(outfilename);
			CSVPrinter printer = null;
			CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(HEADERS);
			printer = csvFormat.print(outWriter);

			Reader inputfile = new FileReader(filename);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).parse(inputfile);
			// .withFirstRecordAsHeader()

			printer.printRecords(records);
			printer.flush();

		}

		@SuppressWarnings("unused")
		public void givenCSVFile_whenRead_thenContentsAsExpected(String filename) throws IOException {
			String stock, volume, tradedate, open, high, low, close;

			Reader inputfile = new FileReader(filename);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS)
					// .withFirstRecordAsHeader()
					.parse(inputfile);

			for (CSVRecord record : records) {
				stock = record.get(StockFileHeaders.stock);
				volume = record.get(StockFileHeaders.volume);
				tradedate = record.get(StockFileHeaders.tradedate);
				open = record.get(StockFileHeaders.open);

				if (open.contains("-")) {
					System.err.println("Incorrect record : " + record);
				}

				high = record.get(StockFileHeaders.high);
				low = record.get(StockFileHeaders.low);
				close = record.get(StockFileHeaders.close);

				System.out.println(record);
			}

		}

		@SuppressWarnings("unused")
		private static void readFiles(String filename) throws IOException {
			Reader in = new FileReader(filename);
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
			for (CSVRecord record : records) {
				String columnOne = record.get(0);
				String columnTwo = record.get(1);
				System.out.println(columnOne + ", " + columnTwo);
			}
		}

		@SuppressWarnings("unused")
		private static void updateHeaders(String filename) {
			FileWriter out;
			try {
				out = new FileWriter(filename);

				CSVPrinter printer = CSVFormat.DEFAULT
						.withHeader("stock", "tradedate", "open", "high", "low", "close", "volume").print(out);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		static Map<String, String> STOCK_MAP = new HashMap<String, String>();

		@SuppressWarnings("unused")
		private static void readCSVFile(String filename) {
			STOCK_MAP.put("20MICRONS", "298100");
			STOCK_MAP.put("21STCENMGM", "73200");
		}
		
}

