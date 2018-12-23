/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.History;
import model.Score;
import model.User;
import socketConnection.Client;
import socketConnection.Server;
import util.DBConnection;
import util.DbQuery;
import static util.DbQuery.createHistoryHib;
import static util.DbQuery.selectScoreHib;
import view.AdminHighscoreForm;
import view.CreateGameForm;
import view.JoinGameForm;
import view.GameForm;
import view.HighscoreForm;
import view.LoggedInMenuForm;
import view.LoginForm;
import view.LoseForm;
import view.MenuForm;
import view.OnlineForm;
import view.HistoryForm;
import view.LoggedInHighscoreForm;
import view.PreviewForm;
import view.RegisterForm;
import view.TieForm;
import view.WinForm;

/**
 *
 * @author Filip
 */
public final class Controller {  
    Connection baza;
    
    private MenuForm m_form;
    private LoggedInMenuForm lm_form;
    private GameForm g_form;
    private LoginForm l_form;
    private RegisterForm r_form;
    private HighscoreForm hs_form;
    private LoggedInHighscoreForm lhs_form;
    private AdminHighscoreForm ahs_form;
    private HistoryForm  h_form;
    private PreviewForm p_form;
    

    private String nextTurn = "X";
    private String PlayerOne = "Player One";
    private String PlayerTwo = "Player Two";
    private int playerOneCount = 0;
    private int playerOneCountFinal = 0;
    private int playerTwoCount = 0;
    private int playerTwoCountFinal = 0;
    private int draws = 0;
    private int drawsFinal = 0;
    private double winRatio = 0;
    private String playersTurn;
    ArrayList<String> match = new ArrayList();
    private String score;
    private int move_counter;
    
    private boolean btn_set1 = false;
    private boolean btn_set2 = false;
    private boolean btn_set3 = false;
    private boolean btn_set4 = false;
    private boolean btn_set5 = false;
    private boolean btn_set6 = false;
    private boolean btn_set7 = false;
    private boolean btn_set8 = false;
    private boolean btn_set9 = false;

    private User u;
    private User u1;
    private User u2;
    private Score s;
    private History h;

    public Controller() {
        baza = DBConnection.getConn();
    if(baza == null)
            System.out.println("Nema konekcije");
        else
            System.out.println("Ima konekcije");
        makeMenuForm();
    }
    
    public Controller(User u1, User u2) {
        baza = DBConnection.getConn();
        if(baza == null)
            System.out.println("Nema konekcije");
        else
            System.out.println("Ima konekcije");
        //makeOnlineGameForm(u1,u2);
    }
    
    public void makeMenuForm(){
        m_form = new MenuForm();
        m_form.setTitle("MAIN MENU - TIC TAC TOE");
        m_form.setSize(600, 400);
        m_form.setLocationRelativeTo(null);
        m_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_form.setVisible(true);
        PlayerOne = "Player One";
        u = null;
        u1 = null;
        u2 = null;
        
        m_form.addListener(
                (e1) -> {
                    //System.out.println("NEW GAME");
                    makeGameForm();
                    m_form.dispose();
                    resetScore();
        }, (e2) -> {
            //System.out.println("HIGHSCORE");
            makeHighScoreForm();
        }, (e3) -> {
            //System.out.println("EXIT");
            m_form.dispose();
            System.exit(0);
        }, (e4) -> {
            //System.out.println("LOGIN");
            makeLoginForm();
            m_form.dispose();
        }, (e5) -> {
            //System.out.println("REGISTER");
            makeRegisterForm();
            m_form.dispose();
        });

    }
    
    public void makeLogedInMenuForm(User u){
        lm_form = new LoggedInMenuForm();
        lm_form.setTitle("MAIN MENU - TIC TAC TOE");
        lm_form.setSize(600, 400);
        lm_form.setLocationRelativeTo(null);
        lm_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lm_form.setVisible(true);
        
        String username = l_form.getTf_username();
        lm_form.setLb_username_display(username);
        
        lm_form.addListener(
                (e1) -> {
                    //System.out.println("Klik na dugme NEW GAME");
                    makeGameForm();
                    lm_form.dispose();
                    resetScore();
        }, (e2) -> {
            //System.out.println("Klik na dugme HIGHSCORE");
            if(!u.getUsername().equals("Admin"))
                makeLoggedInHighscoreForm();
            else
                makeAdminHighscoreForm();
        }, (e3) -> {
            //System.out.println("Klik na dugme EXIT");
            lm_form.dispose();
            System.exit(0);
        }, (e4) -> {
            //System.out.println("Klik na dugme LOGOUT");
            makeMenuForm();
            lm_form.dispose();
        }, (e5) -> {
            //System.out.println("Klik na dugme ONLINE GAME");
            makeOnlineForm();
            lm_form.dispose();
            resetScore();
        });

    }
    
