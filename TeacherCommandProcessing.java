import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TeacherCommandProcessing {
    Teacher teacher;
    int stage;
    int command;
    Message message;
    Course course;
    Course[] courses;

    public void Processing() throws IOException {
        if(stage==1) {
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Просмотр списка курсов;");
            System.out.println("2:Просмотреть новости;");
            System.out.println("3.Просмотр сообщений.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command = Integer.parseInt(br.readLine());
            br.close();
            if (command == 1) {
                stage++;
            }
            if (command == 2) {
                stage = 7;
            }
            if (command == 3) {
                stage = 8;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage==2){
            courses = CourseAdepter.toArray(teacher.currentCourses);
            if(!(courses.length>0)){
                stage--;
                System.out.println("Курсы отсутствуют!");
            }
            else {
                stage++;
            }
        }
        if(stage==3){
            CourseAdepter.viewObjectsInArray(courses);
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Выбрать курс;");
            System.out.println("2:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                System.out.println("Введите номер курса");
                int number = Integer.parseInt(br.readLine());
                course = CourseAdepter.getObjectFromArray(courses,number);
                stage++;
            }
            if(command==2){
                stage=1;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage==4){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Редактировать материалы курса;");
            System.out.println("2:Управление студентами курса.");
            System.out.println("3:Назад");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                stage++;
            }
            if(command==2){
                stage=1;//ASFDDDDDDDDDDDDDDDDDD
            }
            if(command==3)
            {
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage==5){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Просомтреть список материалов курса");
            System.out.println("2:Добавить материалы курса");
            System.out.println("3:Удалить материалы курса");
            System.out.println("4:Назад");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                if(!(course.materials.size()>0)){
                    System.out.println("Материалы курса отсутствуют!");
                }
                else {
                    course.viewMatrials();
                }
            }
            if(command==2){
                String material=br.readLine();
                if(material.length()>0) {
                    System.out.println("Материал добавлен!");
                    course.materials.add(material);
                }
                else {
                    System.out.println("Материал небыл добавлен!");
                }

            }
            if(command==3)
            {

            }
            if(command==4){

            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();

        }
        if(stage==7){
            News[] news = CourseAdepter.toArray(Intronet.news);
            if(!(news.length>0))
            {
                CourseAdepter.viewObjectsInArray(news);
            }
            else {
                System.out.println("Новости отсутствуют!");
            }
            stage=1;
        }
        if(stage == 8){
            teacher.viewAllMessages();
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Выбрать письмо;");
            System.out.println("2:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command == 1){
                Message[] messages = CourseAdepter.toArray(teacher.messages);
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
        if(stage == 9){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Ответить на письмо;");
            System.out.println("2:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                User user = message.userSource;
                String content = br.readLine();
                teacher.writeMessage(user.login,content);
            }
            if(command==2){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
    }
}
