package com.github.kmaslowiec.template_manager.utils;

import java.util.HashMap;
import java.util.Map;

public class CollectionFactory<K, V> {
	
	public  Map<K, V> hashMapBuilder(K[] key, V[] value){
		HashMap<K, V> map = new HashMap<>();
		for(int i = 0; i<key.length; i++) {
			map.put(key[i], value[i]);
		}
		return map;	
	}
}