    public void makeGameForm(){
        if(u1!=null && u2!=null){
                PlayerOne = u1.getUsername();
                PlayerTwo = u2.getUsername();
            }
        
        g_form = new GameForm();

        g_form.setTitle("TIC TAC TOE");
        g_form.setSize(600, 600);
        g_form.setLocationRelativeTo(null);
        g_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g_form.setVisible(true);
        resetGame();
        
        g_form.addListener(
                (e1) -> {
                    matchHistory("1"+nextTurn);
                    if(btn_set1 == false){
                        //System.out.println("Klik na dugme 1");
                        g_form.setBtn_1Text(nextTurn);
                        if(nextTurn.equalsIgnoreCase("X"))
                            g_form.setBtn_1Color(Color.red);
                        else
                            g_form.setBtn_1Color(Color.blue);
                        determineNextTurn();
                        determinWhoWins();
                        setPlayersName();
                        btn_set1 = true;
                    }
                    
        }, (e2) -> {
            matchHistory("2"+nextTurn);
             if(btn_set2 == false){
                //System.out.println("Klik na dugme 2");
                g_form.setBtn_2Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_2Color(Color.red);
                else
                    g_form.setBtn_2Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set2 = true;
                
             }
            
        }, (e3) -> {
            matchHistory("3"+nextTurn);
             if(btn_set3 == false){
                //System.out.println("Klik na dugme 3");
                g_form.setBtn_3Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_3Color(Color.red);
                else
                    g_form.setBtn_3Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();   
                btn_set3 = true;
                
             }
            
        }, (e4) -> {
            matchHistory("4"+nextTurn);
             if(btn_set4 == false){
                //System.out.println("Klik na dugme 4");
                g_form.setBtn_4Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_4Color(Color.red);
                else
                    g_form.setBtn_4Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set4 = true;
                
             }
            
        }, (e5) -> {
            matchHistory("5"+nextTurn);
             if(btn_set5 == false){
                //System.out.println("Klik na dugme 5");
                g_form.setBtn_5Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_5Color(Color.red);
                else
                    g_form.setBtn_5Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set5 = true;
                
             }
            
        }, (e6) -> {
            matchHistory("6"+nextTurn);
             if(btn_set6 == false){
                //System.out.println("Klik na dugme 6");
                g_form.setBtn_6Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_6Color(Color.red);
                else
                    g_form.setBtn_6Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set6 = true;
                
             }
            
        }, (e7) -> {
            matchHistory("7"+nextTurn);
                if(btn_set7 == false){
               //System.out.println("Klik na dugme 7");
               g_form.setBtn_7Text(nextTurn);
               if(nextTurn.equalsIgnoreCase("X"))
                   g_form.setBtn_7Color(Color.red);
               else
                   g_form.setBtn_7Color(Color.blue);
               determineNextTurn();
               determinWhoWins();
               setPlayersName();
               btn_set7 = true;
               
             }
            
        }, (e8) -> {
            matchHistory("8"+nextTurn);
             if(btn_set8 == false){
                //System.out.println("Klik na dugme 8");
                g_form.setBtn_8Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_8Color(Color.red);
                else
                    g_form.setBtn_8Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set8 = true;
                
             }
            
        }, (e9) -> {
            matchHistory("9"+nextTurn);            
            if(btn_set9 == false){
                //System.out.println("Klik na dugme 9");
                g_form.setBtn_9Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_9Color(Color.red);
                else
                    g_form.setBtn_9Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set9 = true;

             }
        });
    }

