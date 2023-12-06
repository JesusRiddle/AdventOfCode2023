package org.example.adventofcode;

import utilities.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {

    public static void main(String[] args) throws IOException {

        List<String> inputList = new ArrayList<>();
        FileReader fileReader = new FileReader();

        try (InputStream is =
                     fileReader.getFileFromResourceAsStream("day2Input.txt");
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

        int sumValidID = 0;
        int sumPowers = 0;
        for (String game:inputList) {
            sumValidID += getValidGameID(game); //2406
            sumPowers += getPowers(game); //78375

        }
        System.out.println("Part 1:" + sumValidID);
        System.out.println("Part 2:" + sumPowers);

    }

    private static int getPowers(String game) {
        int maxRed = getMaxNumber(" (\\d+) red", game);
        int maxGreen = getMaxNumber(" (\\d+) green", game);
        int maxBlue = getMaxNumber(" (\\d+) blue", game);
        return maxRed * maxGreen * maxBlue;
    }

    private static int getMaxNumber(String expression, String game) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(game);
        int maxNumber = 0;

        while(matcher.find()){
            maxNumber = Math.max(maxNumber, Integer.parseInt(matcher.group(1)));
        }
        return maxNumber;
    }

    private static int getValidGameID(String game) {
        Pattern pattern = Pattern.compile("Game (\\d+):");
        Matcher matcher = pattern.matcher(game);
        String gameID = "0";

        if(matcher.find()){
            gameID = matcher.group(1);
        }

        String[] sets = game.split(";");
        Boolean isFeasible = true;
        for (String subset: sets) {
            if(!colorFeasible(" (\\d+) red", subset, 12)
                     || !colorFeasible(" (\\d+) green", subset, 13)
                    || !colorFeasible(" (\\d+) blue", subset, 14)){
                isFeasible = false;
            break;
            }
        }

        return isFeasible?Integer.valueOf(gameID):0;
    }

    private static boolean colorFeasible(String expression, String game, int maxCubes) {
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(game);
        int totalCubes= 0;

        while(matcher.find()){
            totalCubes += Integer.parseInt(matcher.group(1));
        }

        return totalCubes <= maxCubes;
    }
}
