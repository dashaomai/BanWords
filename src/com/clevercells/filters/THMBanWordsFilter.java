package com.clevercells.filters;

/**
 * 基于 Treed HashMap 的屏蔽词过滤
 * Created by Bob Jiang on 2016/11/6.
 */
public class THMBanWordsFilter extends BaseBanWordsFilter {

	private TreedHashMapNode banWordsRoot;

	public THMBanWordsFilter(final String wordsPath) {
		super(wordsPath);
	}

	@Override
	public boolean hasBanWords(String message) {
		boolean inEnglishWord = false;
		boolean isBeginOfEnglishWord = false;

		for (int i = 0, m = message.length(); i<m; i++) {
			final char chr = message.charAt(i);
			final boolean nowIsEng = TreedHashMapNode.isEnglishChar(chr);

			if (!inEnglishWord && nowIsEng) {
				inEnglishWord = true;
				isBeginOfEnglishWord = true;
			} else if (inEnglishWord && !nowIsEng) {
				inEnglishWord = false;
				isBeginOfEnglishWord = false;
			} else if (inEnglishWord && nowIsEng) {
				continue;
			} else {
				isBeginOfEnglishWord = false;
			}

			if (banWordsRoot.IsMatch(message.substring(i), isBeginOfEnglishWord)) {
				return true;
			}
		}

		return false;
	}

	@Override
	protected void init() {
		banWordsRoot = new TreedHashMapNode();
	}

	@Override
	protected void processLine(String line) {
		line = line.trim();

		if (line.length() > 0) {
			banWordsRoot.Add(line);

			if (banWordsRoot.isEnd) {
				System.out.println(line);
			}
		}
	}
}
