import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
/**
 * Reads a file in the form of CSV.
 * Each member of the class represents a country
 * along with number of cellular subscriptions/100 people
 * over the period of years 1960 to 1914.
 * @author rigo-perex
 *
 */
public class CSVReader {
	private File cell_Csv_File;
	private int indexOfCountry;
	private int indexOfCellData;
	private Scanner readCsvFile; 
	private String []countryName;
	private double [][] cellData;
	private int [] yearLabel;
	/**
	 * Takes
	 * @param FILENAME File name in CSV form 
	 * Initialize country[], cellData[][], and yearLable Arrays.
	 * Initialize indexOfCountry, indexOfCellData and cell_Csv_File primitive values.
	 */
	public CSVReader(String FILENAME) {
		File csvFile = new File(FILENAME);
		this.cell_Csv_File = csvFile;
		this.indexOfCountry = 0;
		this.indexOfCellData = 0;
		setRowsAndColumns();
		if(indexOfCountry < 0 || indexOfCellData < 0 ){
			System.out.println("UNITITIALIZED ARRAYS.");
			System.out.println("CHECK FOR FILE DIRECTORY AND SPELLING FOR: " + FILENAME);
			System.out.println("**** TERMINATING APPLICATION ****");
			System.exit(0);
		}else{
			this.countryName = new String[indexOfCountry]; // 253
			this.cellData = new double[indexOfCountry][indexOfCellData]; // [253][54]
			this.setArrays();
		}
	}
	/**
	 * Iterates through CSV file.
	 * Splits data into tokens.
	 * Gets values for primitives indexOfCellData and indexOfCountry.
	 */
	public void setRowsAndColumns(){
		int theWhileCounter = 0;
		try{
			readCsvFile = new Scanner(cell_Csv_File);
			while(readCsvFile.hasNextLine()){
				String countryData = readCsvFile.nextLine();
				String [] tokens = countryData.split(",");
				indexOfCellData = tokens.length; // number of columns (tokens) = 54
				indexOfCountry =	theWhileCounter++;
			}
			readCsvFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File " + cell_Csv_File + " not found!");
		}
	}
	/**
	 * Iterates through CSV file.
	 * Splits data into tokens.
	 * Populates String array with countries.
	 * Populates cellData with number of subscriptions.
	 * 
	 */
	public void setArrays(){
		int theWhileLoopCounter = 0;
		try{
			this.readCsvFile = new Scanner(cell_Csv_File);
			while(readCsvFile.hasNextLine()){
				String lineX = readCsvFile.nextLine();
				String[] tokens = lineX.split(",");
				if (tokens.length > 2) {
					this.countryName[theWhileLoopCounter] = tokens[0]; // tokens[0] contains countries.
					for (int i = 1; i < tokens.length; i++){
						//cellSubscriptions starts copying at[0][0] = values[2]
						this.cellData[theWhileLoopCounter][i-1] =  Double.parseDouble(tokens[i]);
					}
					theWhileLoopCounter++;
				}
			}
			readCsvFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File " + cell_Csv_File + " not found!");
		}
	}
	/**
	 * Copies countryName[] Array values.
	 * @return String all countries.
	 */

	public String[] getCountryNames() {
		String [] countryNames = new String[indexOfCountry-1];
		for(int i = 1; i < countryNames.length; i++){
			countryNames[i-1] = countryName[i].toUpperCase(); // starts at countryNames[0] = countryName[1].toUpperCase().
		}
		return countryNames;
	}
	/**
	 * Copies cellData[][] Array values.
	 * @return double values.
	 */
	public double[][] getParsedTable() {
		double [][] data = new double[indexOfCountry][indexOfCellData];
		for(int i = 1; i < data.length; i++){
			for(int h = 1; h < indexOfCellData; h++ ){
				data[i-1][h-1] =  cellData[i][h]; //starts at data[0][0] = cellData[0][1].
			}
		}
		return data;
	}

	/**
	 * Populates int yearLabel[] Array.
	 * @return return int years.
	 */
	public int[] getYearLabels(){
		this.yearLabel = new int[indexOfCellData+1]; //  indexOfCellData = 54.
		for(int i = 0; i < yearLabel.length; i++){
			yearLabel[i] = (int) (cellData[0][0]) + i; // First year to compy cellData[0][0] = 1960.  for first year i = 1: 1960 + (1-1) = 1960
		}

		return yearLabel;
	}
	/**
	 * @return an int values, which is the number of years found
	 * in CSV file.
	 */
	public int getNumberOfYears() {
		return indexOfCellData;
	}
	/**
	 * Iterates through countryName and cellData Arrays.
	 * Sets up a 2D table. One row is a country and 
	 * one column is the number of cell-phone subscriptions for a specific year. 
	 * Assigns year label to the first row.
	 * Returns  a two dimensional data table. 
	 */
	@Override
	public String toString() {
		DecimalFormat dcFormat = new DecimalFormat("##0.00");
		String temp = "";
		String temp2 = "";
		String [] temp1 = getCountryNames();
		int [] temp3 = getYearLabels();
		double [][] theData = getParsedTable();

		for(int index = 0; index < temp3.length; index++ ){
			temp2 += temp3[index] +"\t";
		}
		for(int i = 0; i < theData.length-2; i++){
			temp += temp1[i] +"    \t";
			for(int h = 0; h < theData[0].length ; h++ ){
				temp += dcFormat.format(theData[i][h]) +"\t";
			}
			temp += "\n\n";
		}
		return String.format("CO  / YEAR > \t%s\n^\n%s",temp2 ,temp);
	}
}
