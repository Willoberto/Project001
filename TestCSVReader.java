import java.text.DecimalFormat;
/**
 *  Tests the CSVReader class, which reads input from a CSV file. Uses
 *  the attributes stored in CSVReader object to fill the CellularData class.
 *
 * @author Foothill College, [Rigoberto Perez]
 *
 * REMINDER: Include text cases in addition to those provided.
 *           See suggestions at the end of the main() method.
 */
public class TestCSVReader{
	public static final int ZERO = 0;
	/**
	 * Takes
	 * @param totalSubscriptions for each country
	 * @param strYr is the starting year the user input.
	 * @param endYr is the ending year the user input.
	 * @param country name.
	 * Prints message if based on the sign of totalSubscriptions or
	 * a message if the value of country is null. 
	 */
	public static void printResult(double totalSubscriptions, int strYr, int endYr, String country){
		DecimalFormat f = new DecimalFormat("##0.00");
		if (totalSubscriptions < ZERO){
			System.out.println(" ** HENCE, CANNOT COMPLETE SUM OF CELLULAR SUBSCRIPTIONS FOR " + country.toUpperCase() + "  **\n");
		}
		else if (country == null){
			System.out.println(" ** ("+country + ") IS AN IVALID ENTRY. PLEASE ENTER A VALID COUNTRY NAME **\n");
		}else{
			System.out.println(country.toUpperCase() + " ("+ strYr + " to "+ endYr+") TOTAL CELLULAR SUBSCRIPTIONS: " + f.format(totalSubscriptions)+"\n");
		}
	}
	/**
	 * Uses a CSVReader to parse a CSV file.
	 * Adds each parsed line to an instance of the CellularData class.
	 * Tests valid user inputs.
	 */
	public static void main(String[] args){	

		final String FILENAME = "resources/cellular.csv";// Directory path for Mac OS X
		CSVReader parser = new CSVReader(FILENAME);// CSV Constructor
		String [] countryNames = parser.getCountryNames();
		int [] yearLabels = parser.getYearLabels();
		double [][] parsedTable = parser.getParsedTable();


			int numRows = parsedTable.length;
			int numColumns = parser.getNumberOfYears();
			int startingYear = yearLabels[0];
			CellularData datatable = new CellularData(numRows, numColumns, startingYear);// CellularData instantiation and constructor

			for (int countryIndex = 0; countryIndex < countryNames.length; countryIndex++){
				double [] countryData = parsedTable[countryIndex];
				datatable.addCountry(countryNames[countryIndex], countryData);					
			}

			for (int countryIndex = 0; countryIndex < countryNames.length; countryIndex++){
				double [] countryData = parsedTable[countryIndex];
				datatable.addCountry(countryNames[countryIndex], countryData);					
			}

			double totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod( countryNames[237], 1960, 2014);
		printResult(totalSubscriptions, 1960, 2014, countryNames[237]);		
		totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[237], 1983,1989);
		printResult(totalSubscriptions,1983,1989, countryNames[237]);
		totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[148],1960,2014);
		printResult(totalSubscriptions, 1960, 2014, countryNames[148]);
		totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[100],1960, 2014);
		printResult(totalSubscriptions,1960, 2014, countryNames[100]);
		totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[200],1960,2014);
		printResult(totalSubscriptions, 1960, 2014, countryNames[200]);
		totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[10],1959, 2014);
		printResult(totalSubscriptions,1959,2014, countryNames[10]);
		totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[20],1960, 2015);////
		printResult(totalSubscriptions,1959, 2015, countryNames[20]);
		totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[30], 2010, 2009);
		printResult(totalSubscriptions,2010, 2009, countryNames[30]);
		totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[40], 2015, 2014);
		printResult(totalSubscriptions,2015, 2014, countryNames[40]);
		totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod(countryNames[252], 2010, 2009);
		printResult(totalSubscriptions,2010, 2009, countryNames[252]);
		totalSubscriptions = datatable.getNumSubscriptionsInCountryForPeriod("Berlin", 2010, 2009);
		printResult(totalSubscriptions,2010, 2009, "Berlin");
			// TODO: For full credit, include test cases in addition to those provided.
			//
			// TODO: Also, make sure to test for other invalid requests for range of years.
			System.out.println("\nDone with TestCSVReader.\n");System.out.println(datatable);

		}
	}
























/*System.out.println(" \n\n***********"+ countryNames.length +" ***********\n\n");

for(int i = 0; i < yearLabels.length; i++){
	System.out.print(yearLabels[i] + "  ");
}
System.out.println("\n");

for(int i = 1; i < countryNames.length; i++){
	System.out.println(countryNames[i] + "  ");
}

for(int i = 0; i < parsedTable.length; i++){
	for(int j = 0; j < parsedTable[i].length; j++ )
		System.out.print(parsedTable[i][j] +" ");
	System.out.println();

}*/