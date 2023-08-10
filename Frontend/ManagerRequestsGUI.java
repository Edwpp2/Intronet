package Frontend;
import Core.Intronet;
import Core.Request;
import Users.Manager;
import java.util.Scanner;

public  class ManagerRequestsGUI {
    public static void menu(Manager manager, Scanner input,boolean start){
        int internalStage=0;
        Request request = null;
        int command=0;
        while (start){
            if(internalStage==0){
                System.out.println("Chose option:");
                System.out.println("[1]Manage requests;");
                System.out.println("[2]Back.");
                command = input.nextInt();
                if(command==1){
                    Intronet.displayFacultyRequests(manager);
                    System.out.println("Enter request number:");
                    int number = input.nextInt();
                    if(number < 1 || number > Intronet.getFacultyRequest(manager).length){
                        System.out.println("WRONG NUMBER!");
                    }
                    else {
                        request = Intronet.getFacultyRequest(manager)[number-1];
                        internalStage++;
                    }
                }
                else if(command==2){
                    start=false;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==1){
                System.out.println("Выберите тип операции:");
                System.out.println("[1]Accept");
                System.out.println("[2]Reject");
                System.out.println("[3]Back");
                command=input.nextInt();
                if(command==1){
                    manager.applyRequest(request);
                    request=null;
                    internalStage--;
                }
                else if(command==2){
                    manager.rejectRequest(request);
                    request=null;
                    internalStage--;
                }
                else if(command==3){
                    internalStage--;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
        }
    }
}