    public void makeOnlineGameForm(User user1, User user2, Socket socket) throws IOException{
        
        //PlayerOne = user1.getUsername();
        //PlayerTwo = user2.getUsername();
        
        g_form = new GameForm();

        g_form.setTitle("TIC TAC TOE");
        g_form.setSize(600, 600);
        g_form.setLocationRelativeTo(null);
        g_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g_form.setVisible(true);
        resetGame();
        Client client = new Client(socket, (message)->{
            
        });
        g_form.addListener(
                (e1) -> {
                    if(btn_set1 == false){
                        //System.out.println("Klik na dugme 1");
                        g_form.setBtn_1Text(nextTurn);
                        if(nextTurn.equalsIgnoreCase("X"))
                            g_form.setBtn_1Color(Color.red);
                        else
                            g_form.setBtn_1Color(Color.blue);
                        determineNextTurn();
                        determinWhoWins();
                        setPlayersName();
                        btn_set1 = true;
                        matchHistory("1"+nextTurn);
                    }
                    
        }, (e2) -> {
             if(btn_set2 == false){
                //System.out.println("Klik na dugme 2");
                g_form.setBtn_2Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_2Color(Color.red);
                else
                    g_form.setBtn_2Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set2 = true;
                matchHistory("2"+nextTurn);
             }
            
        }, (e3) -> {
             if(btn_set3 == false){
                //System.out.println("Klik na dugme 3");
                g_form.setBtn_3Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_3Color(Color.red);
                else
                    g_form.setBtn_3Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();   
                btn_set3 = true;
                matchHistory("3"+nextTurn);
             }
            
        }, (e4) -> {
             if(btn_set4 == false){
                //System.out.println("Klik na dugme 4");
                g_form.setBtn_4Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_4Color(Color.red);
                else
                    g_form.setBtn_4Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set4 = true;
                matchHistory("4"+nextTurn);
             }
            
        }, (e5) -> {
             if(btn_set5 == false){
                //System.out.println("Klik na dugme 5");
                g_form.setBtn_5Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_5Color(Color.red);
                else
                    g_form.setBtn_5Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set5 = true;
                matchHistory("5"+nextTurn);
             }
            
        }, (e6) -> {
             if(btn_set6 == false){
                //System.out.println("Klik na dugme 6");
                g_form.setBtn_6Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_6Color(Color.red);
                else
                    g_form.setBtn_6Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set6 = true;
                matchHistory("6"+nextTurn);
             }
            
        }, (e7) -> {
                if(btn_set7 == false){
               //System.out.println("Klik na dugme 7");
               g_form.setBtn_7Text(nextTurn);
               if(nextTurn.equalsIgnoreCase("X"))
                   g_form.setBtn_7Color(Color.red);
               else
                   g_form.setBtn_7Color(Color.blue);
               determineNextTurn();
               determinWhoWins();
               setPlayersName();
               btn_set7 = true;
               matchHistory("7"+nextTurn);
             }
            
        }, (e8) -> {
             if(btn_set8 == false){
                //System.out.println("Klik na dugme 8");
                g_form.setBtn_8Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_8Color(Color.red);
                else
                    g_form.setBtn_8Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set8 = true;
                matchHistory("8"+nextTurn);
             }
            
        }, (e9) -> {
             if(btn_set9 == false){
                //System.out.println("Klik na dugme 9");
                g_form.setBtn_9Text(nextTurn);
                if(nextTurn.equalsIgnoreCase("X"))
                    g_form.setBtn_9Color(Color.red);
                else
                    g_form.setBtn_9Color(Color.blue);
                determineNextTurn();
                determinWhoWins();
                setPlayersName();
                btn_set9 = true;
                matchHistory("9"+nextTurn);
             }
        });
    }
    
    public void resetGame(){
        g_form.setBtn_1Text("");
        g_form.setBtn_2Text("");
        g_form.setBtn_3Text("");
        g_form.setBtn_4Text("");
        g_form.setBtn_5Text("");
        g_form.setBtn_6Text("");
        g_form.setBtn_7Text("");
        g_form.setBtn_8Text("");
        g_form.setBtn_9Text("");
        btn_set1 = false;
        btn_set2 = false;
        btn_set3 = false;
        btn_set4 = false;
        btn_set5 = false;
        btn_set6 = false;
        btn_set7 = false;
        btn_set8 = false;
        btn_set9 = false;
        setScore();
    }
    
    public void determineNextTurn(){
        if (nextTurn.equalsIgnoreCase("X"))
            nextTurn = "O";
        else
            nextTurn = "X";
    }
    
    public void xWins(){
        makeWinForm();
        playerOneCount++;
        playerOneCountFinal++;
        btn_set1 = true;
        btn_set2 = true;
        btn_set3 = true;
        btn_set4 = true;
        btn_set5 = true;
        btn_set6 = true;
        btn_set7 = true;
        btn_set8 = true;
        btn_set9 = true;
        if(!PlayerOne.equals("Player One")){
            saveScore();
            for(int i=0;i<match.size();i++)
                System.out.println(match.get(i));
            matchHistoryDB();
        }
    }
    
    public void oWins(){
        makeLoseForm();
        playerTwoCount++;
        playerTwoCountFinal++;
        btn_set1 = true;
        btn_set2 = true;
        btn_set3 = true;
        btn_set4 = true;
        btn_set5 = true;
        btn_set6 = true;
        btn_set7 = true;
        btn_set8 = true;
        btn_set9 = true;
        if(!PlayerOne.equals("Player One")){
            saveScore();
            matchHistoryDB();
        }
    }

    public void makeWinForm(){
        score = "WON";
        WinForm w_form = new WinForm();
        w_form.setLb_name(PlayerOne);
        w_form.setTitle("YOU WON");
        w_form.setSize(400, 250);
        w_form.setLocationRelativeTo(null);
        w_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w_form.setVisible(true);
        
        w_form.addListener(
                (e1) -> {
                    //System.out.println("Klik na dugme NEW GAME");
                    resetGame();
                    w_form.dispose();
                    match = new ArrayList<String>();
        },(e2) -> {
            match = new ArrayList<String>();
            //System.out.println("Klik na dugme MAIN MENU");
            if(PlayerOne.equals("Player One"))
                makeMenuForm();
            else
                makeLogedInMenuForm(u);
            w_form.dispose();
            g_form.dispose();
            
        },(e3) -> {
            //System.out.println("Klik na dugme EXIT GAME");
            w_form.dispose();
            g_form.dispose();
            System.exit(0);
        });
    }
    
