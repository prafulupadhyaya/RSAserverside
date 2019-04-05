import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.IOException;

public class LoggedIn {
    LoggedIn() {

            try {

                while(true) {
                    System.out.println("into thr logged in class");
                    //MyServer.din = new DataInputStream(MyServer.socket.getInputStream());
                    System.out.println("din k baad");
                    String s = MyServer.din.readUTF();

                    System.out.println("read k baad");
                    JSONObject response = new JSONObject(s);
                    if (response.getString("code").equals("200")) {
                        System.out.println(response.getString("username"));
                        System.out.println(response.getString("productname"));
                        System.out.println(response.getString("imagedes"));
                        String str = DataBaseHandler.UpdateData(response);
                        System.out.println("data adddition is" + str);
                        MyServer.dout.writeUTF(str);

                    } else if (response.getString("code").equals("201")) {
                        //System.out.println(response.getString("username"));

                        String z = DataBaseHandler.countrows();

                        System.out.println("sacccccccccccccccccccccccccccccccccccccccccc");
                        MyServer.dout.writeUTF(z);



                        for (int i = 1; i <= Integer.parseInt(z); i++) {
                            //int id=MyServer.din.readInt();
                            JSONObject explore=new JSONObject();
                            String id = MyServer.din.readUTF();
                            String p = DataBaseHandler.image(Integer.parseInt(id));
                            String pc = DataBaseHandler.productname(Integer.parseInt(id));
                            String pdes = DataBaseHandler.productdesc(Integer.parseInt(id));
                            String price = DataBaseHandler.price(Integer.parseInt(id));
                            String rentern = DataBaseHandler.renter(Integer.parseInt(id));
                            String phone = DataBaseHandler.phone(Integer.parseInt(id));
                            String lattitude=DataBaseHandler.lattitude(Integer.parseInt(id));
                            String longitude=DataBaseHandler.longitude(Integer.parseInt(id));

                            explore.put("image",p);
                            explore.put("productname",pc);
                            explore.put("productdesc",pdes);
                            explore.put("price",price);
                            explore.put("rentern",rentern);
                            explore.put("phone",phone);
                            explore.put("lattitude",lattitude);
                            explore.put("longitude",longitude);
                            MyServer.dout.writeUTF(explore.toString());

                        }

                        System.out.println("yha tk sab badiyaa aap sunao kaise ho");
                    }
                }
            } catch (Exception e) {
                System.out.println("logged in mai problem" + e.getMessage());
            }


        }


}
