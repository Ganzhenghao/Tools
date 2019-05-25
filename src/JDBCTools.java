import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * ClassName JDBCTools
 * Description
 * Author Ganzhenghao
 * Date 2018/12/18  14:47
 * Version 1.0
 **/
public class JDBCTools {
    private static String driverClass = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;
    static {
        // 1 创建属性配置对象
        Properties properties = new Properties();

        try {
            /*
            * 使用类加载器, 加载src下的资源文件
            * FileInputStream fis = JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
            * */
            properties.load(new FileInputStream("jdbc.properties"));
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 建立连接,返回Connection对象
     * @return
     */
    public static Connection newInstance(){
        Connection c = null;
        try {
            /*
            * 在JDBC4.0以后 不需要自己加载驱动
            * Class.forName(driverClass);可以不用写
            */
            Class.forName(driverClass);
            c = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    public static void close (Connection connection , Statement statement , ResultSet resultSet){
        try {
            assert connection != null;
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assert statement != null;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assert resultSet != null;
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
