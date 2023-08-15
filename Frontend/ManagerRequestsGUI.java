package Frontend;
import Core.InputVerificator;
import Core.Intronet;
import Core.Request;
import Users.Manager;
import java.io.BufferedReader;
import java.io.IOException;


public  class   ManagerRequestsGUI {
    public static void menu(Manager manager,BufferedReader input) throws IOException {
        int internalStage=0;
        Request request = null;
        int command;
        boolean start = true;
        if(Intronet.getInstance().getFacultyRequest(manager).size()<1){
            start=false;
            System.out.println("NO REQUESTS TO MANAGE!");
        }
        while (start){
            if(internalStage==0){
                System.out.println("Chose option:");
                System.out.println("[1]Manage requests;");
                System.out.println("[2]Back.");
                command = InputVerificator.intValueCheck(input.readLine());
                if(command==1){
                    Intronet.getInstance().displayFacultyRequests(manager);
                    System.out.println("Enter request number:");
                    int number = InputVerificator.intValueCheck(input.readLine());
                    if(number < 1 || number > Intronet.getInstance().getFacultyRequest(manager).size()){
                        System.out.println("WRONG NUMBER!");
                    }
                    else {
                        request = Intronet.getInstance().getFacultyRequest(manager).get(number-1);
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
                System.out.println("Chose an option:");
                System.out.println("[1]Accept");
                System.out.println("[2]Reject");
                System.out.println("[3]Back");
                command = InputVerificator.intValueCheck(input.readLine());
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
