package com.clevercells.filters;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于 Loop Equals 进行无优化传统比较的屏蔽词过滤
 * @author Bob Jiang
 *
 */
public class LEBanWordsFilter extends BaseBanWordsFilter {

	private List<String> banWordsList;
	
	public LEBanWordsFilter(final String wordsPath) {
		super(wordsPath);
	}

	@Override
	protected void init() {
		banWordsList = new ArrayList<>();
	}

	@Override
	protected void processLine(final String line) {
		final String l = line.trim();
		if (l.length() > 0) {
			final int pos = l.indexOf("\n");
			if (pos > 0) {
				banWordsList.add(l.substring(0, pos));
			} else {
				banWordsList.add(l);
			}
		}
	}

	@Override
	public boolean hasBanWords(String message) {
		for (final String banWord : banWordsList) {
			if (message.contains(banWord)) {
				System.out.println("命中 " + banWord);
				return true;
			}
		}

		return false;
	}

}