    public void makeLoseForm(){
        score = "LOST";
        LoseForm l_form = new LoseForm();
        l_form.setLb_name(PlayerOne);
        l_form.setTitle("YOU LOST");
        l_form.setSize(400, 250);
        l_form.setLocationRelativeTo(null);
        l_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l_form.setVisible(true);
        
        l_form.addListener(
                (e1) -> {
                    //System.out.println("Klik na dugme NEW GAME");
                    resetGame();
                    l_form.dispose();
                    match = new ArrayList<String>();
        },(e2) -> {
            match = new ArrayList<String>();
            //System.out.println("Klik na dugme MAIN MENU");
            if(PlayerOne.equals("Player One"))
                makeMenuForm();
            else
                makeLogedInMenuForm(u);
            l_form.dispose();
            g_form.dispose();
            
        },(e3) -> {
            //System.out.println("Klik na dugme EXIT GAME");
            l_form.dispose();
            g_form.dispose();
            System.exit(0);
        });
    }
    
    public void makeTieGameForm(){
        score = "DRAW";
        TieForm t_form = new TieForm();
        t_form.setLb_name(PlayerOne);
        t_form.setTitle("TIE");
        t_form.setSize(400, 250);
        t_form.setLocationRelativeTo(null);
        t_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        t_form.setVisible(true);
        
        t_form.addListener(
                (e1) -> {
                    //System.out.println("Klik na dugme NEW GAME");
                    resetGame();
                    t_form.dispose();
                    match = new ArrayList<String>();
        },(e2) -> {
            match = new ArrayList<String>();
            //System.out.println("Klik na dugme MAIN MENU");
            if(PlayerOne.equals("Player One"))
                makeMenuForm();
            else
                makeLogedInMenuForm(u);
            t_form.dispose();
            g_form.dispose();
        },(e3) -> {
            //System.out.println("Klik na dugme EXIT GAME");
            t_form.dispose();
            g_form.dispose();
            System.exit(0);
        });
    }
    
    public void tieGame(){
        String one = GameForm.getBtn_1Text();
        String two = GameForm.getBtn_2Text();
        String three = GameForm.getBtn_3Text();
        String four = GameForm.getBtn_4Text();
        String five = GameForm.getBtn_5Text();
        String six = GameForm.getBtn_6Text();
        String seven = GameForm.getBtn_7Text();
        String eight = GameForm.getBtn_8Text();
        String nine = GameForm.getBtn_9Text();
        
        if(one != "" && two != "" && three != "" && four != "" && five != "" && six != ""
                && seven != "" && eight != "" && nine != ""){
            draws++;
            drawsFinal++;
            makeTieGameForm();
            if(!PlayerOne.equals("Player One")){
                saveScore();
                matchHistoryDB();
            }
        }
    }
    
    public void determinWhoWins(){
        String one = GameForm.getBtn_1Text();
        String two = GameForm.getBtn_2Text();
        String three = GameForm.getBtn_3Text();
        String four = GameForm.getBtn_4Text();
        String five = GameForm.getBtn_5Text();
        String six = GameForm.getBtn_6Text();
        String seven = GameForm.getBtn_7Text();
        String eight = GameForm.getBtn_8Text();
        String nine = GameForm.getBtn_9Text();
        
        if(one == "X" && two == "X" && three == "X")
            xWins();
        else if(four == "X" && five == "X" && six == "X")
            xWins();
        else if(seven == "X" && eight == "X" && nine == "X")
            xWins();
        else if(one == "X" && four == "X" && seven == "X")
            xWins();
        else if(two == "X" && five == "X" && eight == "X")
            xWins();
        else if(three == "X" && six == "X" && nine == "X")
            xWins();
        else if(one == "X" && five == "X" && nine == "X")
            xWins();
        else if(three == "X" && five == "X" && seven == "X")
            xWins();
        else if(one == "O" && two == "O" && three == "O")
            oWins();
        else if(four == "O" && five == "O" && six == "O")
            oWins();
        else if(seven == "O" && eight == "O" && nine == "O")
            oWins();
        else if(one == "O" && four == "O" && seven == "O")
            oWins();
        else if(two == "O" && five == "O" && eight == "O")
            oWins();
        else if(three == "O" && six == "O" && nine == "O")
            oWins();
        else if(one == "O" && five == "O" && nine == "O")
            oWins();
        else if(three == "O" && five == "O" && seven == "O")
            oWins();
        else
            tieGame();
    }
    
