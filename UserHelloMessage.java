import Enums.Role;

public class UserHelloMessage {

    public static void viewHelloMessage(User user){
        if(user.role== Role.STUDENT){
            System.out.println("Choose an option:");
            System.out.println("1:View schedule");
            System.out.println("2.Register to the course");
            System.out.println("3.View transcript");
            System.out.println("4.View notification");
            System.out.println("5.View news");
            System.out.println("6:Quite");
        }
        if(user.role==Role.TEACHER){
            System.out.println("Choose an option:");
            System.out.println("1:View schedule");
            System.out.println("2 View list of courses");
            System.out.println("3.View notification");
            System.out.println("4.View news");
            System.out.println("5.Quite");
        }
        if(user.role==Role.MANAGER){
            System.out.println("Choose an option:");
            System.out.println("1.View notification");
            System.out.println("2.View requests");
            System.out.println("3 Manage courses");
            System.out.println("4.Manage news");
            System.out.println("5.Academic performance notification");
            System.out.println("6.Quite");
        }
        if(user.role==Role.ADMIN){
            System.out.println("Choose an option:");
            System.out.println("1:Add user");
            System.out.println("2.Remove user");
        }
    }


}
