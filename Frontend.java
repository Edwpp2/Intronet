import Enums.Role;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Frontend {
    public static void main(String[] args) throws IOException {
        User currentUser = null;
        while(currentUser==null){
            System.out.println("Добро пожаловать в KBTU, выберите тип операции");
            System.out.println("1:Login\n2:Exit");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String command = br.readLine();
            if(command.equals("1")){
                System.out.println("Введите логин");
                String login = br.readLine();
                System.out.println("Введите пароль");
                String password = br.readLine();
                currentUser = Intronet.login(login,password);
                if(currentUser==null){
                    System.out.println("Вы ввели неверный логин или пароль,попытайтесь снова");
                }

            } else if (command.equals("2")) {
                System.exit(0);
            }
        }
        while (currentUser!=null){
            UserHelloMessage.viewHelloMessage(currentUser);
            System.out.println("Hi! " + currentUser.name + " " + currentUser.surname);
            if(currentUser.role == Role.STUDENT){
                Student student = (Student) currentUser;
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = br.readLine();
            }
            if(currentUser.role == Role.TEACHER){
                Teacher teacher = (Teacher) currentUser;
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = br.readLine();
            }
            if(currentUser.role == Role.MANAGER){
                Manager teacher = (Manager) currentUser;
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = br.readLine();
            }
            if(currentUser.role == Role.ADMIN){
                Admin teacher = (Admin) currentUser;
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = br.readLine();
            }

        }

    }
}
