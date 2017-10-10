
package appenglish;
import static appenglish.ChangeWord.connection;
import static appenglish.ChangeWord.connectionUrl;
import static appenglish.ChangeWord.resultSet;
import static appenglish.ChangeWord.statement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javax.swing.GroupLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import com.microsoft.sqlserver.jdbc.*;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.text.*;
public class Statistic extends Application{
    public  String connectionUrl = "jdbc:sqlserver://localhost:1433;" +  
     "databaseName=AppEnglish;integratedSecurity=true;"; 
    public  Connection connection = null;  
    public Statement statement = null;   
    public  ResultSet resultSet = null;  
    public  String pathToImage = "";
    public PreparedStatement prepsInsertProduct = null; 
    static List<Integer>leanredWord = new ArrayList<Integer>();
    static List<String>activeDate = new ArrayList<String>();
    static List<Integer>reviewWord = new ArrayList<Integer>();
    public Statistic()
    {
        
    }
    public  void readProgressDb() throws ParseException
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sqlquery = "SELECT * FROM dbo.learningProgress ORDER BY activeDate DESC";
            connection = DriverManager.getConnection(connectionUrl);
            statement = connection.createStatement();
            resultSet =  statement.executeQuery(sqlquery);
            while(resultSet.next())
            {
                leanredWord.add(resultSet.getInt(1));
                activeDate.add(resultSet.getString(3));
                reviewWord.add(resultSet.getInt(2));
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public void start(Stage stage) throws ParseException 
    {
        stage.setTitle("Biểu đồ học tập");
        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Ngày");
        yAxis.setLabel("Từ");
        //creating the chart
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);

        lineChart.setTitle("Quá trình học");
        //defining a series
        XYChart.Series seriesLearn = new XYChart.Series();
        seriesLearn.setName("Quá trinh học từ");
        for(int i = (leanredWord.size() > 7 ? 6 : leanredWord.size() - 1); i >= 0; i --)
        {
            DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
            Date date = (Date) parser.parse(activeDate.get(i));
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
            seriesLearn.getData().add(new XYChart.Data(formatter.format(date), leanredWord.get(i)));
        }
        XYChart.Series seriesReivew = new XYChart.Series();
        seriesReivew.setName("Quá trình ôn từ");
        for(int i = (reviewWord.size() > 7 ? 6 : reviewWord.size() - 1); i >= 0; i --)
        {
            DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
            Date date = (Date) parser.parse(activeDate.get(i));
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
            seriesReivew.getData().add(new XYChart.Data(formatter.format(date),reviewWord.get(i)));
        }
        
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(seriesLearn);
        lineChart.getData().add(seriesReivew);
        
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]) throws ParseException
    {
        Statistic handle = new Statistic();
        handle.readProgressDb();
        launch(args);
    }
}
