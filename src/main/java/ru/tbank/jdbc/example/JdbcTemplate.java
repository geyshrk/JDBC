package ru.tbank.jdbc.example;

import ru.tbank.jdbc.example.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcTemplate {

    private DataSource dataSource;

    public <T> List<T> execute(String sql, RowMapper<T> rowMapper, Object ... objects){
        List<T> result = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            for (int i = 0; i < objects.length; ++i) preparedStatement.setObject(i + 1, objects[i]);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) result.add(rowMapper.mapRow(resultSet));
        } catch (SQLException e) {
            //Обработать, мб null вернуть?
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
    public boolean executeUpdate(String sql, Object ... objects){
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            for (int i = 0; i < objects.length; ++i) preparedStatement.setObject(i + 1, objects[i]);
            preparedStatement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

}
