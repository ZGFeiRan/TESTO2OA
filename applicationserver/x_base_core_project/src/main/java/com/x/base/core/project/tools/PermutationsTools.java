package com.x.base.core.project.tools;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.x.base.core.project.gson.XGsonBuilder;

public class PermutationsTools {

	public static <T> List<List<T>> combination(int count, List<T> elements) throws Exception {
		if (ListTools.isEmpty(elements)) {
			throw new Exception("combination elements is empty.");
		}
		if (count < 1) {
			throw new Exception("combination count < 1.");
		}
		if (count > elements.size()) {
			throw new Exception("combination count > elements.size().");
		}
		List<List<T>> result = new ArrayList<>();
		combination_expend(result, elements, new ArrayList<T>(), count, 0);
		return result;
	}

	private static <T> void combination_expend(List<List<T>> result, List<T> elements, List<T> prefix, int count,
			int cursor) {
		T t = null;
		List<T> o = null;
		for (int i = cursor; i < elements.size(); i++) {
			t = elements.get(i);
			o = new ArrayList<>(prefix);
			o.add(t);
			if (o.size() == count) {
				result.add(o);
			} else {
				combination_expend(result, elements, o, count, i + 1);
			}
		}
	}

	@SafeVarargs
	public static <T> List<List<T>> groupCombination(List<T>... elements) throws Exception {
		return groupCombination(ListTools.toList(elements));
	}

	public static <T> List<List<T>> groupCombination(List<List<T>> elements) throws Exception {
		if (ListTools.isEmpty(elements)) {
			throw new Exception("groupCombination elements is empty.");
		}
		List<List<T>> result = new ArrayList<>();
		groupCombination_expend(result, elements, new ArrayList<T>(), 0);
		return result;
	}

	private static <T> void groupCombination_expend(List<List<T>> result, List<List<T>> elements, List<T> prefix,
			int cursor) {
		for (T t : elements.get(cursor)) {
			List<T> o = new ArrayList<>(prefix);
			o.add(t);
			if (cursor == elements.size() - 1) {
				result.add(o);
			} else {
				groupCombination_expend(result, elements, o, cursor + 1);
			}
		}
	}

	public static <T> List<List<T>> arrangement(int count, List<T> elements) throws Exception {
		if (ListTools.isEmpty(elements)) {
			throw new Exception("arrangement elements is empty.");
		}
		if (count < 1) {
			throw new Exception("arrangement count < 1.");
		}
		if (count > elements.size()) {
			throw new Exception("arrangement count > elements.size().");
		}
		return arrangement_expand(count, new ArrayList<>(), elements);
	}

	private static <T> List<List<T>> arrangement_expand(int count, List<T> prefix, List<T> elements) {
		List<List<T>> result = new ArrayList<>();
		for (T t : elements) {
			List<T> _n_elements = new ArrayList<>(elements);
			List<T> _n_prefix = new ArrayList<>(prefix);
			_n_elements.remove(t);
			_n_prefix.add(t);
			if (_n_elements.isEmpty() || count == 1) {
				result.add(_n_prefix);
			} else {
				result.addAll(arrangement_expand(count - 1, _n_prefix, _n_elements));
			}
		}
		return result;
	}

	@Test
	public void test_combination() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		System.out.println(combination(1, list));
		System.out.println(combination(2, list));
		System.out.println(combination(3, list));
		System.out.println(combination(4, list));
	}

	@Test
	public void test_arrangement() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		System.out.println(arrangement(1, list));
		System.out.println(arrangement(2, list));
		System.out.println(arrangement(3, list));
		System.out.println(arrangement(4, list));
	}

	@Test
	public void test_groupCombination() throws Exception {
		List<String> a = new ArrayList<>();
		List<String> b = new ArrayList<>();
		List<String> c = new ArrayList<>();
		a.add("a1");
		a.add("a2");
		a.add("a3");
		b.add("b1");
		b.add("b2");
		b.add("b3");
		c.add("c1");
		c.add("c2");
		c.add("c3");
		List<List<String>> result = groupCombination(a, b, c);
		System.out.println(XGsonBuilder.toJson(result));
		System.out.println(XGsonBuilder.toJson(result.size()));
	}

	@Test
	public void test5() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		System.out.println(arrangement(2, list));
		System.out.println(combination(3, list));
	}

}
