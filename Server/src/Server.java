import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server implements Runnable{
    private BufferedWriter writer;
    private BufferedReader reader;
    ArrayList <Server> servers;
    Server(Socket socket, ArrayList<Server> servers){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            writer = new BufferedWriter(osw);

            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(isr);
            this.servers = servers;
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    String input;
    @Override
    public void run() {
        try {
            ArrayList<Student> students = new ArrayList<>();
            ArrayList<Books> books = new ArrayList<>();
            ArrayList<Lr> lrs =new ArrayList<>();
            while ((input = reader.readLine())!= null) {
                try {

                    if ((input.equals("IssueBook"))) {
                        String name = reader.readLine();
                        String id = reader.readLine();
                        String book_id = reader.readLine();
                        String date = reader.readLine();
                        File file = new File("books1.txt");
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextInt() ) {
                            int bookid = scanner.nextInt();
                            int quantity=scanner.nextInt();
                            System.out.println(book_id);
                            System.out.println(quantity);
                            Books b = new Books(bookid, quantity);
                            books.add(b);
                            if (bookid==Integer.parseInt(book_id)&&quantity>0)
                            {
                                    File file1 = new File("issuebook.txt");
                                    BufferedWriter bw = new BufferedWriter(new FileWriter(file1, true));
                                    String temp =  id + " " + book_id + " " + " " + date;
                                    bw.write(temp);
                                    bw.write("\n");
                                    bw.close();
                                for (Books b1 : books) {
                                     if (bookid == b1.bid) {
                                            b1.bq = b1.bq-1;
                                        }
                                    }
                                writer.write("bookissued\n");
                            } else writer.write("failed\n");
                            writer.close();
                           writer.flush();
                        }
                        for (Books b2 : books) {
                            File file2 = new File("books.txt");
                            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2, true));
                            bufferedWriter.write( b2.bid + " " + b2.bq);
                            bufferedWriter.write("\n");
                            bufferedWriter.close();
                        }

                        scanner.close();

                    } else if ((input.equals("ReturnBook"))) {
                        String name = reader.readLine();
                        String id = reader.readLine();
                        String book_id = reader.readLine();
                        String date = reader.readLine();
                        File file = new File("issuebook.txt");
                        Scanner scanner=new Scanner(file);
                        while (scanner.hasNext()){
                            String name1 = scanner.next();
                            int id1 = scanner.nextInt();
                            int bookid=scanner.nextInt();
                            String date1=scanner.next();
                            if (id1==Integer.parseInt(id)&&bookid==Integer.parseInt(book_id)){
                                remove("issuebook.txt",id,2," ");
                            }
                        }
                        writer.write("done\n");
                        writer.flush();
                    }
                    else if ((input.equals("AddBook"))) {
                        String name = reader.readLine();
                        String id = reader.readLine();
                        String quantity= reader.readLine();
                        System.out.println(id+name+quantity);
                        File file = new File("books1.txt");
                        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
                        System.out.println(id+name+quantity);
                        bufferedWriter.write("\n");
                        bufferedWriter.write( id + " " + quantity );
                        writer.write("Done\n");
                        writer.flush();
                        bufferedWriter.close();

                    }else if (input.equals("Login")) {
                        System.out.println("done");
                        File file = new File("login.txt");
                        String username = reader.readLine();
                        System.out.println(username);
                        String password = reader.readLine();
                        System.out.println(password);
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNext()) {
                            String s = scanner.next();
                            String b = scanner.next();
                            System.out.println(s);
                            System.out.println(b);
                            int count = 0;
                            if (s.equals(username) && b.equals(password)) {

                                try {
                                    writer.write("Loginsuccessful");
                                    writer.newLine();
                                    count++;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (count == 0) {
                                System.out.println(count);
                                writer.write("Loginusuccessful");
                                writer.newLine();
                            }
                        }
                        writer.flush();

                    }
                    else if (input.equals("AdminLogin")) {
                        File file = new File("AdminLogin.txt");
                        String username = reader.readLine();
                        System.out.println(username);
                        String password = reader.readLine();
                        System.out.println(password);
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNext()) {
                            String s = scanner.next();
                            String b = scanner.next();
                            int count = 0;
                            if (s.equals(username) && b.equals(password)) {
                                try {
                                    writer.write("Loginsuccessful");
                                    writer.newLine();
                                    count++;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (count == 0) {
                                System.out.println(count);
                                writer.write("Loginusuccessful");
                                writer.newLine();
                            }
                        }
                        writer.flush();
                    }
                    else if (input.equals("Remove")) {
                        System.out.println("doneremove");
                        File file = new File("login.txt");
                        String name = reader.readLine();
                        String id = reader.readLine();
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNext()) {
                            String s = scanner.next();
                            String b = scanner.next();
                            int count = 0;
                            if (s.equals(id) ) {
                                try {
                                    remove("login.txt",id,1," ");
                                    writer.write("done");
                                    writer.newLine();
                                    writer.flush();
                                    count++;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (count == 0) {
                                System.out.println(count);
                                writer.write("failed");
                                writer.newLine();
                                writer.flush();
                            }
                        }
                        writer.flush();
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    class Student{
        String name ;
        int id ;
        int bookid;
        String date;
        public Student(String name, int id, int bookid, String date) {
            this.name = name;
            this.id = id;
            this.bookid = bookid;
            this.date = date;
        }
    }
    class Books{
        int bid;
        int bq;
        public Books( int bid, int bq) {
            this.bid = bid;
            this.bq = bq;
        }
    }
    class Lr{
        String name;
        String id;
        public Lr(String name, String id) {
            this.name = name;
            this.id = id;
        }
    }
    public void remove(String filepath,String term,int pos,String delim){
       int pos1=pos-1;
       String temp="temp.txt";
       File oldFile=new File(filepath);
       File newFile = new File(temp);
       String currentline;
       String data[];
       try {
           FileWriter fw = new FileWriter(temp,true);
           BufferedWriter bw = new BufferedWriter(fw);
           PrintWriter pw = new PrintWriter(bw);
           FileReader fr = new FileReader(filepath);
           BufferedReader br = new BufferedReader(fr);
           while ((currentline =br.readLine())!=null){
               data=currentline.split(delim);
               if(!(data[pos1].equalsIgnoreCase(term))){
                   pw.println(currentline);
               }
           }
           pw.flush();
           pw.close();
           fr.close();
           br.close();
           bw.close();
           fw.close();
           oldFile.delete();
           File flag=new File(filepath);
           newFile.renameTo(flag);

       } catch (IOException e) {
           e.printStackTrace();
       }

    }
}

