package fi.tr.f2m;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Converter {

	static String inputFile = "example_1.csv";

	static SimpleDateFormat inputDateFormatter = new SimpleDateFormat("MM/dd/yyyy");
	static SimpleDateFormat outputDateFormatter = new SimpleDateFormat("yyyy.MM.dd");
	static SimpleDateFormat inputTimeFormatter = new SimpleDateFormat("HH.mm.ss");
	static SimpleDateFormat outputTimeFormatter = new SimpleDateFormat("HH:mm");
	static DecimalFormat priceFormatter = new DecimalFormat("#.00");
	static int index = 0;

	public static void main(String[] args) throws Exception {

		System.out.println("About to start. Input file: " + inputFile);

		List<List<String>> rows = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			long counter = 1;
			while ((line = br.readLine()) != null) {
				if (counter > 1) {
					String[] values = line.split(",");
					rows.add(Arrays.asList(values));
				}
				counter++;
			}
		}

		List<String> outputRows = new ArrayList<String>();
		for (List<String> row : rows) {
			if (row.get(0).length() > 0) {
				System.out.println("in:  " + row);

				String outputRow = "";
				index = 0;

				Date inputDate = inputDateFormatter.parse(row.get(index));
				outputRow += outputDateFormatter.format(inputDate);
				index++;

				Date inputTime = inputTimeFormatter.parse(row.get(index));
				outputRow += "," + outputTimeFormatter.format(inputTime);
				index++;

				outputRow += "," + temp(row);
				outputRow += "," + temp(row);
				outputRow += "," + temp(row);
				outputRow += "," + temp(row);
				outputRow += "," + row.get(index);

				outputRows.add(outputRow);
				System.out.println("out: " + outputRow);
			}
		}

		Path out = Paths.get("converted_" + inputFile);
		Files.write(out, outputRows, Charset.defaultCharset());
		System.out.println("Done. Output file written: " + out);
	}

	private static String temp(List<String> row) {
		Double open1 = Double.parseDouble(row.get(index));
		index++;
		Double open2 = 0.0;
		if (index < row.size() - 1) {
			open2 = Double.parseDouble(row.get(index));
			if (open2 > 1000) {
				open2 = 0.0;
			} else {
				open2 = open2 / 10f;
				index++;
			}
		} else {
			// This is special case, when the last price cell has no cell
			// for the decimal price part and index will point to the last
			// volumne (tics) cell. Need to skip then.
			//
			open2 = 0.0;
		}
		return priceFormatter.format(open1 + open2);
	}
}