    public void setPlayersName(){
        if(nextTurn.equalsIgnoreCase("X"))
            playersTurn = PlayerOne;
        else
            playersTurn = PlayerTwo;
        
        g_form.setLbl_scoreText(PlayerOne + "'s score: "+ String.valueOf(playerOneCount) 
                + "                  " + playersTurn + "'s move                   "
                + PlayerTwo + "'s score: " + String.valueOf(playerTwoCount));
    }
    
    public void setScore(){
        g_form.setLbl_scoreText(PlayerOne + "'s score: "+ String.valueOf(playerOneCount) 
                + "                  " + playersTurn + "'s move                   "
                + PlayerTwo + "'s score: " + String.valueOf(playerTwoCount));
    }
    
    public void resetScore(){
/*
        g_form.setLbl_scoreText(PlayerOne + "'s score: "+ 0 
                + "                  " + playersTurn + "'s move                   "
                + PlayerTwo + "'s score: " + 0);
*/
        playerOneCount = 0;
        playerTwoCount = 0;
        draws = 0;
    }
    
    public void makeHighScoreForm(){
        hs_form = new HighscoreForm();
        hs_form.setTitle("HIGHSCORE");
        hs_form.setSize(800, 600);
        hs_form.setLocationRelativeTo(null);
        hs_form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        hs_form.setVisible(true);

        makeHighscoreTable(hs_form);
    }
    
    public void makeLoggedInHighscoreForm(){
        lhs_form = new LoggedInHighscoreForm();
        lhs_form.setTitle("HIGHSCORE");
        lhs_form.setSize(800, 600);
        lhs_form.setLocationRelativeTo(null);
        lhs_form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        lhs_form.setVisible(true);

        makeHighscoreTable(lhs_form);
        
        lhs_form.addListener(((e) -> {
            
            makeHistoryForm();
            lhs_form.dispose();

        }));
    }
    
    public void makeAdminHighscoreForm(){
        ahs_form = new AdminHighscoreForm();
        ahs_form.setTitle("HIGHSCORE");
        ahs_form.setSize(800, 600);
        ahs_form.setLocationRelativeTo(null);
        ahs_form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ahs_form.setVisible(true);
        
        makeHighscoreTable(ahs_form);
        
        ahs_form.addListener(((e) -> {
            try{
                String d_user = ahs_form.getTf_username();
                DbQuery.selectUserHib(d_user);
                DbQuery.deleteUserHib(d_user);
                System.out.println("You have deleted: " + d_user);
                ahs_form.dispose();
                makeAdminHighscoreForm();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(ahs_form, "PLAYER NOT FOUND");
            }
        }));

    }
    
    public void makeLoginForm(){
        l_form = new LoginForm();
        l_form.setTitle("LOGIN");
        l_form.setSize(300, 200);
        l_form.setLocationRelativeTo(null);
        l_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l_form.setVisible(true);
        
        l_form.addListener(((e) -> {
            String username = l_form.getTf_username();
            String password = convertPassMd5(l_form.getTf_password());
            if(!(username.equalsIgnoreCase("Admin") && password.equalsIgnoreCase(convertPassMd5("admin")))){
                try{
                    u = DbQuery.selectUserHib(username,password);
                    PlayerOne = username;
                    JOptionPane.showMessageDialog(l_form, "YOU HAVE LOGED IN SUCCESSFULLY");
                    makeLogedInMenuForm(u);
                    System.out.println(u);
                    l_form.dispose();
                    PlayerOne = username;
                    playerOneCountFinal = 0;
                    playerTwoCountFinal = 0;
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(l_form, "YOUR USERNAME OR PASSWORD WAS INCORRECT");
                    makeMenuForm();
                    l_form.dispose();
                }
            }else{
                u = new User(username);
                makeLogedInMenuForm(u);
                l_form.dispose();
            }
            
        }));
        
    }
    
    public String convertPassMd5(String pass) {
        String password = null;
        MessageDigest mdEnc;
        try {
          mdEnc = MessageDigest.getInstance("MD5");
          mdEnc.update(pass.getBytes(), 0, pass.length());
          pass = new BigInteger(1, mdEnc.digest()).toString(16);
          while (pass.length() < 32) {
            pass = "0" + pass;
          }
          password = pass;
        } catch (NoSuchAlgorithmException e1) {
          e1.printStackTrace();
        }
        return password;
}
    
