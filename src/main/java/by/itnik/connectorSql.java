package by.itnik;

import by.itnik.model.beans.logins;
import by.itnik.model.beans.postings;

import java.sql.*;
import java.util.List;

public class connectorSql {

    //Создание соединения через метод, что бы постоянно не заполнять данные для входа
    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Connection connection;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/deliveries",
                "root", "");
        return connection;
    }

    //Sql запрос
    //Удаление таблиц и зануление id, происходит каждый раз при запуске проекта
    private static final String DELETE_LOGINS = "truncate table logins";
    private static final String DELETE_POSTINGS = "truncate table postings";

    //Поиск данных по дате
    private static final String SEARCH_BETWEEN = "SELECT *" +
            "FROM postings WHERE DATE(DocDate) >= ?";
    private static final String SEARCH_BETWEEN_ISACTIVE = "SELECT *" +
            "FROM postings WHERE DATE(DocDate) >= ? and authDelivery=1";

    //Добавление данных
    private static final String ADD_LOGINS = "insert into logins (Application, AppAccountName, isActive," +
            " JobTitle, Department)"
            + " values (?, ?, ?, ?, ?)";
    private static final String ADD_POSTINGS = "insert into postings (MatDoc, Item, DocDate," +
            " PstngDate, MaterialDescription, Quantity, BUn, AmountLC," +
            "Crcy,UserName, authDelivery)"
            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //Добавление данных в БД
    public void addData(List<logins> logins, List<postings> postings) {
        try (Connection cn = getDbConnection()) {
            Statement st = cn.createStatement();
            st.executeUpdate(DELETE_LOGINS);
            st.executeUpdate(DELETE_POSTINGS);
            PreparedStatement pstl = cn.prepareStatement(ADD_LOGINS);
            for (logins logs : logins) {
                pstl.setString(1, logs.getApplication());
                pstl.setString(2, logs.getAppAccountName());
                pstl.setBoolean(3, logs.getIsActive());
                pstl.setString(4, logs.getJobTitle());
                pstl.setString(5, logs.getDepartment());
                pstl.execute();
            }
            pstl.close();
            PreparedStatement pstp = cn.prepareStatement(ADD_POSTINGS);
            for (postings post : postings) {
                pstp.setDouble(1, post.getMat_Doc());
                pstp.setInt(2, post.getItem());
                pstp.setDate(3, post.getDoc_Date());
                pstp.setDate(4, post.getPstng_Date());
                pstp.setString(5, post.getMaterial_Description());
                pstp.setInt(6, post.getQuantity());
                pstp.setString(7, post.getbUn());
                pstp.setDouble(8, post.getAmount_LC());
                pstp.setString(9, post.getCrcy());
                pstp.setString(10, post.getUser_Name());
                pstp.setBoolean(11, post.getIsAuthDelivery());
                pstp.execute();
            }
            pstp.close();
            System.out.println("logins and postings add successful");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Вывод данных в консоль по поиску, если isActive = true, то проверяется и авторизирована ли поставка
    public void selectDateAuth(Date date, Boolean isActive) {
        try (Connection cn = getDbConnection()) {
            PreparedStatement pst;
            if (isActive) {
                pst = cn.prepareStatement(SEARCH_BETWEEN_ISACTIVE);
            } else {
                pst = cn.prepareStatement(SEARCH_BETWEEN);
            }
            pst.setDate(1, date);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date pstngDate = rs.getDate("PstngDate");
                System.out.print("Поставка авторизирована: " + rs.getBoolean("authDelivery") + ", ");
                String matDesc = rs.getString("MaterialDescription");
                String userName = rs.getString("UserName");
                Double matDoc = rs.getDouble("MatDoc");

                System.out.printf("Дата поставки: %s, описание: %s, кто провел поставку: %s," +
                        " номер поставки: = %.0f\n", pstngDate, matDesc, userName, matDoc);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
