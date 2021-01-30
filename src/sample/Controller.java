package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
public class Controller {
    @FXML
  public Button login,returnbook,searchbook,addbooks,issuebook,back,ic,ab,rb;
    @FXML
    javafx.scene.control.TextField user,pass,name,id,idate,bookid,name1,id1,idate1,bookid1;
    BufferedReader reader = null;
    BufferedWriter writer = null;

    public Controller() {
        try {
            Socket socket = new Socket("127.0.0.1", 3005);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            writer = new BufferedWriter(outputStreamWriter);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(inputStreamReader);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
   @FXML
    public void loginClick() throws IOException {
       String username = user.getText();
       String password = pass.getText();
       if (username.equals("") || password.equals("")) {
           JOptionPane.showMessageDialog(null, "Please fill up all the blanks");
       }


       try{
           writer.write("Login\n");
           writer.write(username);
           writer.newLine();
           writer.write(password);
           writer.newLine();
           writer.flush();
           String msg = reader.readLine();
           System.out.println(msg);
           if (msg.equals("Loginsuccessful")){
               Parent root = FXMLLoader.load(getClass().getResource("library.fxml"));
               Stage window = (Stage) login.getScene().getWindow();
               window.setScene(new Scene(root,600,575));
           }
           else
               JOptionPane.showMessageDialog(null, "Incorrect username and password");

       }catch (NumberFormatException | IOException e){
           e.printStackTrace();
       }
   }
   @FXML
    public void addbooksClick() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("addbooks.fxml"));
       Stage window = (Stage) addbooks.getScene().getWindow();
       window.setScene(new Scene(root,600,575));

   }
   @FXML public void issuebookclick() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("issuebook.fxml"));
       Stage window = (Stage) issuebook.getScene().getWindow();
       window.setScene(new Scene(root,600,575));
   }
   @FXML
   public void iclick(){
       String n= name.getText();
       String i =id.getText();
       String date = idate.getText();
       String book = bookid.getText();
       try {
           writer.write("IssueBook\n");
           writer.write(n);
           writer.newLine();
           writer.write(i);
           writer.newLine();
           writer.write(date);
           writer.newLine();
           writer.write(book);
           writer.newLine();
           writer.flush();
           String msg = reader.readLine();


       }catch (IOException e){

       }

   }
   @FXML
    public void returnbookclick() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("returnbook.fxml"));
       Stage window = (Stage) returnbook.getScene().getWindow();
       window.setScene(new Scene(root,600,575));

   }
   @FXML
    public void searchbookclick() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("searchbook.fxml"));
       Stage window = (Stage) searchbook.getScene().getWindow();
       window.setScene(new Scene(root,600,575));

   }
   @FXML
    public void retrunclick() throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
       Stage window = (Stage) login.getScene().getWindow();
       window.setScene(new Scene(root,600,575));

    }

    @FXML
    public void adbblick(){

    }
    @FXML
    public void rbclick(){

    }

   }

