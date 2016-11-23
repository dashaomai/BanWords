package com.clevercells.benchmarks;

import com.clevercells.filters.IBanWordsFilter;
import com.clevercells.filters.LEBanWordsFilter;
import com.clevercells.filters.THMBanWordsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试器
 * Created by Bob Jiang on 2016/11/6.
 */
public class Benchmarker {
	private static List<IBanWordsFilter> filters = new ArrayList<>();

	public static void benchmark(final String wordsPath, final String message) {

		filters.add(new THMBanWordsFilter(wordsPath));
		filters.add(new LEBanWordsFilter(wordsPath));

		for (final IBanWordsFilter filter : filters) {
			final long start = System.nanoTime();

			boolean has = true;

			for (int i = 0, m = 50; i<m; i++) {
				has &= filter.hasBanWords(message);
			}

			final long end = System.nanoTime();

			System.out.println(String.format("%s 测试结果为 %b，消耗了 %d 纳秒", filter.getClass().getName(), has, end - start));
		}
	}
}
