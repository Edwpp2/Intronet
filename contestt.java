public class contestt {
    public static String funnyString(String s) {
        int length = s.length();

        for(int j,k,i = 0; i < length-1;i++){
            System.out.println(j+1);
            if(Math.abs((s.charAt(i) - '0')-(s.charAt(i+1) - '0'))!=Math.abs((s.charAt(length-1-i) - '0')-(s.charAt(s.length()-2-i) - '0'))){
                return "Not funny";
            }
        }
        return "funny";
    }

    public static void main(String[] args) {
        System.out.println(funnyString("bcxz"));
    }

}
