import java.io.*;

import static java.lang.Integer.max;
import static java.lang.Integer.parseInt;

public class Conundrum
{
    /**
     * say if a round is possible (part 1)
     * @param round the round as a string
     * @return yes or no it's obvious
     */
    public static boolean isRoundPossible(String round)
    {
        boolean res = true;
        String[] colors = round.split(", ");

        // we count each number until one is impossible
        for (int i=0; i<colors.length && res; i++)
        {
            String[] thatsALotOfArrays = colors[i].split(" ");

            // we see the color and the validity with it
            res = switch (thatsALotOfArrays[1]) {
                case "red" -> (parseInt(thatsALotOfArrays[0]) <= 12);
                case "green" -> (parseInt(thatsALotOfArrays[0]) <= 13);
                case "blue" -> (parseInt(thatsALotOfArrays[0]) <= 14);
                default -> res;
            };
        }
        return res;
    }

    /**
     * part 1 : say if a game is possible with 2 red, 3 green, 4 blue
     * @param game the game as a string (complete line)
     * @return the ID if possible, 0 either
     */
    public static int isGamePossible(String game)
    {
        String[] data = game.split(": ");
        int id = parseInt(data[0].substring(5));
        String[] rounds = data[1].split("; ");
        int res = id;

        // we see every round of a game until one is impossible
        for (int i=0; i < rounds.length && res == id; i++)
        {
            if (!isRoundPossible(rounds[i]))
                res = 0;
        }
        System.out.println(res + "   " + game);
        return res;
    }

    /**
     * part 2 : get the power of a set of cubes for a game
     * @param game game to match with the set of cubes
     * @return red limit * green limit * blue limit
     */
    public static int getPowerOf(String game)
    {
        String[] data = game.split(": ");
        String[] rounds = data[1].split("; ");
        int red = 0;
        int green = 0;
        int blue = 0;

        // we look at every round one by one
        for (String round : rounds) {
            String[] colors = round.split(", ");

            // we test every number to see the limit for each color
            for (String color : colors) {
                String[] thatsALotOfArrays = color.split(" ");

                // we see which color is concerned and if the limit changes for it
                switch (thatsALotOfArrays[1]) {
                    case "red" -> red = max(red, parseInt(thatsALotOfArrays[0]));
                    case "green" -> green = max(green, parseInt(thatsALotOfArrays[0]));
                    case "blue" -> blue = max(blue, parseInt(thatsALotOfArrays[0]));
                }
            }
        }
        return red * green * blue;
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
                res += getPowerOf(line);
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
