package stringProblems.ValidNumber;

public class ValidNumber {
    public static void main(String[] args) {
        String s = "3.33";
        String[] out = splitMe('.', s);
        System.out.println("this is the first tline www");
    }

    public static boolean isNumber(String s) {
        if(s == null || s.trim().equals(""))
            return false;

        String[] components = splitMe('e', s.trim().toLowerCase());

        boolean isValid = isDecimal(components[0], true);
        if(components.length > 1)
            isValid = isValid && !components[1].equals("") && isInteger(components[1], true);
        return isValid;
    }

    public static boolean isDecimal(String input, boolean firstCall)
    {
        System.out.println("isDecimal called for  " + input);
        if(input == null || input.trim().equals(""))
            return false;

        if(firstCall && (input.charAt(0) == '+' || input.charAt(0) == '-'))
            return  isDecimal(input.substring(1), false);

        if(input.trim().equals("."))
            return false;

        String[] integers = splitMe('.', input.trim());

        for(String i : integers)
            System.out.println("isDecimal split ints " + i);

        boolean isValid = isInteger(integers[0], false);
        if(integers.length > 1)
            isValid = isValid && isInteger(integers[1], false);
        return isValid;
    }

    public static boolean isInteger(String input, boolean firstCall)
    {
        System.out.println("isInteger called for   " + input);

        if(input == null)
            return false;
        if(input.trim().equals(""))
            return true;
        if(firstCall && (input.charAt(0) == '+' || input.charAt(0) == '-'))
            return !input.substring(1).equals("") && isInteger(input.substring(1), false);

        for(int i = 0; i < input.length(); i++)
        {
            if(!Character.isDigit(input.charAt(i)))
                return false;
        }
        return true;
    }

    public static String[] splitMe(char c, String input)
    {
        int index = input.indexOf(c);
        if(index == -1)
            return new String[]{input};
        return new String[] {input.substring(0, index), input.substring(index + 1)};
    }
}
