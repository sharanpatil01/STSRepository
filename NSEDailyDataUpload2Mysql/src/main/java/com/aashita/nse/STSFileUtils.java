package com.aashita.nse;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class STSFileUtils {

	// java -cp nsefileupload-japp-1.0-v1.jar com.aashita.nse.STSFileUtils
	// C:\\workspaces\\bhavcopy\\2020-11-09-NSE-EQ.txt
	static String[] HEADERS = { "stock", "tradedate", "open", "high", "low", "close", "volume" };

	enum StockFileHeaders {
		stock, tradedate, open, high, low, close, volume
	}

	public static void main(String[] args) throws IOException {
		STSFileUtils futil = new STSFileUtils();
		String filename = "C:\\workspaces\\bhavcopy\\2020-11-14-NSE-EQ.txt";

		if (args.length > 0)
			filename = args[0];

		if (args.length != 1) {
			System.err.println("filename args missing : enter filename & path like ["+ filename +"]");
			return;	
		}
		System.out.println("filename : " + filename);

		if (!isFileExists(filename)) {
			System.err.println("1.File Not exists [" + filename + "], please verify  ");
			return;
		}

		String outfilename = filename.replaceAll("bhavcopy", "bhavcopyarchive");
		outfilename.replaceAll(".txt", "-hdr.txt");
		
		// STSFileUtils.updateHeaders(filename);

		// readFiles(filename);

		// readCSVFile(filename);

		// futil.givenCSVFile_whenRead_thenContentsAsExpected(filename);

		writeFiles(filename, outfilename);

		System.out.println("File Updated with Header!!!!!");
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

	private static void readFiles(String filename) throws IOException {
		Reader in = new FileReader(filename);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
		for (CSVRecord record : records) {
			String columnOne = record.get(0);
			String columnTwo = record.get(1);
			System.out.println(columnOne + ", " + columnTwo);
		}
	}

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

	private static void readCSVFile(String filename) {
		STOCK_MAP.put("20MICRONS", "298100");
		STOCK_MAP.put("21STCENMGM", "73200");
	}

}
