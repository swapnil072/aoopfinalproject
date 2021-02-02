package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Controller {
    @FXML
  public Button login,returnbook,searchbook,addbooks,issuebook,back,ic,ab,rb,r1,back1,back2,back3,soh,sbe,soe,back5,back6,ll,al,h1,h2,login1,back8,back9,dlt,h3;
    @FXML
    javafx.scene.control.TextField user,pass,name,id,idate,bookid,name1,id1,idate1,bookid1,a1,a2,a3,se1,user1,pass1,lname,lid;
    @FXML
    TextArea ta,soe4,bl1;
    BufferedReader reader = null;
    BufferedWriter writer = null;
    public Controller() {
        try {
            Socket socket = new Socket("127.0.0.1", 3006);
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
       else

       {
           writer.write("Login\n");
           writer.write(username);
           writer.newLine();
           writer.write(password);
           writer.newLine();
           writer.flush();
           String msg = reader.readLine();
           if (msg.equals("Loginsuccessful")){

               Parent root = FXMLLoader.load(getClass().getResource("library.fxml"));
               Stage window = (Stage) login.getScene().getWindow();
               window.setScene(new Scene(root,554,449));
           }
           if (msg.equals("Loginusuccessful")) {
               JOptionPane.showMessageDialog(null, "Incorrect username and password");
           }
       }

       user.setText("");
       pass.setText("");
   }
    public void loginClick1(ActionEvent actionEvent) throws IOException {
        String username = user1.getText();
        String password = pass1.getText();
        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill up all the blanks");
        }
        else {
            writer.write("AdminLogin\n");
            writer.write(username);
            writer.newLine();
            writer.write(password);
            writer.newLine();
            writer.flush();
            String msg = reader.readLine();
            if (msg.equals("Loginsuccessful")){

                Parent root = FXMLLoader.load(getClass().getResource("Adminpage.fxml"));
                Stage window = (Stage) login1.getScene().getWindow();
                window.setScene(new Scene(root,554,449));
            }
            if (msg.equals("Loginusuccessful")) {
                JOptionPane.showMessageDialog(null, "Incorrect username and password");
            }
        }

        user1.setText("");
        pass1.setText("");
    }
    @FXML
    public void dltclik() throws IOException {
        String name=lname.getText();
        String id = lid.getText();
        if (name.equals("")||id.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill up all the blanks");
        }
        else {
            writer.write("Remove\n");
            writer.write(name);
            writer.newLine();
            writer.write(id);
            writer.newLine();
            writer.flush();
            String msg = reader.readLine();
            if (msg.equals("done")) {
                JOptionPane.showMessageDialog(null, "Librarian Removed");
            }
            if (msg.equals("failed")){

                JOptionPane.showMessageDialog(null, "Failed");
            }
        }
        lname.setText("");
        lid.setText("");

    }

    @FXML
   public void iclick  () throws IOException {
       String n= name.getText();
       String i =id.getText();
       String book = bookid.getText();
       String date = idate.getText();
       if (n.equals("")||i.equals("")||book.equals("")||date.equals("")) {
           JOptionPane.showMessageDialog(null, "Please fill up all the blanks");
       }
       else {
           writer.write("IssueBook\n");
           writer.write(n);
           writer.newLine();
           writer.write(i);
           writer.newLine();
           writer.write(book);
           writer.newLine();
           writer.write(date);
           writer.newLine();
           writer.flush();
           String msg = reader.readLine();
           if (msg.equals("bookissued")) {
               JOptionPane.showMessageDialog(null, "Book Isuued");
           }
           if(msg.equals("failed")){
               JOptionPane.showMessageDialog(null, "This book is not available");
           }
           name.setText("");
           id.setText("");
           bookid.setText("");
           idate.setText("");
       }
   }
    @FXML
    public void addbclick() throws IOException{
        String id= a1.getText();
        String name =a2.getText();
        String quantity = a3.getText();
        if (id.equals("")||name.equals("")||quantity.equals("")) {
            JOptionPane.showMessageDialog(null, "Please fill up all the blanks");
        }
        else {
            writer.write("AddBook\n");
            writer.write(name);
            writer.newLine();
            writer.write(id);
            writer.newLine();
            writer.write(quantity);
            writer.newLine();
            writer.flush();
            String msg = reader.readLine();
            if (msg.equals("Done")) {
                JOptionPane.showMessageDialog(null, "Book Added");
            }
            a1.setText("");
            a2.setText("");
            a3.setText("");

        }
    }
    @FXML
    public void rbclick (){
        try {
            String n= name1.getText();
            String i =id1.getText();
            String date = idate1.getText();
            String book = bookid1.getText();
            if (n.equals("")||i.equals("")||book.equals("")||date.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill up all the blanks");
            }
            else {
                writer.write("ReturnBook\n");
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
                if (msg.equals("Done")) {
                    JOptionPane.showMessageDialog(null, "Book Returned");
                }
                writer.flush();

            }
            name1.setText("");
            id1.setText("");
            idate1.setText("");
            bookid1.setText("");
        }catch (IOException e){
        }
    }
    public void r1click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("library.fxml"));
        Stage window = (Stage) r1.getScene().getWindow();
        window.setScene(new Scene(root,554,449));

    }
    @FXML
    public void retrunclick1() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("library.fxml"));
        Stage window = (Stage)back1.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
    @FXML
    public void retrunclick2() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("library.fxml"));
        Stage window = (Stage)back2.getScene().getWindow();
        window.setScene(new Scene(root,554,499));
    }
    @FXML
    public void retrunclick3() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("library.fxml"));
        Stage window = (Stage)back3.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
    @FXML
    public void retrunclick5() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("library.fxml"));
        Stage window = (Stage)back5.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }

    public void sbeclick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sbe.fxml"));
        Stage window = (Stage)sbe.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
@FXML
    public void soeclick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("soe.fxml"));
        Stage window = (Stage)soe.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
    public void sohclick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("soh.fxml"));
        Stage window = (Stage)soh.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
    public void retrunclick6(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("library.fxml"));
        Stage window = (Stage)back6.getScene().getWindow();
        window.setScene(new Scene(root,554,449));

    }
    public void alclick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Stage window = (Stage) al.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }

    public void llclick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LibrarianPage.fxml"));
        Stage window = (Stage) ll.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
    public void h1click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage window = (Stage) h1.getScene().getWindow();
        window.setScene(new Scene(root,554,449));

    }


    public void h2click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage window = (Stage) h2.getScene().getWindow();
        window.setScene(new Scene(root,554,449));

    }
    public void retrunclick8(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("library.fxml"));
        Stage window = (Stage)back8.getScene().getWindow();
        window.setScene(new Scene(root,554,449));

    }

    public void retrunclick9(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("library.fxml"));
        Stage window = (Stage)back9.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
    @FXML
    public void searchbookclick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Book.fxml"));
        Stage window = (Stage)searchbook.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
    @FXML
    public void retrunclick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LibrarianPage.fxml"));
        Stage window = (Stage)back.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
    @FXML
    public void addbooksClick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addbooks.fxml"));
        Stage window = (Stage) addbooks.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
    @FXML public void issuebookclick() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("issuebook.fxml"));
            Stage window = (Stage) issuebook.getScene().getWindow();
            window.setScene(new Scene(root,554,449));
        }catch (IOException e){
        }
    }
    @FXML
    public void returnbookclick() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("returnbook.fxml"));
        Stage window = (Stage) returnbook.getScene().getWindow();
        window.setScene(new Scene(root,554,449));

    }

    public void h3click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage window = (Stage) h3.getScene().getWindow();
        window.setScene(new Scene(root,554,449));
    }
}

