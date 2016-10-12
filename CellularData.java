import java.text.DecimalFormat;

/**
 * Cellular Data prints a 2D table
 * containing countries' names as rows and number of subscriptions
 * for each country in an specific year as columns
 * It also calculates the number of subscriptions in each country 
 * within a range of years.
 * @author rigo-perex
 */
public class CellularData {
	private int tempIndex;
	private static final int INVALID_ENTRY = -1;
	private static final int ZERO = 0;
	private int startingYear;
	private int numCountries;
	private int numRows;
	private int numColumns;
	private int [] yearSinceDataAvailable;
	private String [] allCountries;	
	private double [][] cellularSubscriptions;
	/**
	 * Constructor
	 *Initialize countryNameArray and cellDataArray
	 */
	public CellularData(int rows, int columns, int startingYear) {
		this.tempIndex = 0;
		this.startingYear = startingYear; // year X
		this.numCountries = 0;
		this.numRows = rows;//  = 254
		this.numColumns = columns; //  54
		this.yearSinceDataAvailable = new int[columns+1];
		this.allCountries = new String[rows];
		this.cellularSubscriptions = new double [rows][columns];
	}
	/**
	 * Takes country's names and cell phone subscriptions from within 
	 * a range of years.
	 * Populates an array of Strings containing countries.
	 * Populates a 2D array containing doubles.
	 * @param country
	 * @param countryPartial
	 */
	public void addCountry(String country, double[] cellSubs) {
		if(numCountries < numRows){
			allCountries[numCountries] = country;
			for(int index = ZERO; index < numColumns; index++){
				cellularSubscriptions[numCountries][index] = cellSubs[index];
			}
			numCountries++;
		}
	}
	/**
	 * Takes
	 * @param country name.
	 * Iterates through the Array of countries.
	 * Searches for equality of two values.
	 * @return True if a match if found,
	 * Or false otherwise.
	 * Gets the index of the matched value.
	 */
	public boolean findCountry(String country){
		boolean notFound = true;
		for (int i = 0; i < cellularSubscriptions.length; i++){
			if (allCountries[i] == country) {
				this.tempIndex = i;
				return true;
			}else{
				notFound = false;
			}
		}
		System.out.println("COULD NOT FOUND SUCH COUNTRY: " + country.toUpperCase() );
		return notFound;
	}
	/**
	 * Checks for true/false, iterates through cellData and find a matching index.
	 * Adds up number of cellular subscriptions throughout the matching row.
	 * @param country name.
	 * @param startingYr user's starting year.
	 * @param endingYear user's limit year.
	 * @return sum of subscriptions.
	 */
	public double getSumOfSubscriptions(String country, int startingYr, int endingYear){
		double sumOfSubscriptions = 0.0;
		int sumStartingPoint = (startingYr-startingYear);  
		int sumEndingPoint = (endingYear-startingYear);
		if(findCountry(country)){
			for(int i = sumStartingPoint; i < sumEndingPoint; i++){ //i < 52
				sumOfSubscriptions += cellularSubscriptions[tempIndex][i] ;	
			}
		}
		return sumOfSubscriptions;

	}
	/**
	 * Tests that user data input meets the requirements.
	 * @param country is the name of the country the user wants to find.
	 * @param startingYr is the starting year the user input.
	 * @param endingYear is the year limit user enter.
	 * @return the sum of all subscriptions for the matching country.
	 */
	public double getNumSubscriptionsInCountryForPeriod(String country, int startingYr, int endingYear) {
		String warningMessg = " ** VALID RANGE IS FROM " + startingYear +" TO " + (startingYear+numColumns) +" **\n" + " ** STARTING YEAR " + "("+ startingYr + ") IS NOT WITHIN THE VALID RANGE OF YEARS **";
		String warningMessg2 = " ** VALID RANGE IS FROM " + startingYear +" TO " + (startingYear+numColumns) +" **\n" + " ** ENDING YEAR " + "("+ endingYear + ") SHOULD NOT BE GRATER THAN " + (startingYear+numColumns)+  "**";
		String warningMessg3 = " ** VALID RANGE IS FROM " + startingYear +" TO " + (startingYear+numColumns) +" **\n" + " ** ENDING YEAR " + "("+ endingYear + ") SHOULD NOT BE LESS THAN " +  startingYear +  "  **";
		try{
			if (country == null) {
				return ZERO;
			} else if(!findCountry(country)) { 
				return INVALID_ENTRY;
			} else if(startingYr < startingYear){
				System.out.println(warningMessg);
				return INVALID_ENTRY;// checked
			}else if(startingYr > (startingYear+numColumns) ){
				System.out.println(warningMessg); // checked
				return INVALID_ENTRY;
			} else if(endingYear < this.startingYear){
				System.out.println(warningMessg3) ;
				return INVALID_ENTRY;	// checked
			}else if( endingYear > (startingYear+numColumns)){
				System.out.println(warningMessg2) ;
				return INVALID_ENTRY;  // checked
			} else if( endingYear < startingYr ){
				System.out.println("** ENDING YEAR (" +endingYear +") SHOULD NOT BE LESS OR EQUAL TO STARTING YEAR (" + startingYr+")  **" ) ;
				return INVALID_ENTRY;
			}else 
				return getSumOfSubscriptions(country, startingYr, endingYear);
		} catch (ArrayIndexOutOfBoundsException ex){
			System.out.println(ex);
			return INVALID_ENTRY;
		}
	}
	/**
	 * Creates an String of years from when CELL DATA is available. 
	 * @return years as a string
	 */
	public String getYears() {
		String yearsRange = "";

		for(int index = ZERO; index < yearSinceDataAvailable.length; index++){
			yearSinceDataAvailable[index] = startingYear + index; 

		}
		for(int i = ZERO; i < yearSinceDataAvailable.length ; i++){
			yearsRange += yearSinceDataAvailable[i] + "\t";
		}
		return yearsRange;
	}
	/**
	 * It iterates through allCountries and cellularSubscriptions Arrays.
	 * Sets up a 2D table. One row is a country and 
	 * one column is the number of cell-phone subscriptions for a specific year. 
	 * Returns modified table as String. 
	 */
	@Override
	public String toString() {
		DecimalFormat dcFormat = new DecimalFormat("##0.00");
		String temp = "";
		for(int row = ZERO; row < (allCountries.length + INVALID_ENTRY); row++){
			temp += allCountries[row] + "  \t";
			for(int column = ZERO; column < cellularSubscriptions[row].length; column++){
				temp += dcFormat.format(cellularSubscriptions[row][column]) + "\t";
			}
			temp += "\n\n";
		}
		return String.format("CNTY\\YEAR  >    %s\n^      \n%s", getYears(), temp);
	}

}








