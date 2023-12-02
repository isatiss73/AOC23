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
        while (c != CharacterIterator.DONE)
        {
            if (c >= '0' && c <= '9')
            {
                last = Character.getNumericValue(c);
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
        while (c != CharacterIterator.DONE)
        {
            if (c >= '0' && c <= '9')
            {
                number = "";
                last = Character.getNumericValue(c);
                if (first < 0)
                    first = last;
            }
            else
            {
                number += c;
                boolean found = false;
                int i = 0;
                while (i<10 && !found)
                {
                    found = numbers[i].startsWith(number);
                    i++;
                }
                i--;
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
                System.out.println(getTrueValue(line) + "   " + line);
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
