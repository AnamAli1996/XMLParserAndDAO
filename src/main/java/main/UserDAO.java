package main;

import java.util.Date;
import java.util.List;

import entity.*;
import persistence.PersistenceUtil;


public class UserDAO {

    public static void main(String[] args){
        UserDAO config = new UserDAO();
    }

    public UserDAO(){
    }

/*
    public void viewStudent(){
        List<User> users = PersistenceUtil.findAllStudents();
        for(User s: users){
            System.out.println("User "+s.getFirstName() + " exists.");
        }
    }

    */

    public static User createUser(String firstName, String lastName, String email, String password){
        User user = new User(firstName,lastName, email, password);
        PersistenceUtil.persist(user);
        System.out.println("User registered");
        return user;
    }

    public void deleteUser(User s) {
        PersistenceUtil.remove(s);
        System.out.println("User deleted");
    }

    public void mergeUser(User s) {
        PersistenceUtil.merge(s);
        System.out.println("User merged");


    }

    public static void addLibrary(int userId, String LibId){
        User p = PersistenceUtil.findUserById(userId);
        Library l = PersistenceUtil.findLibraryById(LibId);
        p.setLibrary(l);
        PersistenceUtil.merge(p);
    }
   /* public User updateStudent(User user){
       // List<User> studentList = PersistenceUtil.findAllStudents();
        User updateUser = PersistenceUtil.findStudentById(user.getId());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setLanguageLevel(user.getLanguageLevel());
        updateUser.setDateOfBirth(user.getDateOfBirth());
                PersistenceUtil.merge(updateUser);
                System.out.println("Updated");
        return updateUser;

        }
*/

}
