import org.json.JSONObject;
import java.io.*;
import java.net.Socket;

public class MyServer extends Thread{

    Socket socket;
    static DataOutputStream dout;
    static DataInputStream din;
    static String s;
    File file;
    public MyServer(Socket socket)
    {
        this.socket=socket;
    }
   boolean check()
    {
        file=new File("RSADatabase.db");
      return file.exists() ;
    }
    public void run()
    {
        try {

           dout=new DataOutputStream(socket.getOutputStream());

           din = new DataInputStream(socket.getInputStream());

           while(true) {

               System.out.println("in the  while loop");

               System.out.println("socket found");


               s=din.readUTF();
               if(check())
               {
                   System.out.println("exits");
               }
               else
               {
                   System.out.println("doesnot exists");
               }
               DataBaseHandler dataBaseHandler=new DataBaseHandler();
               JSONObject myResponse = new JSONObject(s);
               if(myResponse.getString("code").equals("504"))
               {

                  String returned=dataBaseHandler.login(myResponse);

                  if(returned.equals("true"))
                  {
                      System.out.println("*******your username and pass is correct*******");
                      dout.writeUTF("successful");

                    new LoggedIn();
                  }
                  else
                  {
                      System.out.println("*********sorry did not match*******");
                      dout.writeUTF("unsuccessful");
                  }
               }
               else if(myResponse.getString("code").equals("400"))
               {
//                   System.out.println("welcome to thee database my son"+myResponse.getString("username"));
//                   System.out.println("u r destroyed now i know yor password"+myResponse.getString("password"));
                   String returned=dataBaseHandler.signup(myResponse);
                   if(returned.equals("successful"))
                   {
                       System.out.println("*******updated bitch*******");
                       dout.writeUTF("successful");
                      new LoggedIn();
                   }
                   else
                   {
                       System.out.println("*********sorry not updated*******");
                       dout.writeUTF("unsuccessful");
                   }
               }

                if(s.equals("exit"))
                {
                    break;
                }


           }
            dout.close();
            din.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try{
                socket.close();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

    }

}
