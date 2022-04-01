package com.bjpowernode.dao;

import com.bjpowernode.entity.Question;
import com.bjpowernode.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    public int add(Question question) {
        String sql="insert into question(title,optionA,optionB,optionC,optionD,answer) values(?,?,?,?,?,?)";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        int result = 0;
        try {
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
    public List findAll() {
        List list = new ArrayList();
        String sql = "select * from question";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        ResultSet resultSet = null;
        try {
            resultSet = ps.executeQuery();
            while(resultSet.next()) {
                    Integer questionID = resultSet.getInt("questionId");
                    String title = resultSet.getString("title");
                    String optionA = resultSet.getString("optionA");
                    String optionB = resultSet.getString("optionB");
                    String optionC = resultSet.getString("optionC");
                    String optionD = resultSet.getString("optionD");
                    String answer = resultSet.getString("answer");
                    Question question = new Question(questionID,title,optionA,optionB,optionC,optionD,answer);
                    list.add(question);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                JdbcUtil.close(null,ps,resultSet);
        }
        return list;
    }
    public int delete(String questionId) {
        int result = 0;
        String sql = "delete from question where questionId=?";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        try {
            ps.setInt(1,Integer.valueOf(questionId));
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.close(null,ps,null);
        }
        return result;
    }
    public Question findById(String questionId) {
        List list = new ArrayList();
        String sql = "select * from question where questionId=?";
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        ResultSet resultSet = null;
        Question  question = null;
        try {
            ps.setInt(1,Integer.valueOf(questionId));
            resultSet = ps.executeQuery();
            if(resultSet.next()) {
                String title = resultSet.getString("title");
                String optionA = resultSet.getString("optionA");
                String optionB = resultSet.getString("optionB");
                String optionC = resultSet.getString("optionC");
                String optionD = resultSet.getString("optionD");
                String answer = resultSet.getString("answer");
                question = new Question(Integer.valueOf(questionId),title,optionA,optionB,optionC,optionD,answer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(null,ps,resultSet);
        }
        return question;
    }
    public int update(Question question) {
        String sql = "update question set title=?,optionA=?,optionB=?,optionC=?,optionD=?,answer=?" +
                "where questionId=?";
        int result = 0;
        PreparedStatement ps = JdbcUtil.createStatement(sql);
        try {
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            ps.setString(7,question.getQuestionId()+"");
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtil.close(null,ps,null);
        }
        return result;
    }


}
