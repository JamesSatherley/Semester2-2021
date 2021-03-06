package loggingTutorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

/**
 * The purpose of this class is to read and merge financial transactions, and print a summary:
 * - total amount 
 * - highest/lowest amount
 * - number of transactions 
 * @author jens dietrich
 */

public class MergeTransactions {

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	private static NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(Locale.getDefault());
	private static final Logger loggerFILE = LogManager.getLogger("FILE");
	private static final Logger loggerTRANSACTIONS = LogManager.getLogger("TRANSACTIONS");

	public static void main(String[] args) {
		PropertyConfigurator.configure("CONFIG.config");
		List<Purchase> transactions = new ArrayList<Purchase>();
		
		// read data from 4 files
		readData("src/resources/transactions1.csv",transactions);
		readData("src/resources/transactions2.csv",transactions);
		readData("src/resources/transactions3.csv",transactions);
		readData("src/resources/transactions4.csv",transactions);

		// print some info for the user
		loggerTRANSACTIONS.info("\n\n\n");
		loggerTRANSACTIONS.info("" + transactions.size() + " transactions imported");
		loggerTRANSACTIONS.info("total value: " + CURRENCY_FORMAT.format(computeTotalValue(transactions)));
		loggerTRANSACTIONS.info("max value: " + CURRENCY_FORMAT.format(computeMaxValue(transactions)));
	}
	
	private static double computeTotalValue(List<Purchase> transactions) {
		double v = 0.0;
		for (Purchase p:transactions) {
			v = v + p.getAmount();
		}
		return v;
	}
	
	private static double computeMaxValue(List<Purchase> transactions) {
		double v = 0.0;
		for (Purchase p:transactions) {
			v = Math.max(v,p.getAmount());
		}
		return v;
	}

	// read transactions from a file, and add them to a list
	private static void readData(String fileName, List<Purchase> transactions) {
		
		File file = new File(fileName);
		String line = null;
		// print info for user
		loggerTRANSACTIONS.info("import data from " + fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine())!=null) {
				String[] values = line.split(",");
				Purchase purchase = new Purchase(
						values[0],
						Double.parseDouble(values[1]),
						DATE_FORMAT.parse(values[2])
				);
				transactions.add(purchase);
				// this is for debugging only
				loggerTRANSACTIONS.debug("imported transaction " + purchase);
			} 
		}
		catch (FileNotFoundException x) {
			// print warning
			loggerFILE.warn("file " + fileName + " does not exist - skip");
		}
		catch (IOException x) {
			// print error message and details
			loggerFILE.error("problem reading file " + fileName);
		}
		// happens if date parsing fails
		catch (ParseException x) { 
			// print error message and details
			loggerFILE.error("cannot parse date from string - please check whether syntax is correct: " + line);
		}
		// happens if double parsing fails
		catch (NumberFormatException x) {
			// print error message and details
			loggerFILE.error("cannot parse double from string - please check whether syntax is correct: " + line);
		}
		catch (Exception x) {
			// any other exception 
			// print error message and details
			loggerFILE.error("exception reading data from file " + fileName + ", line: " + line);
		}
		finally {
			try {
				if (reader!=null) {
					reader.close();
				}
			} catch (IOException e) {
				// print error message and details
				loggerFILE.error("cannot close reader used to access " + fileName);
			}
		}
	}
}