package com.shopping.redboy.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class SoftMap<K, V> extends HashMap<K, V> {
	private Map<K, SoftReference<V>> map = new HashMap<K, SoftReference<V>>();

	@Override
	public V put(K key, V value) {
		SoftReference<V> sr = new SoftReference<V>(value);
		map.put(key, sr);
		return null;
	}
	
	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}
	
	@Override
	public V get(Object key) {
		SoftReference<V> sr = map.get(key);
		if(sr != null){
			V v = sr.get();
			if(v != null){
				return v;
			}
		}
		return null;
	}
	
	
}
