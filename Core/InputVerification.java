package Core;

public class InputVerification {
    public static int intValueCheck(String streamValue){
        try {
            return Integer.parseInt(streamValue);

        } catch (NumberFormatException e) {
            System.out.println("Value that was entered is't number!");
        }
        return 0;
    }
    public static double doubleValueCheck(String streamValue){
        try {
            return Double.parseDouble(streamValue);

        } catch (NumberFormatException e) {
            System.out.println("Value that was entered is't number!");
        }
        return 0;
    }

}
