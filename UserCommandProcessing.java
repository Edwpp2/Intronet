import Enums.Role;

import java.util.HashSet;

public class UserCommandProcessing {
    public void processCommand(int command,User user){
        if(user.role== Role.STUDENT){
            Student student = (Student) user;
            if(command==1){

            } else if (command==2) {

            } else if (command==3) {

            } else if (command==4) {

            } else if (command==5) {

            } else if (command==6) {

            }
        }
        if(user.role==Role.TEACHER){
            if(command==1){

            } else if (command==2) {

            } else if (command==3) {

            } else if (command==4) {

            } else if (command==5) {

            } else if (command==6) {

            }
        }
        if(user.role==Role.MANAGER){

        }
        if(user.role==Role.ADMIN){

        }
    }
}
