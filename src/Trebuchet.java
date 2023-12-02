import java.io.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * Day 1 : Trebuchet | stars : 1
 */
public class Trebuchet
{
    public static String[] numbers = new String[]
            {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    /**
     * first part : parse a line of the file with only digital digits
     * @param word the line as a String
     * @return the integer value of the line
     */
    public static int getCalibrationValue(String word)
    {
        int first = -1;
        int last = -1;
        CharacterIterator it = new StringCharacterIterator(word);
        char c = it.current();

        // we read every character one by one
        while (c != CharacterIterator.DONE)
        {
            // if the character is a digit, we save it
            if (c >= '0' && c <= '9')
            {
                last = Character.getNumericValue(c);

                // save of the first digit of the word
                if (first < 0)
                    first = last;
            }
            it.next();
            c = it.current();
        }
        return first*10 + last;
    }

    /**
     * first part : parse a line of the file with text digits
     * @param word the line as a String
     * @return the integer value of the line
     */
    public static int getTrueValue(String word)
    {
        int first = -1;
        int last = -1;
        String number = "";
        CharacterIterator it = new StringCharacterIterator(word);
        char c = it.current();

        // we read every character one by one
        while (c != CharacterIterator.DONE)
        {
            // same than getCalibrationValue
            if (c >= '0' && c <= '9')
            {
                number = "";
                last = Character.getNumericValue(c);
                if (first < 0)
                    first = last;
            }
            // additional letters management
            else
            {
                number += c;
                boolean found = false;
                int i = 0;

                // we search if a number starts with the current part
                while (i<10 && !found)
                {
                    found = numbers[i].startsWith(number);
                    i++;
                }
                i--;

                // if there is a match, we see if it is complete
                if (found)
                {
                    if (number.equals(numbers[i]))
                    {
                        last = i;
                        if (first < 0)
                            first = last;
                        number = "";
                    }
                }
                // if not, we see if the end of the part is the start of a number
                else
                {
                    while (!number.isEmpty() && !found)
                    {
                        number = number.substring(1);
                        i = 0;
                        while (i<10 && !found)
                        {
                            found = numbers[i].startsWith(number);
                            i++;
                        }
                        i--;
                    }
                    if (found)
                    {
                        if (number.equals(numbers[i]))
                        {
                            last = i;
                            if (first < 0)
                                first = last;
                            number = "";
                        }
                    }
                }
            }
            it.next();
            c = it.current();
        }
        return first*10 + last;
    }

    /**
     * solve a problem statement stored in a text file
     * @param path file path
     * @return sum of all the calibration values
     */
    public static int solve(String path)
    {
        try
        {
            InputStream input = new FileInputStream(path);
            Reader isreader = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isreader);
            int res = 0;
            String line;
            while ((line = reader.readLine()) != null)
            {
                res += getTrueValue(line);
            }
            reader.close();
            isreader.close();
            input.close();
            return res;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
