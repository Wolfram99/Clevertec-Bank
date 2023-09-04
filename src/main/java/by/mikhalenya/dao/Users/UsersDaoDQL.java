package by.mikhalenya.dao.Users;

import by.mikhalenya.dao.Dao;
import by.mikhalenya.dao.DaoDQL;
import by.mikhalenya.dao.daoTools.InitObject;
import by.mikhalenya.dao.daoTools.InitUsers;
import by.mikhalenya.entities.Users;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoDQL extends Dao implements DaoDQL {
    InitObject initObject = new InitUsers();
    public UsersDaoDQL(Connection connection) {
        super(connection);
    }

    //    private List<Users> usersList = new ArrayList<>();
//    {
//        usersList.add(new Users(1,"Alex","Aa","Xx","aaxx","123","asdasff"));
//        usersList.add(new Users(2,"Vlad","Vv","Dd","vvdd","24432","adsasda"));
//        usersList.add(new Users(3,"Ivan","Ii","Nn","iiinn","1232","asdasdgr"));
//        usersList.add(new Users(4,"Egor","Ee","Rr","eerr","45233","dgfgdfg"));
//        usersList.add(new Users(5,"Mischa","Mm","Aa","mmaa","34412","dfgfdg"));
//        usersList.add(new Users(6,"Jenya","Jj","Ay","jjayy","52341","ghjghj"));
//        usersList.add(new Users(7,"Petay","Pp","Yy","ppyy","1231245","eeert"));
//        usersList.add(new Users(8,"Katy","Ka","Yt","kayyt","53","erfdb"));
//        usersList.add(new Users(9,"Irina","Ii","An","iian","1223","cvbbvc"));
//        usersList.add(new Users(10,"Kristina","Kr","Ani","krrani","1235","tyuytubv"));
//        usersList.add(new Users(11,"Angeina","Aa","Anie","aaanie","5234","ws5rpio"));
//
//    }
//
//    public List<Users> showAll(){
//        return usersList;
//    }

    @Override
    public List<Users> showAll() {
        List<Users> usersList = new ArrayList<>();
        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM users");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                usersList.add((Users) initObject.initObject(resultSet));
            }
            return usersList;
        } catch (SQLException e) {
            System.out.println("**** The 'SELECT' operation failed");
            e.printStackTrace();
        }
        return usersList;
    }
    @Override
    public Users showById(int id){
        try {
            preparedStatement = getConnection()
                    .prepareStatement("SELECT * FROM users WHERE id_user=?");
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return (Users) initObject.initObject(resultSet);
        } catch (SQLException e) {
            System.out.println("**** The 'SELECT' operation failed");
            e.printStackTrace();
        }
        return (Users) initObject.initObject(resultSet);
    }
}
