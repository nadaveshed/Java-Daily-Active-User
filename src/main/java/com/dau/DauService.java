package com.dau;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
public class DauService {

    public String searchDailyActiveUserByDate(String dateToLook) {

        try {
            if (!isDateValid(dateToLook)) {
                return "Enter valid date in format: dd/mm/yyyy";
            }

            File f = new File("src/main/java/com/dau/input.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            int counter = 0, index = 0;
            String line;
            Map<Integer, String> mapToDistinct = new HashMap<>(); //0(n)

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                mapToDistinct.put(index, columns[0] + "," + columns[1].split(" ")[0]);
                index++;
            }
            List<String> mapToList = new ArrayList<>(deDuplicateByValues(mapToDistinct).values());//0(n)
            for (int i = 0; i < mapToList.size(); i++) {
                if (dateToLook.equals(mapToList.get(i).split(",")[1])) {
                    counter++;
                }
            }
            if (counter == 0) {
                return "The date " + dateToLook + " doesn't exist in " + f.getName() + " file.";
            }
            return "The DAU for " + dateToLook + " is " + counter;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    private boolean isDateValid(String date)
    {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private Map<Integer, String> deDuplicateByValues(Map<Integer, String> map) {
        Map<String, Integer> inverse = map.entrySet().stream().collect(toMap(
                Map.Entry::getValue,
                Map.Entry::getKey,
                Math::max)
        );
        return inverse.entrySet().stream().collect(toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}
