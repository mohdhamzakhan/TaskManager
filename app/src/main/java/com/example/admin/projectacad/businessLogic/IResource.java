package com.example.admin.projectacad.businessLogic;

import java.util.ArrayList;

/**
 * Created by admin on 13-05-2016.
 */
public interface IResource {
    //Creating Database
    String CreateDatabase(String[] ColumnName, String[] DataTypes);

    //Get the result from Database
    ArrayList<To_Do> GetResultDatabase(int Status);

    //Insert into data base
    String InsertDatabase(To_Do values);

    //Delete from Database
    String DeleteDatabase(int id);

    String UpdateDatabase(int id, int Status);

}
