package Frontend;
import Core.Intronet;
import Core.Request;
import Users.Manager;
import java.util.Scanner;

public  class RequestsGUI {
    public static void menu(Manager manager,Request request, Scanner input,boolean start){
        int internalStage=0;
        int command=0;
        while (start){
            if(internalStage==0){
                System.out.println("Выеберите опцию");
                System.out.println("[1]Manage requests;");
                System.out.println("[2]Back.");
                command = input.nextInt();
                if(command==1){
                    Intronet.displayFacultyRequests(manager);
                    System.out.println("Введите номер запроса:");
                    int number = input.nextInt();
                    request = Intronet.getFacultyRequest(manager)[number-1];
                    if(request!=null){
                        internalStage++;
                    }
                    else {
                        System.out.println("Wrong request number");
                    }
                }
                if(command==2){
                    start=false;
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
                if(command==2){
                    manager.rejectRequest(request);
                    request=null;
                    internalStage--;
                }
                if(command==3){
                    internalStage--;
                }
            }
        }
    }
}
