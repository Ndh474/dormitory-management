package dao.impl;

import connection.MySQLConnection;
import dao.NewsDAO;
import entity.News;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.statement.NewsSQLStatements.*;

public class NewsDAOImpl implements NewsDAO {

    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<News> getAllNews() {
        List<News> newsList = new ArrayList<>();
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newsList.add(rowMapper.newsMapper(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public List<News> getAllByTitle(String title, int page, int take) {
        List<News> newsList = new ArrayList<>();
        int offset = (page - 1) * take;
        try ( Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAllNewsByTitle)
        ) {
            st.setString(1, "%" + title + "%"); // Thêm dấu % để tìm kiếm tiêu đề chứa chuỗi title
            st.setInt(2, take);
            st.setInt(3, offset);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                newsList.add(rowMapper.newsMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }
    public List<News> getAllByNumber(int number) {
        List<News> newsList = new ArrayList<>();

        try ( Connection connection = mySQLConnection.getConnection();
              PreparedStatement st = connection.prepareStatement(getAllNewsByNumber)
        ) {
            st.setInt(1, number);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                newsList.add(rowMapper.newsMapper(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    @Override
    public int countNumberOfNews() {
        int totalNews = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(countNumberOfNews)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalNews = resultSet.getInt("total_news");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalNews;
    }

    @Override
    public List<News> getAllNewsByPagination(int page) {
        List<News> newsList = new ArrayList<>();
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllByPagination)) {
            preparedStatement.setInt(1, (page - 1) * 10);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newsList.add(rowMapper.newsMapper(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    @Override
    public News getById(int id) {
        News news = null;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getNewsById)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                news = rowMapper.newsMapper(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    @Override
    public List<News> getAllByDate(String date) {
        List<News> newsList = new ArrayList<>();
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllNewsByDate)) {
            preparedStatement.setString(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                newsList.add(rowMapper.newsMapper(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    @Override
    public int update(News news) {
        int result = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateNews)) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getAccount().getAccountID());
            preparedStatement.setInt(4, news.getNewsId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int add(News news) {
        int result = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addNews)) {
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getAccount().getAccountID());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try (Connection connection = mySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteNews)) {
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        NewsDAO newsDAO = new NewsDAOImpl();
        System.out.println(newsDAO.getAllNewsByPagination(1));
    }
}
