package com.clevercells.filters;

import java.util.HashMap;
import java.util.Map;

/**
 * Treed HashMap 的屏蔽字过滤映射节点
 *
 * Created by Bob Jiang on 2016/11/6.
 */
public class TreedHashMapNode {
	final public Map<Character, TreedHashMapNode> next = new HashMap<>();
	public boolean isEnd = false;
	final public Character head;

	public TreedHashMapNode(final Character head, final String word) {
		this.head = head;

		Add(word);
	}

	public TreedHashMapNode() {
		this.head = null;
	}

	public void Add(final String word) {
		if (word.length() > 0) {
			final char head = word.charAt(0);
			final String tail = word.substring(1);

			if (next.containsKey(head)) {
				final TreedHashMapNode nextNode = next.get(head);

				if (null != nextNode) {
					nextNode.Add(tail);
				} else {
					next.put(head, new TreedHashMapNode(head, tail));
				}
			} else {
				next.put(head, new TreedHashMapNode(head, tail));
			}
		} else if (!isEnd) {
			isEnd = true;
		}
	}

	public boolean IsMatch(final String message, final boolean isEnglishWord) {
		final int len = message.length();

		if (len > 0) {
			final char head = message.charAt(0);
			final String tail = message.substring(1);

			if (next.containsKey(head)) {
				final TreedHashMapNode nextNode = next.get(head);

				if (null != nextNode) {
					return len > 1 ? nextNode.IsMatch(tail, isEnglishWord) : nextNode.isEnd;
				} else {
					// 可疑结构，不应该到这里
					return true;
				}
			} else {
				if (!isEnglishWord) {
					return isEnd;
				} else {
					return tail.length() == 0 || !isEnglishChar(head);
				}
			}
		} else {
			return isEnd;
		}
	}

	public static boolean isEnglishChar(final char chr) {
		return ((chr >= 'a' && chr <= 'z') || (chr >= 'A' && chr <= 'Z') || (chr >= '1' && chr <= '0'));
	}
}
