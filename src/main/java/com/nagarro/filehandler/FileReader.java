package com.nagarro.filehandler;

import com.nagarro.models.TShirtModel;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * 
 * @author yash02
 *
 * {@summary This is the FileReader Class which handles the thread,
 * update the data in database and search the data in database}
 */

public class FileReader extends Thread {

	private static final String FOLDERNAME = "csvfiles";
	private final AtomicBoolean running;
	
	private List<String> filesList;
	private ProductView productView;

	public FileReader() {
		productView = new ProductView();
		running = new AtomicBoolean(true);
	}

	public void stopThread() {
		running.set(false);
	}

	@Override
	public void run() {
		while (running.get()) {
			try {
//				filesList = getFilesList(FOLDERNAME);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("Thread was interrupted, Failed to complete operation");
			}
		}
	}

	public void readFile(String color, String size, String gender, int choice) {

		/**
		 * updating the database
		 */
//		updateDatabase();

		String order;
		if (choice == 1) {
			order = "t.price";
		} else if (choice == 2) {
			order = "t.rating";
		} else {
			order = "t.price, t.rating";
		}

		SessionFactory factory = new Configuration().configure().addAnnotatedClass(TShirtModel.class)
				.buildSessionFactory();

		Session session = factory.openSession();
		session.beginTransaction();

		String hql = "from TShirtModel t WHERE t.color = :color and t.gender = :gender and t.size = :size order by "
				+ order;

		Query query = session.createQuery(hql);
		query.setParameter("color", color);
		query.setParameter("gender", gender);
		query.setParameter("size", size);

		List<TShirtModel> tshirtsList = query.list();

		session.getTransaction().commit();
		session.close();

		productView.print(tshirtsList);
	}

	private void updateDatabase() {
		
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(TShirtModel.class)
				.buildSessionFactory();

		Session session = factory.openSession();
		session.beginTransaction();

		for (String i : filesList) {

			try {
				java.io.FileReader filereader = new java.io.FileReader(FOLDERNAME + "\\" + i);

				CSVParser parser = new CSVParserBuilder().withSeparator('|').build();

				CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(parser).withSkipLines(1).build();

				List<String[]> allData = csvReader.readAll();

				for (String[] row : allData) {
					TShirtModel tshirt = new TShirtModel();
					tshirt.setId(row[0]);
					tshirt.setName(row[1]);
					tshirt.setColor(row[2]);
					tshirt.setGender(row[3]);
					tshirt.setSize(row[4]);
					tshirt.setPrice(Double.parseDouble(row[5]));
					tshirt.setRating(Double.parseDouble(row[6]));
					tshirt.setAvailable(row[7]);
					session.merge(tshirt);
				}

			} catch (CsvException | IOException e) {
				System.out.println("Cannot read the csv file");
			}
		}

		session.getTransaction().commit();
		session.close();
	}

	private List<String> getFilesList(String folderName) {

		List<String> files = new ArrayList<>();
		File folder = new File(folderName);
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.getName().contains(".csv")) {
				files.add(fileEntry.getName());
			}
		}
		return files;
	}
}
