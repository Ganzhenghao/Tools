import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * ClassName JDBCTools_C3P0
 * Description TODO
 * Author Ganzhenghao
 * Date  2019/6/2 19:08
 * Version 1.0
 **/
public class JDBCTools_C3P0 {
    //默认会找xml文件的<default-config>分支
    private static ComboPooledDataSource comboPooledDataSource;

    static {
        comboPooledDataSource = new ComboPooledDataSource();
    }

    /**
     *
     * @return 返回数据库连接池
     */
    public static DataSource getDataSource(){
        return comboPooledDataSource;
    }

    /**
     * 建立连接,返回Connection对象
     *
     * @return
     */
    public static Connection newInstance() throws SQLException {
        return comboPooledDataSource.getConnection();
    }

    /**
     * 释放资源
     *
     * @param conn
     * @param st
     * @param rs
     */
    public static void close(Connection conn, Statement st, ResultSet rs) {
        closeRs(rs);
        closeSt(st);
        closeConn(conn);
    }

    public static void close(Connection conn, Statement st) {
        closeSt(st);
        closeConn(conn);
    }

    public static void close(Statement st, ResultSet rs) {
        closeSt(st);
        closeRs(rs);
    }


    private static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs = null;
        }
    }

    private static void closeSt(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            st = null;
        }
    }

    private static void closeConn(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
}
