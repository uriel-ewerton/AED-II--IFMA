
package util;

/**
 *
 * @author uriel
 */
public class TestaNumero {
    public static boolean isInt(String num){
        try {
            Long.parseLong(num);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    public static boolean isDouble(String num){
        try {
            Double.parseDouble(num);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }
}
