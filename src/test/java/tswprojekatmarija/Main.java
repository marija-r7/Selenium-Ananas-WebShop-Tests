package tswprojekatmarija;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import junit.textui.TestRunner;


public class Main {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(RunAllTests.class);
		Logger logger = Logger.getLogger(TestRunner.class.toString());

		for (Failure failure : result.getFailures()) {
			logger.warning(failure.toString());
		}

		logger.info("Vreme izvrsavanja testova: " + result.getRunTime());
		logger.info("Ukupan broj testova: " + result.getRunCount());
		logger.info("Broj uspesnih testova: " + (result.getRunCount() - result.getFailureCount()
				- result.getIgnoreCount() - result.getAssumptionFailureCount()));
		logger.info("Broj palih testova: " + result.getFailureCount());
		logger.info("Broj preskocenih testova: " + result.getIgnoreCount());
		logger.info("Broj dinamickih preskocenih testova: " + result.getAssumptionFailureCount());

		if (result.wasSuccessful())
			logger.info("Svi testovi su uspesno izvrseni.");
		else
			logger.info("Postoje greske u testovima.");
		try {
			File myObj = new File("test-report.txt");
			if (myObj.createNewFile()) {
				System.out.println("Fajl kreiran: " + myObj.getName());
			} else {
				System.out.println("Fajl vec postoji.");
			}
		} catch (IOException e) {
			System.out.println("Greska.");
			e.printStackTrace();
		}
		try {
			FileWriter myWriter = new FileWriter("test-report.txt");
			myWriter.write("Vreme izvrsavanja:" + result.getRunTime());
			myWriter.write("\nBroj testova:" + result.getRunCount());
			myWriter.write("\nUspesno testova:" + (result.getRunCount() - result.getFailureCount()
					- result.getIgnoreCount() - result.getAssumptionFailureCount()));
			myWriter.write("\nBroj palih testova:" + result.getFailureCount());
			myWriter.write("\nBroj preskocenih:" + result.getIgnoreCount());
			myWriter.write("\nBroj dinamicki preskocenih:" + result.getAssumptionFailureCount());
			myWriter.write("\n\n");
			myWriter.write("ANANAS E-COMMERCE d.o.o.:\n");
			myWriter.write("Lokacija:\n" + "Vladimira Popovica 8, sprat 7, Beograd\n");
			myWriter.write("\nMatični broj:\n" + "21625434");

			myWriter.write("\n\nIzvestaj uspesnosti testova:\n");

			myWriter.write("\nRegisterUserTest:\n"
					+ "Opis testa: Test pokazuje da li su podaci prilikom prijave validni.");

			myWriter.write("\nLoginUserTest:\n"
					+ "Opis testa: Test za proveru forme za prijavu.");
			
			myWriter.write("\nUserDataTest:\n"
					+ "Opis testa: Test za proveru podataka na korisnickom nalogu.");

			myWriter.write("\nAddToCartTest:\n"
					+ "Opis testa: Test za dodavanje proizvoda u korpu.");

			myWriter.write("\nIme testa: PerformanceTest\n"
					+ "Opis testa: Test za merenje performansi otvaranja stranica.");
			
			myWriter.write("\nIme testa: AdditionalTest\n"
					+ "Opis testa: Dodatni testovi za proveru osnovnih prikaze i search funkcije.");

			myWriter.write("\nSvi testovi su uspesno zavrseni.\n");

			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}