/**
 * main class which does nothing but call the day class
 */
public class Main
{
    /**
     * just a sys out with a simpler syntax
     * @param o object to print
     */
    public static void print(Object o)
    {
        System.out.println(o);
    }

    /**
     * main entry point of the program
     * @param args program arguments (nothing special here)
     */
    public static void main(String[] args)
    {
        String path = System.getProperty("user.dir") + "/resources/4_Scratchcards.txt";
        System.out.println(Scratchcards.solve(path));
    }
}