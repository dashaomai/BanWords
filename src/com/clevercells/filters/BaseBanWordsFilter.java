package com.clevercells.filters;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Bob Jiang on 2016/11/6.
 */
public abstract class BaseBanWordsFilter implements IBanWordsFilter {
	public BaseBanWordsFilter(final String wordsPath) {
		init();

		try {
			final FileReader fr = new FileReader(wordsPath);
			final BufferedReader reader = new BufferedReader(fr);

			for (String line; (line = reader.readLine()) != null;) {
				processLine(line);
			}

			reader.close();
			fr.close();

		} catch (final Exception ex) {
			System.out.println("打开文件 " + wordsPath + " 时出错：" + ex.getMessage());
		}
	}

	abstract protected void init();

	abstract protected void processLine(final String line);
}
