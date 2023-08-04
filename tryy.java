public class tryy {
    public int minAddToMakeValid(String s) {
        StringBuilder title = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(title.length()==0){
                title.append(s.charAt(i));
            }
            else{
                if(title.charAt(title.length()-1)=='(' && s.charAt(i)==')'){
                    title = new StringBuilder(title.substring(0, title.length() - 2));
                }
                else{
                    title.append(s.charAt(i));
                    String modifiedString = originalString.substring(0, originalString.length() - 1);
                }
            }
        }
        return title.length();
    }
}