    public void makeRegisterForm(){
        
        r_form = new RegisterForm();
        
        r_form.setTitle("REGISTER");
        //r_form.setSize(350, 250);
        r_form.setLocationRelativeTo(null);
        r_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        r_form.setVisible(true);
        
        r_form.addListener(((e) -> {
            
            if(!r_form.getTf_username().equalsIgnoreCase("Admin")){
                try{
                    DbQuery.selectUserHib(r_form.getTf_username());
                    JOptionPane.showMessageDialog(r_form, "USERNAME IS TAKEN, TRY AGAIN");
                    //if(DbQuery.selectEmailHib(r_form.getTf_email())==true)
                    //    JOptionPane.showMessageDialog(r_form, "EMAIL IS ALREADY USED");

                }
                catch(Exception ex1){
                    u = new User(r_form.getTf_username(),convertPassMd5(r_form.getTf_password()),r_form.getTf_email());
                    //DbQuery.insertUser(u);
                    try{
                        if(DbQuery.selectEmailHib(r_form.getTf_email())==true)
                            JOptionPane.showMessageDialog(r_form, "EMAIL IS ALREADY USED");
                        }
                    catch(Exception ex2){
                        if(ValidateEmail(r_form.getTf_email())==true){
                            DbQuery.createUserHib(u);
                            makeLoginForm();            
                            s = new Score(u.getId(), 0, 0, 0, 0);
                            DbQuery.createScoreHib(s, u);
                            r_form.dispose(); 
                        }else{
                            JOptionPane.showMessageDialog(r_form, "INVALID EMAIL, TRY AGAIN");
                        }
                    }
                }
            }else
                JOptionPane.showMessageDialog(r_form, "YOU CAN'T BE ADMIN!!!");
        }));
    }
    
    public boolean ValidateEmail(String email){
        boolean status;
        String email_pattern =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher=pattern.matcher(email);
        if(matcher.matches()){
            status=true;
        }else{
            status=false;
        }
        return status;
    }

    public void makeHighscoreTable(HighscoreForm hs_form){
        ArrayList<String> score_list = DbQuery.getHighscoreInfo();
        for(int i = 0;i<score_list.size()/5;i++){
            String wins = score_list.get(0+(i*5));
            String loses = score_list.get(1+(i*5));
            String draws = score_list.get(2+(i*5));
            String win_ratio = score_list.get(3+(i*5));
            String username = score_list.get(4+(i*5));
            Object[] row = {username, wins, loses, draws, win_ratio};
            DefaultTableModel model = (DefaultTableModel) hs_form.getTbl_highscore().getModel();
            model.addRow(row);
            hs_form.getTbl_highscore().setRowSorter(new TableRowSorter(model)); // int sortiranje - pogresno
            }
        System.out.println(hs_form.getProfile());
        
    }
    
    public void makeHighscoreTable(AdminHighscoreForm ahs_form){
        ArrayList<String> score_list = DbQuery.getHighscoreInfo();
        for(int i = 0;i<score_list.size()/5;i++){
            String wins = score_list.get(0+(i*5));
            String loses = score_list.get(1+(i*5));
            String draws = score_list.get(2+(i*5));
            String win_ratio = score_list.get(3+(i*5));
            String username = score_list.get(4+(i*5));
            Object[] row = {username, wins, loses, draws, win_ratio};
            DefaultTableModel model = (DefaultTableModel) ahs_form.getTbl_highscore().getModel();
            model.addRow(row);
            ahs_form.getTbl_highscore().setRowSorter(new TableRowSorter(model)); //int sortiranje - pogresno
            }
    }
    
    public void makeHighscoreTable(LoggedInHighscoreForm lhs_form){
        ArrayList<String> score_list = DbQuery.getHighscoreInfo();
        for(int i = 0;i<score_list.size()/5;i++){
            String wins = score_list.get(0+(i*5));
            String loses = score_list.get(1+(i*5));
            String draws = score_list.get(2+(i*5));
            String win_ratio = score_list.get(3+(i*5));
            String username = score_list.get(4+(i*5));
            Object[] row = {username, wins, loses, draws, win_ratio};
            DefaultTableModel model = (DefaultTableModel) lhs_form.getTbl_highscore().getModel();
            model.addRow(row);
            lhs_form.getTbl_highscore().setRowSorter(new TableRowSorter(model)); //int sortiranje - pogresno
            }
    }
    
    public void makeOnlineForm(){
        OnlineForm o_form = new OnlineForm();
        
        o_form.setTitle("ONLINE GAME");
        o_form.setSize(400, 100);
        o_form.setLocationRelativeTo(null);
        o_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        o_form.setVisible(true);
        
        o_form.addListener(
                (e1) -> {
                    //System.out.println("Klik na dugme CREATE GAME");
                    makeCreateGameForm();
                    o_form.dispose();
                    
        },(e2) -> {
            //System.out.println("Klik na dugme JOIN GAME);
            makeJoinGameForm();
            o_form.dispose();
            
        });
    }
    
