/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.History;
import model.Score;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Grupa1
 */
public class DbQuery {

    public static void insertUser(User user) {
        String sql = "insert into users (username, password, mail) values (?,md5(?),?);";
        Connection conn = DBConnection.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getMail());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId((int) generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

     public static void createUserHib(User u){
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int id = (int) session.save(u);
            System.out.println("Registrovali ste se pod id-em " + id);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
     }
     
     public static User selectUserHib(String username, String password){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from User where username=? and password=?");
            query.setString(0, username).setString(1, password);
            List<User> u_list = query.list();
            session.close();
            return u_list.get(0);
     }
     
     public static User selectUserHib(String username){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from User where username=?");
            query.setString(0, username);
            List<User> u_list = query.list();
            session.close();
            return u_list.get(0);
     }
     
     public static User selectUserHib(int id){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from User where id=?");
            query.setInteger(0, id);
            List<User> u_list = query.list();
            session.close();
            return u_list.get(0);
     }
     
     public static boolean selectEmailHib(String email){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from User where mail=?");
            query.setString(0, email);
            List<String> u_list = query.list();
            session.close();
            u_list.get(0);
            boolean status;
            if(u_list.get(0)==null)
                status = false;
            else
                status = true;
            return status;
     }
     
     public static Score selectScoreHib(int id){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Score where user_id=?");
            query.setInteger(0, id);
            List<Score> u_list = query.list();
            session.close();
            return u_list.get(0);
     }
     
     public static void createScoreHib(Score s, User u){
            s.setUser(u);
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(s);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
         
     }
     
    public static ArrayList getHighscoreInfo(){
        Session sesija = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesija.beginTransaction();
        String upit = "SELECT user.username, score.wins, score.draws, score.loses, score.win_ratio\n" +
                "FROM score, user\n" +
                "WHERE score.id_score = user.id;";
        SQLQuery query = sesija.createSQLQuery(upit);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List list =  query.list();
        System.out.println(list.size());
        String s = list.toString();
        Pattern s_pattern =  Pattern.compile("(?<=\\=)(.*?)(?=\\,)|(?<=\\=)(.*?)(?=\\})");
        Matcher mtch = s_pattern.matcher(s);
        ArrayList<String> s_list = new ArrayList<String>();
        while(mtch.find()){
            s_list.add(mtch.group());
        }
        for (int i=4;i<s_list.size()-5;i+=5)
            s_list.set(i, s_list.get(i).substring(0, s_list.get(i).length()-1));             //ipravka regexa ( "}" kod prvog username-a)
        System.out.println(s_list);
        t.commit();
        sesija.close();
        return s_list;
    }
    
    public static ArrayList getHistoryInfo(int id){
        Session sesija = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesija.beginTransaction();
        String upit = "SELECT id_history, user1_id, user2, score\n" +
                        "FROM history\n" +
                        "WHERE user1_id = :par1";
        SQLQuery query = sesija.createSQLQuery(upit);
        query.setParameter("par1",id);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List list =  query.list();
        System.out.println(list.size());
        String s = list.toString();
        System.out.println(s);
        Pattern s_pattern =  Pattern.compile("(?<=\\=)(.*?)(?=\\,)|(?<=\\=)(.*?)(?=\\})");
        Matcher mtch = s_pattern.matcher(s);
        ArrayList<String> h_list = new ArrayList<String>();
        while(mtch.find()){
            h_list.add(mtch.group());
        }
        for (int i=3;i<h_list.size()-4;i+=4)
            h_list.set(i, h_list.get(i).substring(0, h_list.get(i).length()-1));             //ipravka regexa ( "}" kod prvog username-a)
        System.out.println(h_list);
        t.commit();
        sesija.close();
        return h_list;
    }
    
    public static ArrayList getGameHistoryInfo(int profile){
        Session sesija = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesija.beginTransaction();
        String upit = "select move1, move2, move3, move4, move5, move6, move7, move8, move9\n" +
                        "from history\n" +
                        "where id_history = :par1";
        SQLQuery query = sesija.createSQLQuery(upit);
        query.setParameter("par1",profile);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List list =  query.list();
        System.out.println(list.size());
        String s = list.toString();
        System.out.println("LISTA JE: "+s);
        Pattern s_pattern =  Pattern.compile("(?<=\\=)(.*?)(?=\\,)|(?<=\\=)(.*?)(?=\\})");
        Matcher mtch = s_pattern.matcher(s);
        ArrayList<String> h_list = new ArrayList<String>();
        while(mtch.find()){
            h_list.add(mtch.group());
        }
        for (int i=8;i<h_list.size()-9;i+=9)
            h_list.set(i, h_list.get(i).substring(0, h_list.get(i).length()-1));             //ipravka regexa ( "}" kod prvog username-a)
        System.out.println(h_list);
        t.commit();
        sesija.close();
        return h_list;
    }
     
     public static void createHistoryHib(History h, User u1, String u2, String move1,
             String move2, String move3, String move4, String move5, String move6,
             String move7, String move8, String move9, String score){
            h.setUser1(u1);
            h.setUser2_id(u2);
            h.setMove1(move1);
            h.setMove2(move2);
            h.setMove3(move3);
            h.setMove4(move4);
            h.setMove5(move5);
            h.setMove6(move6);
            h.setMove7(move7);
            h.setMove8(move8);
            h.setMove9(move9);
            h.setScore(score);
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(h);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
         
     }
     
     public static void deleteUserHib(String username){
         User u = selectUserHib(username);
         String sql1 = "SET SQL_SAFE_UPDATES = 0;";
         String sql2 = "SET FOREIGN_KEY_CHECKS=0;";
         String sql3 = "DELETE FROM user\n" +
                        "WHERE username = ?;";
         String sql4 = "DELETE FROM score\n" +
                        "WHERE user_id = ?;";
         String sql5 = "DELETE FROM history\n" +
                        "WHERE user1_id = ? or user2 = ?;";
         String sql6 = "SET SQL_SAFE_UPDATES = 1;";
         String sql7 = "SET FOREIGN_KEY_CHECKS=1;";
        Connection conn = DBConnection.getConn();
        try {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps3.setString(1, username);
            PreparedStatement ps4 = conn.prepareStatement(sql4);
            ps4.setInt(1, u.getId());
            PreparedStatement ps5 = conn.prepareStatement(sql5);
            ps5.setInt(1, u.getId());
            ps5.setString(2, username);
            PreparedStatement ps6 = conn.prepareStatement(sql5);
            PreparedStatement ps7 = conn.prepareStatement(sql5);
            
            ps1.executeUpdate();
            ps2.executeUpdate();
            ps3.executeUpdate();
            ps4.executeUpdate();
            ps5.executeUpdate();
            ps6.executeUpdate();
            ps7.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
     }

}
