package ru.goth.repository;

import ru.goth.config.DataBaseConfig;
import ru.goth.entity.Author;
import ru.goth.entity.Buy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BuyDAO {
    public Buy getBuy(long id) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT buy_description, client\n" +
                     "FROM public.buy\n" +
                     "WHERE buy_id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Buy buy = new Buy();
            while (resultSet.next()) {
                buy.setDescription(resultSet.getString("buy_description"));
                buy.setClient(resultSet.getString("client"));
            }
            return buy;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }
//    public void setAuthor(String name) {
//        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
//             PreparedStatement statement = connection.prepareStatement("\n" +
//                     "INSERT INTO public.author \n" +
//                     "(name_author) VALUES (?)")) {
//
//            statement.setString(1, name);
//            statement.executeUpdate();
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//    }
//    public void updateAuthor(long id, String name) {
//        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
//             PreparedStatement statement = connection.prepareStatement("\n" +
//                     "UPDATE public.author\n" +
//                     "SET name_author = (?)\n" +
//                     "WHERE author_id = ?")) {
//
//            statement.setString(1, name);
//            statement.setLong(2, id);
//            statement.executeUpdate();
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//    }
//    public void deleteAuthor(String name) {
//        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
//             PreparedStatement statement = connection.prepareStatement("\n" +
//                     "DELETE FROM public.author \n" +
//                     "WHERE name_author = (?)")) {
//
//            statement.setString(1, name);
//            statement.executeUpdate();
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//    }
}
