package com.my.urlshortener.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
/**
 * 
 * @author mahesh.joshi
 *
 */

public class Dictonary {
	static {
	    mapValuesToChars();
	    populateListIndexTable();

	}
    private static HashMap<Character, Integer> valueCharMapDictionary;
    private static List<Character> lstIndexTable;

    public static void mapValuesToChars() {
        valueCharMapDictionary = new HashMap<Character, Integer>();
        // convert 0 to a , 1 to b and 25 to z , 61 to 9
        IntStream.rangeClosed(0, 25).forEach((index) -> valueCharMapDictionary.put(((char)('a'+index)) , Integer.valueOf(index)));
        IntStream.rangeClosed(26, 51).forEach((index) -> valueCharMapDictionary.put(((char)('A' + (index - 26))) , Integer.valueOf(index)));
        IntStream.rangeClosed(52, 61).forEach((index) -> valueCharMapDictionary.put(((char)('0' + (index-52))) , Integer.valueOf(index)));
      }

    public static void populateListIndexTable() {
    	lstIndexTable = new ArrayList<Character>();
        // 0->a, 1->b, ..., 25->z, ..., 52->0, 61->9
    	IntStream.rangeClosed(0, 25).forEach((index) -> lstIndexTable.add((char)('a'+ index)));
    	IntStream.rangeClosed(26, 51).forEach((index) -> lstIndexTable.add((char)('A' + (index - 26))));
    	IntStream.rangeClosed(52, 61).forEach((index) -> lstIndexTable.add((char)('0' + (index - 52))));
    }

    public static String generateUniqueNumber(Long id) {
        StringBuilder uniqueURLID = new StringBuilder();
        findBase62FromBase10(id).forEach((index) -> uniqueURLID.append(lstIndexTable.get(index)));
        return uniqueURLID.toString();
    }

    private static List<Integer> findBase62FromBase10(Long id) {
        List<Integer> digits = new LinkedList<Integer>();
        //	eg. id = 65,remainder of 65/62 is 3 and then id updated to 65/62 i.e 1 , and futher reminder 1/62 is 1 so remainder [1,3] 
        while(id > 0) { //65
            int remainder = (int)(id % 62); //3,1
            ((LinkedList<Integer>) digits).addFirst(remainder);
            id /= 62; // 1
        }
        return digits;
    }

    public static Long getkey(String uniqueID) {
        List<Character> base62IDs = new ArrayList<Character>();
        for (int i = 0; i < uniqueID.length(); ++i) {
            base62IDs.add(uniqueID.charAt(i));
        }
        Long dictionaryKey = convertBase62ToBase10ID(base62IDs);
        return dictionaryKey;
    }

    private static Long convertBase62ToBase10ID(List<Character> ids) {
        long id = 0L;
        for (int i = 0, exp = ids.size() - 1; i < ids.size(); ++i, --exp) {
            int base10 = valueCharMapDictionary.get(ids.get(i));
            id += (base10 * Math.pow(62.0, exp));
        }
        return id;
    }
}

