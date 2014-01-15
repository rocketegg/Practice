package main.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class BBUtil {

	public static String filename="C:/Users/Gamer/Documents/rawt6.txt";
	public static String filename_processed="C:/Users/Gamer/Documents/rawt6processed.txt";
	
	public static void processFile() {
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename_processed)));
			String line = "";
			while ((line = in.readLine()) != null) {
				String [] split = line.split("\t");
				System.out.println("Processing: " + line);
				
				
				if (split.length > 1) {
					//Process singular
					if (split[0].contains("[")) {
						String prefix = split[0].substring(0, split[0].indexOf("["));
						String[] suffixsplit = getBracketed(split[0]);
						String [] keys = new String[suffixsplit.length];
						for (int i = 0; i < suffixsplit.length; i++) {
							keys[i] = prefix+suffixsplit[i];
						}
						
						String [] vals;
						if (split[1].contains("[")) {
							String val_prefix = split[1].substring(0, split[1].indexOf("["));
							String [] val_suffixsplit = getBracketed(split[1]);
							vals = new String[val_suffixsplit.length];
							for (int i =0; i < val_suffixsplit.length; i++) {
								vals[i] = val_prefix + val_suffixsplit[i];
							}
						} else {
							vals = new String[1];
							vals[0] = split[1];
						}
						
						//process singular
						for (int i = 0; i < keys.length; i++) {
							if (i+1 > vals.length) {
								
								out.println(concat(keys[i], vals[0]));
							} else {
								out.println(concat(keys[i], vals[i]));
							}
						}
						
						//process plural
						for (int i = 0; i < keys.length; i++) {
							if (i+1 > vals.length) {
								
								out.println(concat(pluralizeKey(keys[i]), pluralizeValue(vals[0])));
							} else {
								out.println(concat(pluralizeKey(keys[i]), pluralizeValue(vals[i])));
							}
						}
						
					} else {
						//Process singular
						out.println(concat(split[0], split[1]));
						
						//Process plural
						out.println(concat(pluralizeKey(split[0]), pluralizeValue(split[1])));
					}

				}
				
			}
			//Append others
			appendOther(out);
			
			in.close();
			out.close();
			System.out.println("Done");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not find file: " + filename);
			e.printStackTrace();
		} catch (Exception d) {
			
		}
	}
	
	private static String concat(String key, String value, boolean last) {
		if (last) {
			String processed = concat(key,value);
			processed.replace(",", "");
			return processed;
		} else {
			return concat(key,value);
		}
	}
	
	private static String concat(String key, String value) {
		//escape chars
		if (key.contains("'")) {
			key = key.replace("'", "\\'");
		}
		
		if (value.contains("'")) {
			value = value.replace("'", "\\'");
		}
		String processed = "'" + key.toLowerCase() + "':'" + value + "',";
		System.out.println("\t Output: " + processed);
		return processed;
	}
	
	private static String [] getBracketed(String s) {
		String [] returnVal;
		if (s.indexOf("[") == -1) {
			returnVal = new String[1];
			returnVal[0] = s;
			return returnVal;
		}
			
		String suffix = s.substring(s.indexOf("[")+1, s.indexOf("]"));
		String[] suffixsplit = suffix.split(",");
		for (int i = 0; i < suffixsplit.length; i++) {
			suffixsplit[i] = suffixsplit[i].trim();
		}
		return suffixsplit;
	}
	
	private static String pluralizeKey(String s) {
		if (s.endsWith("y")) {
			s = s.substring(0, s.length()-1) + "ies";
		} else if (s.endsWith("x")) {
			s = s + "es";
		} else {
			s = s + "s";
		}
		return s;
	}
	
	private static String pluralizeValue(String s) {
		if (s.endsWith("y.")) {
			s = s.substring(0, s.length()-2) + "ies.";
		} else if (s.endsWith("x.")) {
			s = s.substring(0, s.length()-2) + "xes.";
		} else if (s.endsWith("y")) {
			s = s.substring(0, s.length()-1) + "ies";
		} else if (s.endsWith("x")) {
			s = s.substring(0, s.length()-1) + "es";
		}else {
			s = s.replace(".", "s.");
		}
		return s;
	}
	
	private static void appendOther(PrintWriter out) {
		HashMap<String, String> others = new HashMap<String, String>();
		others.put("l.l.c.", "L.L.C.");
		others.put("l.l.c", "L.L.C.");
		others.put("llc", "L.L.C.");
		others.put("l.lc", "L.L.C.");
		others.put("ll.c", "L.L.C.");
		others.put("l.l.c.,", "L.L.C.");
		others.put("llc,", "L.L.C.");
		
		
		for (Entry<String,String> e : others.entrySet()) {
			out.println(concat(e.getKey(), e.getValue()));
			//out.println(concat(pluralizeKey(e.getKey()), pluralizeValue(e.getValue())));
		}
		
	}
	
	public static void main(String[] args) {
		BBUtil.processFile();
	}
}
