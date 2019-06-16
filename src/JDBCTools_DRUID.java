import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.jdbc.Statement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName JDBCTools_DRUID
 * Description TODO
 * Author Ganzhenghao
 * Date  2019/6/16 18:12
 * Version 1.0
 **/
public class JDBCTools_DRUID {
    //定义成员变量
    private static DataSource dataSource;
    //静态代码块加载配置
    static {
        try {
            Properties properties = new Properties();
            //通过ClassLoader加载Src下的文件
            properties.load(JDBCTools_DRUID.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource  = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取数据库连接池
    public static DataSource getDataSource(){
        return dataSource;
    }
    //获取连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    //释放资源
    public static void close(Connection conn, Statement st){
        close(conn, st, null);
    }
    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
