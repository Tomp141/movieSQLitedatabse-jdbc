/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.pk;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tompk
 */
public class SQLiteConnection {
    public static Connection myConn = null;
    public static String sqliteServer ="jdbc:sqlite:";
    public static String resetPath ="";
    
    public static boolean isDatabaseExists(String dbFilePath){
        File dbFile = new File(dbFilePath);
        return dbFile.exists();   
    }
    public static Connection getConnection(){
        sqliteServer ="jbc:sqlite:";
        String getFilePath = new File("").getAbsolutePath();
        String fileAbsolutePath = getFilePath.concat("\\src\\movie\\pk\\database\\movie.db");
        
        if (isDatabaseExists(fileAbsolutePath)){
            try {
                myConn = DriverManager.getConnection(sqliteServer+fileAbsolutePath);
                System.out.println("Connection to ite has been established!");
            } catch (SQLException ex){
                Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
             createNewDatabase("database","movie");
            try {
                myConn = DriverManager.getConnection(sqliteServer+fileAbsolutePath);
                System.out.println("Connection to ite has been established!");
            } catch (SQLException ex){
                Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return myConn;
    }
    public static void createNewDatabase(String fileSubFolder,String fileName){
        String getFilePath = new File ("").getAbsolutePath();
        String fileAbsolutePath ="";
        if(fileSubFolder.isEmpty()){
                fileAbsolutePath = getFilePath.concat("\\src\\movie\\pk\\" + fileName + ".db");
                resetPath =fileAbsolutePath;
        }else{
            fileAbsolutePath = getFilePath.concat("\\scr\\movie\\pk\\" + fileSubFolder + "\\" + fileName + ".db");
            resetPath =fileAbsolutePath;    
        }
        Connection conn;
        try {
            conn = DriverManager.getConnection(sqliteServer+fileAbsolutePath);
        if(conn != null){
            DatabaseMetaData meta = conn.getMetaData();
            try{
                Statement statement = conn.createStatement();
                statement.executeQuery(
                "CREATE TABLE words(ID INT PRIMARY KEY NOT NULL),"
                +"word TEXT NOT NULL,"
                +"type TEXT NOT NULL,"
                +"definition NOT NULL"
                );
            } catch (SQLException sqlException){
            }
         }
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
