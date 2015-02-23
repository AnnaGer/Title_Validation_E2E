package core;

//BEGIN
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Selenium {
	public String[][] a2d() throws IOException {

		String csvFile = "./src/main/resources/Title_Validation.csv";
		BufferedReader br = null;
		String line = null;
		String[] column = null;
		int lines = 0;
		String line1 = null;
		int columns = 0;
		String SplitBy = "~";
		String text_case_id = null;
		String url = null;
		String title_expected = null;
		
		// COUNTING LINES AND COLUMNS
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
		      lines++;
		      column = line.split(SplitBy);
		      columns = column.length;
		} 
		br.close();
		
		String s2d [][] = new String [lines][columns];
		br = new BufferedReader(new FileReader(csvFile));
		WebDriver driver = new FirefoxDriver();// Version 1.4 :: Firefox
		int i = 0;
		while ((line = br.readLine()) != null){
			line1 = line.replace("\"","");
			
			String[] csv = line1.split(SplitBy); 
				text_case_id = csv[0];
				url = csv[1];
				title_expected = csv[2];

			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String title_actual = driver.getTitle();
			
			s2d[i][0] = text_case_id;
			s2d[i][1] = title_expected;
			s2d[i][2] = title_actual;
			
			i++;
			}

			driver.quit();
			br.close();
			return s2d;
		}

	public static void main(String[] args) throws IOException {
		core.Selenium selenium = new core.Selenium();
		selenium.a2d();
	}
}
// END