package services.impl;

import dao.*;
import dao.impl.*;
import entity.*;
import services.*;

import java.util.*;

public class NewsServiceImpl implements NewsService {
    private NewsDAO newsDao = new NewsDAOImpl();

    @Override
    public List<News> getAllNews() {
        return newsDao.getAllNews();
    }

    @Override
    public int countNumberOfNews() {
        return newsDao.countNumberOfNews();
    }

    @Override
    public List<News> getAllNewsByPagination(int page) {
        return newsDao.getAllNewsByPagination(page);
    }

    @Override
    public News getById(int id) {
        return newsDao.getById(id);
    }

    @Override
    public List<News> getAllByDate(String date) {
        return newsDao.getAllByDate(date);
    }

    @Override
    public int update(News news) {
        return newsDao.update(news);
    }

    @Override
    public int add(News news) {
        return newsDao.add(news);
    }

    @Override
    public int delete(int id) {
        return newsDao.delete(id);
    }

    public static void main(String[] args) {
        NewsService newsService = new NewsServiceImpl();
        List<News> newsList = newsService.getAllNews();
        for (News news : newsList) {
            System.out.println(news);
        }
    }
}
