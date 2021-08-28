/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.pk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tompk
 */
public class Operations {
    public static void insertData(int ID,String word,String type ,String definition){
        String sqlQuery = "INSERTION INTO words (ID, word,type,definition)VALUE(?,?,?,?)";
        try{
          Connection conn = SQLiteConnection.getConnection();
          PreparedStatement preparedStatement;
          preparedStatement = conn.prepareStatement(sqlQuery);
          preparedStatement.setInt(1,ID);
          preparedStatement.setString(2, word);
          preparedStatement.setString(3, type);
          preparedStatement.setString(4, definition);
          preparedStatement.executeUpdate();
          System.out.println("Data has been inserted");
            
    }   catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DefaultTableModel customModel(String query,JTable table){
        DefaultTableModel Model = (DefaultTableModel) table.getModel();
        Model.setRowCount(0);
        
        try {
            Connection conn = SQLiteConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            if(resultSet.isBeforeFirst()){
                while(resultSet.next()){
                Object dateObject[] = {
                    resultSet.getString("word"),
                    resultSet.getString("type"),
                    resultSet.getString("definition")
                };
                Model.addRow(dateObject);    
            }
                System.out.println("Model has been Generated!");
            }
            
        }catch(SQLException sqlException){
            System.out.println("Error: " + sqlException.getMessage());
        }
        return Model;
    }
    
}
