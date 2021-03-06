package life.qbic.datamodel.identifiers;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper functions used for sample creation
 * 
 * @author Andreas Friedrich
 * 
 */
public class SampleCodeFunctions {

  private static final Logger logger = LogManager.getLogger(SampleCodeFunctions.class);


  /**
   * Checks if a String fits the QBiC barcode pattern
   * 
   * @param code A String that may be a barcode
   * @return true if String is a QBiC barcode, false if not
   */
  public static boolean isQbicBarcode(String code) {
    //String pattern = "Q[A-X0-9]{4}[0-9]{3}[A-X0-9]{2}";
    String pattern = "20[0-9]{2}-[1-4]-[0-9]{4}-[0-9]{3}-[0-9]{3}[A-X0-9]{2}";
    return code.matches(pattern);
  }

  public static boolean isMeasurementOfBarcode(String code, String type) {
    try {
      String prefix = type.split("_")[1];
      code = code.replaceFirst(prefix, "");
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    }
    return isQbicBarcode(code);//isQbicBarcode(code);
  }

  //TODO THIS IS WRONG FOR CFH CODE !!!!
  public static int compareSampleCodes(String c1, String c2) {
    if (!c1.startsWith("Q") || c1.contains("ENTITY") || !c2.startsWith("Q")
        || c2.contains("ENTITY"))
      return c1.compareTo(c2);
    try {
      // compares sample codes by projects, ending letters (999A --> 001B) and numbers (001A -->
      // 002A)
      int projCompare = c1.substring(0, 16).compareTo(c2.substring(0, 16));
      int numCompare = c1.substring(16, 19).compareTo(c2.substring(16, 19));
      int letterCompare = c1.substring(19, 20).compareTo(c2.substring(19, 20));
      if (projCompare != 0)
        return projCompare;
      else {
        if (letterCompare != 0)
          return letterCompare;
        else
          return numCompare;
      }
    } catch (Exception e) {
      logger.warn("Could not split code " + c1 + " or " + c2
          + ". Falling back to primitive lexicographical comparison.");
    }
    return c1.compareTo(c2);
  }

  /**
   * Checks if a String can be parsed to an Integer
   * 
   * @param s a String
   * @return true, if the String can be parsed to an Integer successfully, false otherwise
   */
  public static boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

  /**
   * Increments the value of an upper case char. When at "Z" restarts with "A".
   * 
   * @param c the char to be incremented
   * @return the next letter in the alphabet relative to the input char
   */
  public static char incrementUppercase(char c) {
    if (c == 'X')
      return 'A';
    else {
      int charValue = c;
      return (char) (charValue + 1);
    }
  }

  /**
   * Increments to the next sample string in the order, meaning the project code stays the same and
   * the 3 letter number is incremented, except if it's 999, then the following letter is
   * incremented and the number starts with 001 again.
   * 
   * @param code a 10 digit sample code
   * @return a new sample code
   */
  public static String incrementSampleCode(String code) {
    //String old = code.substring(5, 8);
	String old = code.substring(16, 19);
    String num = "";
    int newNum = Integer.parseInt(old) + 1;
    
    //char letter = code.charAt(8);
    char letter = code.charAt(19);
    if (newNum > 999) {
      num = "001" + incrementUppercase(letter);
    } else
      num = createCountString(newNum, 3) + letter; //TODO check if 3 ?
    //String res = code.substring(0, 5) + num;
      String res = code.substring(0, 16)  + num;
            
    return res + checksum(res);
  }

  /**
   * Creates a string with leading zeroes from a number
   * 
   * @param id number
   * @param length of the final string
   * @return the completed String with leading zeroes
   */
  public static String createCountString(int id, int length) {
    String res = Integer.toString(id);
    while (res.length() < length) {
      res = "0" + res;
    }
    return res;
  }

  /**
   * Checks which of two Strings can be parsed to a larger Integer and returns it.
   * 
   * @param a a String
   * @param b another String
   * @return the String that represents the larger number.
   */
  public static String max(String a, String b) {
    int a1 = Integer.parseInt(a);
    int b1 = Integer.parseInt(b);
    if (Math.max(a1, b1) == a1)
      return a;
    else
      return b;
  }

  /**
   * Maps an integer to a char representation. This can be used for computing the checksum.
   * 
   * @param i number to be mapped
   * @return char representing the input number
   */
  public static char mapToChar(int i) {
    i += 48;
    if (i > 57 && i<91) {
      i += 7;
    }
    return (char) i;
    
  }

  public static float getPercentageStep(int max) {
    return new Float(1.0 / max);
  }

  /**
   * Computes a checksum digit for a given String. This checksum is weighted position-specific,
   * meaning it will also most likely fail a check if there is a typo of the String resulting in a
   * swapping of two numbers.
   * 
   * @param s String for which a checksum should be computed.
   * @return Character representing the checksum of the input String.
   */
  public static char checksum(String s) {
    int i = 1;
    int sum = 0;
    for (int idx = 0; idx <= s.length() - 1; idx++) {
      sum += (((int) s.charAt(idx))) * i;
      i += 1;
    }
    return mapToChar(sum % 34);
  }
  


  /**
   * Parses a whole String list to integers and returns them in another list.
   * 
   * @param strings List of Strings
   * @return list of integer representations of the input list
   */
  public static List<Integer> strArrToInt(List<String> strings) {
    List<Integer> res = new ArrayList<Integer>();
    for (String s : strings) {
      res.add(Integer.parseInt(s));
    }
    return res;
  }

  /**
   * Returns a String denoting the range of a list of barcodes as used in QBiC
   * 
   * @param ids List of code strings
   * @return String denoting a range of the barcodes
   */
  public static String getBarcodeRange(List<String> ids) {
    String head = getProjectPrefix(ids.get(0));
    String min = ids.get(0).substring(15, 18); // (15, 18) original 5,8 hengam
    String max = min;
    for (String id : ids) {
      String num = id.substring(15, 18); //(15, 18) hengam
      if (num.compareTo(min) < 0)
        min = num;
      if (num.compareTo(max) > 0)
        max = num;
    }
    return head + min + "-" + max;
  }

  /**
   * Returns the 4 or 5 character project prefix used for samples in openBIS.
   * 
   * @param sample sample ID starting with a standard project prefix.
   * @return Project prefix of the sample
   */
  public static String getProjectPrefix(String sample) {
    boolean numeric = StringUtils.isNumeric("" + sample.charAt(4));
    if (numeric)
      return sample.substring(0, 15); //hengam from 4 to 15
    else
      return sample.substring(0, 15); // from 5 to 15 hengam
  }
  
}