    public void makeJoinGameForm (){
        JoinGameForm j_form = new JoinGameForm();
        
        j_form.setTitle("CONNECTION");
        j_form.setSize(400, 200);
        j_form.setLocationRelativeTo(null);
        j_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j_form.setVisible(true);
        
        j_form.addListener(((e) -> {
            String ip = j_form.getTf_ip();
            System.out.println("IP adresa je: "+ip);
            int port = j_form.getTf_port();
            System.out.println("Port je: "+port);
            try {
                System.out.println("IP "+ip);
                System.out.println("PORT "+port);
                Socket socket = new Socket(ip, port);
                Client client = new Client(socket, (message)->System.out.println("User Two: "+message));
                System.out.println("Saljem potez");
                client.sendMessage(u.getUsername());
                System.out.println(u);
                j_form.dispose();
                m_form.dispose();
                u2=u;
                makeOnlineGameForm(u1, u2, socket);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }));
    }
    
    public void makeCreateGameForm (){
        CreateGameForm c_form = new CreateGameForm();
        
        c_form.setTitle("CREATE GAME");
        c_form.setSize(400, 200);
        c_form.setLocationRelativeTo(null);
        c_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c_form.setVisible(true);
        //String ip = IPAdress().toString().substring(IPAdress().toString().lastIndexOf("/") + 1);
        String ip = "localhost";
        c_form.setLb_myIP(ip);
        
        c_form.addListener(((ActionEvent e) -> {
            int port = c_form.getTf_port();
            System.out.println("Port je: " + port);
            try {
                Server server = new Server();
                Socket socket = server.createServer(port, u);
                System.out.println(u);
                c_form.dispose();
                m_form.dispose();
                u1=u;
                makeOnlineGameForm(u1, u2, socket);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));
    }
    
    public InetAddress IPAdress(){
        InetAddress ip = null;
	  try {

		ip = InetAddress.getLocalHost();
		//System.out.println("Current IP address : " + ip.getHostAddress());

	  } catch (UnknownHostException e) {

		e.printStackTrace();

	  }
        return ip;
    }
    
    public void matchHistory(String field){
        match.add(field);
        System.out.println(match);
    }
    
    public void matchHistoryDB(){
        System.out.println(score);
        for(int i=5;i<9;i++){
            if(match.size()<9)
                match.add("");
        }
        h = new History(u,PlayerTwo, match.get(0), match.get(1), match.get(2), 
            match.get(3), match.get(4), match.get(5), match.get(6), 
            match.get(7), match.get(8), score);

        createHistoryHib(h,u,PlayerTwo, match.get(0), match.get(1), match.get(2), 
            match.get(3), match.get(4), match.get(5), match.get(6), 
            match.get(7), match.get(8), score);
    }
    
    public void saveScore(){
        //  Skor iz baze
        if(!PlayerOne.equals("Player One")){
            Score su = selectScoreHib(u.getId());

            winRatio = ((playerOneCountFinal+su.getWins())+0.5*(drawsFinal+su.getDraws()))/
                    ((playerOneCountFinal+su.getWins())+(drawsFinal+su.getDraws())+(playerTwoCountFinal+su.getLoses()));
            winRatio = Math.round(winRatio*10000);
            winRatio = winRatio/100;

            //  Pravim skor za igraca tako sto trenutni skor sabiram sa skorom izvucenim iz baze
            s = new Score(u.getId(), playerOneCountFinal+su.getWins(), drawsFinal+su.getDraws(), playerTwoCountFinal+su.getLoses(),winRatio);
            DbQuery.createScoreHib(s,u);
            playerOneCountFinal = 0;
            playerTwoCountFinal = 0;
            drawsFinal = 0;
        }
    }
    
    public void makeHistoryForm(){
        h_form = new HistoryForm();
        h_form.setTitle("HISTORY");
        //h_form.setSize(300, 600);
        h_form.setLocationRelativeTo(null);
        h_form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        h_form.setVisible(true);

        makeHistoryTable(h_form);
        
        h_form.addListener(((e) -> {
            
            makePreviewForm();
            h_form.dispose();

        }));
    }
    
    public void makeHistoryTable(HistoryForm h_form){
        //String p1 = DbQuery.selectScoreHib(u.getId());
        ArrayList<String> history_list = DbQuery.getHistoryInfo(u.getId());
        for(int i = 0;i<history_list.size()/4;i++){
            String p1 = u.getUsername();
            System.out.println("p1 "+p1);            
            String p2 = history_list.get(1+(i*4));
            System.out.println("p2 "+p2);
            String g = history_list.get(2+(i*4));
            System.out.println("game "+g);
            String game_id = history_list.get(3+(i*4));
            System.out.println("ID "+game_id);
            Object[] row = {game_id, p1, p2, g};
            DefaultTableModel model = (DefaultTableModel) h_form.getTbl_history().getModel();
            model.addRow(row);
            }
    }
    
    public void makePreviewForm(){
        p_form = new PreviewForm();
        p_form.setTitle("HISTORY");
        //h_form.setSize(300, 600);
        p_form.setLocationRelativeTo(null);
        p_form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        p_form.setVisible(true);
        p_form.setLb_score("X - " + h_form.getP1() + "                           "
                + "O - " + h_form.getP2());
        int gameID = Integer.parseInt(h_form.getProfile());
       // System.out.println("BROJ PARTIJE JE: "+gameID);
        match = DbQuery.getGameHistoryInfo(Integer.parseInt(h_form.getProfile()));
            String move1 = match.get(4);
            String move2 = match.get(5);
            String move3 = match.get(6);
            String move4 = match.get(7);
            String move5 = match.get(0);
            String move6 = match.get(1);
            String move7 = match.get(2);
            String move8 = match.get(3);
            String move9 = match.get(8);
            move_counter = 1;
            //System.out.println("Brojac poteza: " + move_counter);
         if(gameID==10){
                JOptionPane.showMessageDialog(p_form, "CHOOSE GAME");
                makeHistoryForm();
                p_form.dispose();
            }else{
            p_form.addListener(((e) -> {
                if(p_form.getBtn_next().equals("NEXT")){
                    switch (move_counter){
                        case 1: historyLogic(move1);
                                break;
                        case 2: historyLogic(move2);
                                break;
                        case 3: historyLogic(move3);
                                break;
                        case 4: historyLogic(move4);
                                break;
                        case 5: historyLogic(move5);
                                break;    
                        case 6: 
                            if(move6.equals("")){
                                if(!h_form.getGame().equalsIgnoreCase("DRAW"))
                                    p_form.setLb_score("You " + h_form.getGame());
                                else
                                p_form.setLb_score(h_form.getGame());
                            }
                            historyLogic(move6);
                            break;   
                        case 7: historyLogic(move7);
                            if(move7.equals("")){
                                if(!h_form.getGame().equalsIgnoreCase("DRAW"))
                                    p_form.setLb_score("You " + h_form.getGame());
                                else
                                    p_form.setLb_score(h_form.getGame());
                            }
                                break;
                        case 8: 
                            if(move8.equals("")){
                                if(!h_form.getGame().equalsIgnoreCase("DRAW"))
                                    p_form.setLb_score("You " + h_form.getGame());
                                else
                                    p_form.setLb_score(h_form.getGame());
                            }
                            historyLogic(move8);
                            break; 
                        case 9: 
                            if(move9.equals("")){
                                if(!h_form.getGame().equalsIgnoreCase("DRAW"))
                                    p_form.setLb_score("You " + h_form.getGame());
                                else
                                    p_form.setLb_score(h_form.getGame());
                            }
                            historyLogic(move9);
                            break;
                        case 10: 
                            if(!h_form.getGame().equalsIgnoreCase("DRAW"))
                                p_form.setLb_score("You " + h_form.getGame());
                            else
                                p_form.setLb_score(h_form.getGame());
                        break;
                        default:p_form.setL1("");
                                p_form.setL2("");
                                p_form.setL3("");
                                p_form.setL4("");
                                p_form.setL5("");
                                p_form.setL6("");
                                p_form.setL7("");
                                p_form.setL8("");
                                p_form.setL9("");
                                p_form.dispose();
                                makeHistoryForm();
                                move_counter = 0;
                                break;
                    }
                    move_counter++;
                    if(p_form.getLb_score().contains("DRAW") || p_form.getLb_score().contains("LOST") || p_form.getLb_score().contains("WON")){
                            match.clear();
                            p_form.setBtn_next("CLOSE");
                        }
            }else{
                p_form.dispose();
                makeHistoryForm();
            }
            }));
        }
    }
    
    public void historyLogic(String move){
        if(move.equals("1X"))
                p_form.setL1("X");
            else if(move.equals("2X"))
                p_form.setL2("X");
            else if(move.equals("3X"))
                p_form.setL3("X");
            else if(move.equals("4X"))
                p_form.setL4("X");
            else if(move.equals("5X"))
                p_form.setL5("X");
            else if(move.equals("")){
                if(!h_form.getGame().equalsIgnoreCase("DRAW"))
                    p_form.setLb_score("You " + h_form.getGame());
                else
                    p_form.setLb_score(h_form.getGame());
            }
            else if(move.equals("6X"))
                p_form.setL6("X");
            else if(move.equals("7X"))
                p_form.setL7("X");
            else if(move.equals("8X"))
                p_form.setL8("X");
            else if(move.equals("9X"))
                p_form.setL9("X");
            else if(move.equals("1O"))
                p_form.setL1("O");
            else if(move.equals("2O"))
                p_form.setL2("O");
            else if(move.equals("3O"))
                p_form.setL3("O");
            else if(move.equals("4O"))
                p_form.setL4("O");
            else if(move.equals("5O"))
                p_form.setL5("O");
            else if(move.equals("6O"))
                p_form.setL6("O");
            else if(move.equals("7O"))
                p_form.setL7("O");
            else if(move.equals("8O"))
                p_form.setL8("O");
            else if(move.equals("9O"))
                p_form.setL9("O");
    }
    
}