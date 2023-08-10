package Constructors;

import Core.Intronet;
import Core.Request;
import Enums.Faculty;
import Enums.RequestType;
import java.util.Scanner;
public class RequestConstructor {
    public static void requestCreation(String courseId, String userId, Faculty faculty){
        Scanner input = new Scanner(System.in);
        RequestType requestType = null;
        while (requestType==null){
            System.out.println("Choose request type");
            System.out.println("[1]Add course;\n[2]Drop course.");
            int index = input.nextInt();
            if(index < 0 || index > 7){
                System.out.println("WRONG NUMBER!");
            }
            else {
                requestType = RequestType.values()[index-1];
            }
        }
        Intronet.requests.add(new Request(courseId,userId,requestType,faculty));
    }
}
