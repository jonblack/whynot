package crawl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;

import model.Databank;

public class LineCrawler extends AbstractCrawler {
	/**
	 * Flat file crawler
	 * @param db
	 */
	public LineCrawler(Databank db) {
		super(db);
	}

	@Override
	public int addEntriesIn(String filepath) throws IOException {
		long lastmodified = new File(filepath).lastModified();
		BufferedReader bf = new BufferedReader(new FileReader(filepath));
		for (String line = ""; (line = bf.readLine()) != null;) {
			Matcher m = pattern.matcher(line);
			if (m.matches())
				new model.File(database, m.group(1), filepath, lastmodified);
		}
		bf.close();
		return database.getFiles().size();
	}
}
