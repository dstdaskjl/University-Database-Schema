import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Data {
    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try{
            OracleDataSource ods = new OracleDataSource();
            //ods.setURL("");
            //ods.setUser("");
            //ods.setPassword("");
            connection = ods.getConnection();

        }catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }

    public boolean hasAccount(String username, String password) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from account where username = ?");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next() && resultSet.getString(2).equals(password)){
            return true;
        }
        return false;
    }

    public boolean hasDup(Takes takes) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from takes " +
                "where ID = ? and course_id = ? and sec_id = ? and semester = ? and year = ?");
        preparedStatement.setObject(1, takes.ID);
        preparedStatement.setObject(2, takes.course_id);
        preparedStatement.setObject(3, takes.sec_id);
        preparedStatement.setObject(4, takes.semester);
        preparedStatement.setObject(5, takes.year);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            return true;
        }
        return false;
    }

    public boolean hasParent(Takes takes) throws SQLException {
        boolean hasParent = false;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from student where ID = ?");
        preparedStatement.setObject(1, takes.ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            hasParent = true;
            break;
        }
        if (hasParent == false){
            return hasParent;
        }

        hasParent = false;
        preparedStatement = connection.prepareStatement("select * from section where course_id = ? and sec_id = ? and semester = ? and year = ?");
        preparedStatement.setObject(1, takes.course_id);
        preparedStatement.setObject(2, takes.sec_id);
        preparedStatement.setObject(3, takes.semester);
        preparedStatement.setObject(4, takes.year);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            hasParent = true;
            break;
        }
        return hasParent;
    }

    public void insert(Takes takes) throws SQLException {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into takes values(?, ?, ?, ?, ?, ?)");
            preparedStatement.setObject(1, takes.ID);
            preparedStatement.setObject(2, takes.course_id);
            preparedStatement.setObject(3, takes.sec_id);
            preparedStatement.setObject(4, takes.semester);
            preparedStatement.setObject(5, takes.year);
            preparedStatement.setObject(6, takes.grade);
            preparedStatement.executeQuery();
            OperationAdd.succMessage.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
            OperationAdd.dataTypeErrMessage.setVisible(true);
        }

    }

    public void delete(Takes takes) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from takes where ID = ? and course_id = ? and sec_id = ? and semester = ? and year = ?");
        preparedStatement.setObject(1, takes.ID);
        preparedStatement.setObject(2, takes.course_id);
        preparedStatement.setObject(3, takes.sec_id);
        preparedStatement.setObject(4, takes.semester);
        preparedStatement.setObject(5, takes.year);
        preparedStatement.executeQuery();
    }

    public void update(Takes takes, Object newGrade) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update takes set grade = ? where ID = ? and course_id = ? and sec_id = ? and semester = ? and year = ?");
        preparedStatement.setObject(1, newGrade);
        preparedStatement.setObject(2, takes.ID);
        preparedStatement.setObject(3, takes.course_id);
        preparedStatement.setObject(4, takes.sec_id);
        preparedStatement.setObject(5, takes.semester);
        preparedStatement.setObject(6, takes.year);
        preparedStatement.executeQuery();
    }

    public String[] getTableNames() throws SQLException {
        List<String> tableList = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select table_name from user_tables");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            tableList.add(resultSet.getString(1));
        }
        String[] tableArray = tableList.toArray(new String[0]);
        return tableArray;
    }

    public String[] getIDs() throws SQLException {
        List<String> idList = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select distinct ID from student");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            idList.add(resultSet.getString(1));
        }
        String[] idArray = idList.toArray(new String[0]);
        return idArray;
    }

    public String[] getSectionInfo() throws SQLException {
        List<String> infoList = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select course_id, sec_id, semester, year from section");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String str = resultSet.getString(1) + ", " + resultSet.getString(2) + ", " + resultSet.getString(3) + ", " + resultSet.getString(4);
            infoList.add(str);
        }
        String[] infoArray = infoList.toArray(new String[0]);
        return infoArray;
    }

    public int getNumOfRowsFromTakesID(String id) throws SQLException{
        int count = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select course_id, sec_id, semester, year from takes where id = ?");
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            count++;
        }
        return count;
    }

    public int getNumOfColsFromTakesID(String id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select course_id, sec_id, semester, year from takes where id = ?");
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        return resultSetMetaData.getColumnCount();
    }

    public Object[][] getRowsFromTakesID(String id) throws SQLException{
        int rowSize = getNumOfRowsFromTakesID(id);
        int colSize = getNumOfColsFromTakesID(id);
        Object[][] rowArray = new Object[rowSize][colSize];

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select course_id, sec_id, semester, year from takes where id = ?");
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        int rowIdx = 0;
        while (resultSet.next()){
            for (int colIdx = 1; colIdx <= colSize; colIdx++){
                rowArray[rowIdx][colIdx-1] = resultSet.getObject(colIdx);
            }
            rowIdx++;
        }
        return rowArray;
    }

    public String[] getColsFromTakesID(String id) throws SQLException{
        int size = getNumOfColsFromTakesID(id);
        String[] columnArray = new String[size];
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select course_id, sec_id, semester, year from takes where id = ?");
        preparedStatement.setObject(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= size; i++){
            columnArray[i-1] = resultSetMetaData.getColumnName(i);
        }
        return columnArray;
    }

    public int getNumOfRowsFromTakesInfo(String concatenatedInfo) throws SQLException{
        concatenatedInfo = concatenatedInfo.replace(" ", "");
        int count = 0;
        Object[] colNames = concatenatedInfo.split(",");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select ID from takes where course_id = ? and sec_id = ? and semester = ? and year = ?");
        preparedStatement.setObject(1, colNames[0]);
        preparedStatement.setObject(2, colNames[1]);
        preparedStatement.setObject(3, colNames[2]);
        preparedStatement.setObject(4, colNames[3]);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            count++;
        }
        return count;
    }

    public int getNumOfColsFromTakesInfo(String concatenatedInfo) throws SQLException {
        concatenatedInfo = concatenatedInfo.replace(" ", "");
        Object[] colNames = concatenatedInfo.split(",");
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select ID from takes where course_id = ? and sec_id = ? and semester = ? and year = ?");
        preparedStatement.setObject(1, colNames[0]);
        preparedStatement.setObject(2, colNames[1]);
        preparedStatement.setObject(3, colNames[2]);
        preparedStatement.setObject(4, colNames[3]);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        return resultSetMetaData.getColumnCount();
    }

    public Object[][] getRowsFromTakesInfo(String concatenatedInfo) throws SQLException{
        concatenatedInfo = concatenatedInfo.replace(" ", "");
        int rowSize = getNumOfRowsFromTakesInfo(concatenatedInfo);
        int colSize = getNumOfColsFromTakesInfo(concatenatedInfo);
        Object[] colNames = concatenatedInfo.split(",");
        Object[][] rowArray = new Object[rowSize][colSize];

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select ID from takes where course_id = ? and sec_id = ? and semester = ? and year = ?");
        preparedStatement.setObject(1, colNames[0]);
        preparedStatement.setObject(2, colNames[1]);
        preparedStatement.setObject(3, colNames[2]);
        preparedStatement.setObject(4, colNames[3]);
        ResultSet resultSet = preparedStatement.executeQuery();

        int rowIdx = 0;
        while (resultSet.next()){
            for (int colIdx = 1; colIdx <= colSize; colIdx++){
                rowArray[rowIdx][colIdx-1] = resultSet.getObject(colIdx);
            }
            rowIdx++;
        }
        return rowArray;
    }

    public String[] getColsFromTakesInfo(String concatenatedInfo) throws SQLException{
        concatenatedInfo = concatenatedInfo.replace(" ", "");
        int size = getNumOfColsFromTakesInfo(concatenatedInfo);
        Object[] colNames = concatenatedInfo.split(",");
        String[] columnArray = new String[size];
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select ID from takes where course_id = ? and sec_id = ? and semester = ? and year = ?");
        preparedStatement.setObject(1, colNames[0]);
        preparedStatement.setObject(2, colNames[1]);
        preparedStatement.setObject(3, colNames[2]);
        preparedStatement.setObject(4, colNames[3]);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= size; i++){
            columnArray[i-1] = resultSetMetaData.getColumnName(i);
        }
        return columnArray;
    }

    public int getNumOfRows(String tableName) throws SQLException {
        int count = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            count++;
        }
        return count;
    }

    public int getNumOfCols(String tableName) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        return resultSetMetaData.getColumnCount();
    }

    public Object[][] getRows(String tableName) throws SQLException {
        int rowSize = getNumOfRows(tableName);
        int colSize = getNumOfCols(tableName);
        Object[][] rowArray = new Object[rowSize][colSize];
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName);
        ResultSet resultSet = preparedStatement.executeQuery();

        int rowIdx = 0;
        while (resultSet.next()){
            for (int colIdx = 1; colIdx <= colSize; colIdx++){
                rowArray[rowIdx][colIdx-1] = resultSet.getObject(colIdx);
            }
            rowIdx++;
        }
        return rowArray;
    }

    public String[] getColumns(String tableName) throws SQLException {
        int size = getNumOfCols(tableName);
        String[] columnArray = new String[size];
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= size; i++){
            columnArray[i-1] = resultSetMetaData.getColumnName(i);
        }
        return columnArray;
    }
}
