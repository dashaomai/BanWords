package com.clevercells.filters;

/**
 * 屏蔽词的过滤接口
 * @author Bob Jiang
 *
 */
public interface IBanWordsFilter {
	boolean hasBanWords(final String message);
}
