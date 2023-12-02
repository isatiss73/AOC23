/**
 * main class wich does nothing but call the day class
 */
public class Main
{
    /**
     * just a sysout with a simpler syntax
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
        String path = System.getProperty("user.dir") + "/resources/1_Trebuchet.txt";
        System.out.println(Trebuchet.solve(path));
    }
}