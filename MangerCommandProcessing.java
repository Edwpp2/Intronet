import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class MangerCommandProcessing {
    Manager manager;
    int command;
    int stage=1;
    Request request=null;
    Discipline discipline=null;
    Message message=null;

    public void commandProcessing() throws IOException {
        if(stage == 1){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Просмотр запросов;");
            System.out.println("2:Управление курсами;");
            System.out.println("3:Управление новостями;");
            System.out.println("4:Просмотр почты.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            br.close();
            if(command==1){
                stage++;
            }
            if(command==2){
                stage=7;
            }
            if(command==3){
                stage=9;
            }
            if(command==4){
                stage=4;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
        }
        if(stage == 2){
            Request[] requests = CourseAdepter.getRequests(manager);
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Выбрать запрос;");
            System.out.println("2:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                System.out.println("Введите номер запроса:");
                int number =Integer.parseInt(br.readLine());
                request=CourseAdepter.getObjectFromArray(requests,number);
            }
            if(command==2){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage == 3){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Принять;");
            System.out.println("2:Отклонить.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                //request.accept;
                stage--;
            }
            if(command==2){
                //request.accept
                stage=1;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage == 4){
            manager.viewAllMessages();
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Выбрать письмо;");
            System.out.println("2:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command == 1){
                Message[] messages = CourseAdepter.toArray(manager.messages);
                if(!(messages.length>0)){
                    System.out.println("Письма отсутствуют!");
                    stage=1;
                }
            }
            if(command ==2){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage == 5){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Ответить на письмо;");
            System.out.println("2:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                User user = message.userSource;
                String content = br.readLine();
                manager.writeMessage(user.login,content);
            }
            if(command==2){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage == 7){
            Discipline[] disciplines = CourseAdepter.toArray(Intronet.disciplines);
            if((disciplines.length>0)){
                CourseAdepter.viewObjectsInArray(disciplines);
            }
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Выбрать дисциплину;");
            System.out.println("2:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                System.out.println("Введите номер дисцыплины:");
                int number =Integer.parseInt(br.readLine());
                if(!(disciplines.length>0))
                {
                    discipline=CourseAdepter.getObjectFromArray(disciplines,number);
                    stage++;
                }
                else {
                    System.out.println("Вы ввели не верную комманду!");
                }
            }
            if(command==2){
                stage=1;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage == 8){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Зарегестрировать;");
            System.out.println("2:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                System.out.println("Введите кол-во кредитов:");
                int credits = Integer.parseInt(br.readLine());
                System.out.println("Введите количество студентов на потоке:");
                int capacity = Integer.parseInt(br.readLine());
                Course course = new Course(discipline.department,discipline.title,discipline.description,credits,capacity);
                Intronet.courses.add(course);
            }
            if(command==2){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage == 9){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Просмотреть;");
            System.out.println("2:редактировать.");
            System.out.println("3:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                News[] news = CourseAdepter.toArray(Intronet.news);
                if(!(news.length>0))
                {
                    CourseAdepter.viewObjectsInArray(news);
                }
                else {
                    System.out.println("Новости отсутствуют!");
                }
            }
            if(command==2){
                stage++;
            }
            if(command==3){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage == 10){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Добавить;");
            System.out.println("2:Удалить");
            System.out.println("3:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                System.out.println("Введите название новсти");
                String title = br.readLine();
                System.out.println("Введите контент новости");
                String content = br.readLine();
                News news = new News(title,content);
                Intronet.news.add(news);
            }
            if(command==2){
                stage++;
            }
            if(command==3){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage==11){
            News[] news = CourseAdepter.toArray(Intronet.news);
            if((news.length>0)){
                CourseAdepter.viewObjectsInArray(news);
                System.out.println("Введите номер новости для удаления");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int number = Integer.parseInt(br.readLine());
                News newss = CourseAdepter.getObjectFromArray(news,number);
                Intronet.news.remove(newss);
                br.close();
            }
            else {
                System.out.println("Новости отсутствуют!");
            }
            stage--;
        }
    }
}
