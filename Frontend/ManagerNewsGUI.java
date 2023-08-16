package Frontend;

import Core.InputVerification;
import Core.Intranet;
import Core.News;
import Users.Manager;

import java.io.BufferedReader;
import java.io.IOException;

public class ManagerNewsGUI {
    public static void menu(Manager manager,News news, BufferedReader input) throws IOException {
        int internalStage = 0;
        int command;
        boolean start = true;
        if(Intranet.getInstance().news.size()>1){
            start=false;
            System.out.println("NO NEWS TO MANAGE!");
        }
        while (start){
            if(internalStage==0){
                System.out.println("Choose an option::");
                System.out.println("[1]Add news");
                System.out.println("[2]Manage news");
                System.out.println("[3]Back");
                command = InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    internalStage=1;
                }
                else if(command==2){
                    Intranet.getInstance().printNews();
                    System.out.println("Enter number of news!");
                    int number = InputVerification.intValueCheck(input.readLine());
                    if(number < 1 || number > Intranet.getInstance().news.size())
                    {
                        System.out.println("WRONG NUMBER!");
                    }
                    else {
                        news = manager.getNews(number);
                        internalStage=2;
                    }
                }
                else if(command==3){
                    start=false;
                    news=null;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }

            if(internalStage==1){
                System.out.println("Enter the title:");
                String title = input.readLine();
                System.out.println("Enter content:");
                String content = input.readLine();
                manager.addNews(title,content);
                internalStage--;
            }
            if(internalStage==2){
                System.out.println("Choose option");
                System.out.println("[1]Change title;");
                System.out.println("[2]Change content;");
                System.out.println("[3]Remove news");
                System.out.println("[4]Back.");
                command = InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    System.out.println("Enter new title:");
                    String title = input.readLine();
                    news.setTitle(title);
                }
                else if(command==2){
                    System.out.println("Enter new content:");
                    String content  = input.readLine();
                    news.setContent(content);
                }
                else if(command==3){
                    manager.removeNews(news);
                    internalStage=0;
                }
                else if(command==4){
                    internalStage=0;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
        }
    }
}

