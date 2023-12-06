package org.example.adventofcode;

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import utilities.FileReader;
public class Day1 {
    public static void main(String[] args){
        List<String> inputList = new ArrayList<>();
        FileReader fileReader = new FileReader();

        try ( InputStream is =
                      fileReader.getFileFromResourceAsStream("day1Input.txt");
              InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader =
                      new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                inputList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Long sum = 0L;
        int counter = 1;
        for (String chain: inputList  ) {
            sum += getCalibrationValues(chain, counter++);
        }
        System.out.println("Result:" + sum);
    }



    private static int getCalibrationValues(String chain, int counter) {
        String sanitizeChain = sanitizeString(chain);
        char[] charArray= sanitizeChain.toCharArray();
        int firstDigit = -1;
        int lastDigit = -1;

        for (int i = 0; i < charArray.length; i++) {
            if(Character.isDigit(charArray[i])){
                if(firstDigit == -1)
                {
                    firstDigit = Character.getNumericValue(charArray[i]);
                }
                lastDigit = Character.getNumericValue(charArray[i]); //Keeps asigning until the end
            }
        }
        //System.out.println(counter + ") " +chain + " -> " + sanitizeChain + " ->" + ((firstDigit*10)+lastDigit));

        return (firstDigit * 10) + lastDigit;
    }

    private static String sanitizeString(String chain) {
        HashMap<String,String> traductionValues = new HashMap<>();
        traductionValues.put("one","1");
        traductionValues.put("two","2");
        traductionValues.put("three","3");
        traductionValues.put("four","4");
        traductionValues.put("five","5");
        traductionValues.put("six","6");
        traductionValues.put("seven","7");
        traductionValues.put("eight","8");
        traductionValues.put("nine","9");
        chain = chain.replace("oneight", "oneeight");
        chain = chain.replace("eightwo", "eighttwo");
        chain = chain.replace("twone", "twoone");

        for (Map.Entry<String,String> entry: traductionValues.entrySet() ) {
            chain = chain.replace(entry.getKey(), entry.getValue());
        }
        return chain;
    }

}
