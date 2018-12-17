package sql_verilerim;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sql_baglanma
{
    public static final String DB_NAME="EnvanterTakip.db";
    public static final String Connection_String="jdbc:sqlite:"+DB_NAME;

    private Sql_baglanma(){}

    private static Sql_baglanma instance=new Sql_baglanma();

    public static Sql_baglanma getInstance(){return instance;}

    public Connection connection;


    /*sqlite bağlanması*/
    public boolean veritabaninaBaglanma()
    {
        try {
            connection= DriverManager.getConnection(Connection_String);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /*sqlite çıkış sağlanması*/
    public void veritabaniniKapat()
    {
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}





















