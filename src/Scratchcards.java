import java.io.*;

import static java.lang.Integer.parseInt;

public class Scratchcards
{
    /**
     * transform a list of text integers to a list of int
     * @param list the (static) list of text integers
     * @return the (static) list of int
     */
    public static int[] getIntList(String[] list)
    {
        int length = list.length;
        int[] res = new int[length];
        for (int i=1; i<length; i++)
        {
            res[i] = parseInt(list[i]);
            Main.print(res[i]);
        }
        return res;
    }

    /**
     * part 1 : give the number of points earned with a card
     * @param card the card as a string (complete line)
     * @return the number of points of the card
     */
    public static int getScore(String card)
    {
        Main.print(card);
        String[] data = card.split(":");
        String[] numbers = data[1].split("\\|");
        int[] winning = getIntList(numbers[0].split(" +"));
        int[] possessed = getIntList(numbers[1].split(" +"));
        int res = 0;

        // we look at every pair of numbers to see if there is a match
        for (int w : winning)
        {
            if (w > 0) {
                for (int p : possessed) {
                    // we add points to the score if there is a match
                    if (w == p) {
                        if (res == 0)
                            res = 1;
                        else
                            res += res;
                        Main.print(w + " = " + p);
                    }
                }
            }
        }
        Main.print(res);
        return res;
    }

    /**
     * solve a problem statement stored in a text file
     * @param path file path
     * @return sum of the IDs of possible games
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
                res += getScore(line);
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
