import org.json.JSONObject;

import java.sql.*;

public class DataBaseHandler {
    static  public Connection con;
   static public Statement stmt;
  static ResultSet  resultSet;
  static  public PreparedStatement pstmt;

    DataBaseHandler()
    {
        try {
            System.out.println("in the database constructr");

            con=DriverManager.getConnection("jdbc:sqlite:RSADatabase.db");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static String UpdateData(JSONObject response) {
        try {
            stmt=con.createStatement();
            int z=stmt.executeUpdate("insert into userdata(username,productname,price,productdesc,imagedesc,phone,lattitude,longitude) values('" + response.getString("username") + "','" + response.getString("productname") + "','" + response.getString("price") + "','" + response.getString("productdesc")+ "','" + response.getString("imagedes")+"','" +response.getString("number")+"','" +response.getString("lattitude") +"','" +response.getString("longitude")+"')");
            if(z==0)
            {return "unsuccessful";}
            else
            {
                return "successful";
            }

        } catch (Exception e) {
            System.out.println("exceptoin generated"+e.getMessage());
           return "unsuccessful";
        }

    }
    public static String image(int i) throws SQLException {
//
//        resultSet = stmt.executeQuery("select imagedesc from userdata");
//        String zz="error";
//
//        System.out.println("image k ndar");
//        while (resultSet.next())
//        {
//            if((i+"").equals(resultSet.getString(1)))
//            {zz=resultSet.getString(6);
//            break;}
//        }
////        while(resultSet.next())
//        {
//            zz=resultSet.getString(1);
//        }
        stmt=con.createStatement();
        String query=String.format("select imagedesc from userdata where id = %s",i+"");
        resultSet=stmt.executeQuery(query);
        String zz="error";
       // System.out.println("image k ndar");
      //  System.out.println("id is++++++++++++++++++++++++++++++++++++++++++++++ "+i);
        while(resultSet.next())
        {
            zz=resultSet.getString(1);
          //  System.out.println("///////////////////////////////////id is "+i);
            //System.out.println("**************************************************************image desc is"+zz);
        }

        return zz;
    }


    public static String productname(int i) throws SQLException {

        stmt=con.createStatement();
        String query=String.format("select productname from userdata where id = %s",i+"");
        resultSet=stmt.executeQuery(query);
        String zz="error";
      //  System.out.println("product k ndar");
       // System.out.println("id is++++++++++++++++++++++++++++++++++++++++++++++ "+i);
        while(resultSet.next())
        {
            zz=resultSet.getString(1);
          //  System.out.println("///////////////////////////////////id is "+i);
          //  System.out.println("**************************************************************product name is"+zz);
        }

        return zz;
    }

    public static String productdesc(int i) throws SQLException {

        stmt=con.createStatement();
        String query=String.format("select productdesc from userdata where id = %s",i+"");
        resultSet=stmt.executeQuery(query);
        String zz="error";

        while(resultSet.next())
        {
            zz=resultSet.getString(1);

        }

        return zz;
    }
    public static String price(int i) throws SQLException {

        stmt=con.createStatement();
        String query=String.format("select price from userdata where id = %s",i+"");
        resultSet=stmt.executeQuery(query);
        String zz="error";

        while(resultSet.next())
        {
            zz=resultSet.getString(1);

        }

        return zz;
    }
    public static String renter(int i) throws SQLException {

        stmt=con.createStatement();
        String query=String.format("select username from userdata where id = %s",i+"");
        resultSet=stmt.executeQuery(query);
        String zz="error";

        while(resultSet.next())
        {
            zz=resultSet.getString(1);

        }

        return zz;
    }
    public static String phone(int i) throws SQLException {

        stmt=con.createStatement();
        String query=String.format("select phone from userdata where id = %s",i+"");
        resultSet=stmt.executeQuery(query);
        String zz="error";

        while(resultSet.next())
        {
            zz=resultSet.getString(1);

        }

        return zz;
    }

    public static String lattitude(int i) throws SQLException {

        stmt=con.createStatement();
        String query=String.format("select lattitude from userdata where id = %s",i+"");
        resultSet=stmt.executeQuery(query);
        String zz="error";

        while(resultSet.next())
        {
            zz=resultSet.getString(1);

        }

        return zz;
    }
    public static String longitude(int i) throws SQLException {

        stmt=con.createStatement();
        String query=String.format("select longitude from userdata where id = %s",i+"");
        resultSet=stmt.executeQuery(query);
        String zz="error";

        while(resultSet.next())
        {
            zz=resultSet.getString(1);

        }

        return zz;
    }
    public static String countrows() throws SQLException {
        stmt=con.createStatement();
        resultSet=stmt.executeQuery("select count(*) from userdata");
        String z="error";
        while(resultSet.next())
        {
            z=resultSet.getString(1);
            System.out.println("z is count is"+z);
        }
        return z;

    }


    public String login(JSONObject response) throws Exception {
        stmt=con.createStatement();
       resultSet=stmt.executeQuery("select * from users");
      while(resultSet.next())
      {
//          System.out.println("pass is"+resultSet.getString(3)+"   response pass is"+response.getString("password"));
//          System.out.println("username is"+resultSet.getString(2)+"    responce username is"+response.getString("username"));
          if((resultSet.getString(2).equals(response.getString("username")))&&(resultSet.getString(3).equals(response.getString("password"))))
          {
              System.out.println("gotcha");
              return "true";
          }
      }
        return "false";
    }
    public String signup(JSONObject response) throws Exception {
        stmt=con.createStatement();
        String mobile=response.getString("mobile");
        String username=response.getString("username");
        String password=response.getString("password");
        String email=response.getString("email");
        int result=0;
        try {
           result = stmt.executeUpdate("insert into users values('" + mobile + "','" + username + "','" + password + "','" + email + "')");
            }
            catch (Exception e)
            {
                System.out.println("sorry cannot b updated,primary key ");
                return "unsuccessful";
            }
        if(result==0)
        {
            System.out.println("not updated"+result+"rows ---updated");
            return "unsuccessful";
        }
        else
        {
            System.out.println("updated with "+result+"rows updated");
            return "successful";
        }
    }
}
