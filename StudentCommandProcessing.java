import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class StudentCommandProcessing {
    Student student=null;
    Course course=null;
    Teacher teacher=null;
    Message message=null;
    Course[] coursesForRegistration=null;
    Teacher[] teachers=null;
    int stage=1;
    int command;

    public void Processing() throws IOException {
        if(stage==1){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Просмотр списка курсов;");
            System.out.println("2:Регистрация на курсы;");
            System.out.println("3:Текущие оценки;");
            System.out.println("4:Транскрипт;");
            System.out.println("5:Оценить учителей;");
            System.out.println("6:Написать письмо;");
            System.out.println("7:Просмотр новостей;");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            br.close();
            if(command==1){
                stage++;
            }
            if(command==2){
                stage=4;
            }
            if(command==3){
                stage=6;
            }
            if(command==4){
                stage=7;
            }
            if(command==5){
                stage=8;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
        }
        if(stage==2){
            Course[] courses = CourseAdepter.toArray(student.currentCourses.keySet());
            if(!(courses.length>0)){
                stage--;
            }
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
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();

        }
        if(stage==3){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Просмотреть файлы курса;");
            System.out.println("2:Просмотреть оценки по курсу;");
            System.out.println("3:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                course.viewMatrials();
            }
            if(command==2){
                //View marks in course
            }
            if(command==3){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage==4){
            coursesForRegistration = CourseAdepter.getCourses(Intronet.courses,student);
            if(!(coursesForRegistration.length>0)){
                System.out.println("Нет доступных курсов для реестрации!");
                stage=1;
            }
            stage++;
        }
        if(stage==5){
            CourseAdepter.viewObjectsInArray(coursesForRegistration);
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Выбрать курс для регестрации;");
            System.out.println("2:Назад");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                System.out.println("Введите номер курса");
                int number = Integer.parseInt(br.readLine());
                course = CourseAdepter.getObjectFromArray(coursesForRegistration,number);
                student.currentCourses.put(course,new double[4]);
                course=null;
                stage--;
            }
            if(command==2){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage==6){
            //View current marks
            stage--;
        }
        if(stage==7){
            student.viewTranscript();
            stage=1;
        }
        if(stage==8){
            teachers = CourseAdepter.toArray(student.getTeacher());
            if(!(teachers.length>0)){
                System.out.println("Нет учителей для оценки");
                stage=1;
            }
            else {
                stage++;
            }
        }
        if(stage==9){
            CourseAdepter.viewObjectsInArray(teachers);
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Выбрать учителя;");
            System.out.println("2:Назад");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                System.out.println("Введите номер учителя");
                int number = Integer.parseInt(br.readLine());
                teacher = CourseAdepter.getObjectFromArray(teachers,number);
                //addMarkForTeacher
                stage--;
            }
            if(command==2){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage == 10){
            student.viewAllMessages();
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Выбрать письмо;");
            System.out.println("2:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command == 1){
                Message[] messages = CourseAdepter.toArray(student.messages);
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
        if(stage == 11){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Ответить на письмо;");
            System.out.println("2:Назад.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            command=Integer.parseInt(br.readLine());
            if(command==1){
                User user = message.userSource;
                String content = br.readLine();
                student.writeMessage(user.login,content);
            }
            if(command==2){
                stage--;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
        if(stage==12){
            System.out.println("Выберите комманду из списка:");
            System.out.println("1:Просмотреть;");
            System.out.println("2:Назад.");
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
                stage=1;
            }
            else {
                System.out.println("Вы ввели не верную комманду!");
            }
            br.close();
        }
    }
}
