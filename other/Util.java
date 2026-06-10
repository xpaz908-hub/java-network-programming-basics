package other;
public class Util {
    public static void clearTerminal() {System.out.print("\033[H\033[2J");}
    public static String bold(String text) {
        return "\u001B[1m" + text + "\u001B[0m";
    }
}