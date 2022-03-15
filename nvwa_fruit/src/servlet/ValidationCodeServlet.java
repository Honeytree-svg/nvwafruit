package servlet;

import javax.imageio.ImageIO;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Random;

public class ValidationCodeServlet extends HttpServlet{
    private static String codeChars="23456789wertyupasdfghkzxcvbnmWERTYUPASDFGHKZXCVBNM";
    private static Color getRandomColor(int minColor,int maxColor){
        Random random=new Random();
        if(minColor>255)minColor=255;
        if(maxColor>255)minColor=255;
        int red=minColor+random.nextInt(maxColor-minColor);
        int blue=minColor+random.nextInt(maxColor-minColor);
        int green=minColor+random.nextInt(maxColor-minColor);
        return new Color(red,green,blue);
    }

    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws SecurityException{

    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws SecurityException, IOException {
        //get the lenght of verification code
        int charsLength=codeChars.length();

        //close the buffer area on client-side to reach one pursuit in compatibility
        response.setHeader("ragma","No-cache");
        response.setHeader("Cache-Control","No-cache");
        response.setDateHeader("Expires",0);

        //width and height of verification code
        int width=90,height=20;
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //get one graphics object to be used to print text
        Graphics g=image.getGraphics();
        Random random=new Random();
        //randomly set fulfilling color
        g.setColor(getRandomColor(180,250));
        //fulfill a rectangular background
        g.fillRect(0,0,width,height);
        //set initial font
        g.setFont(new Font("Times New Roman",Font.ITALIC,height));
        g.setColor(getRandomColor(120,180));
        //save the lastest verification code
        StringBuilder validationCode=new StringBuilder();
        //all font randomly used in verification code
        String[] fontNames={"Times New Roman","Book antiqua","Arial"};
        //randomly generate 3 to 5 verification codes
        for (int i = 0; i < 3+random.nextInt(3); i++) {
            g.setFont(new Font(fontNames[random.nextInt(3)],Font.ITALIC,height));
            char codeChar=codeChars.charAt(random.nextInt(charsLength));
            validationCode.append(codeChar);
            g.setColor(getRandomColor(10,100));
            g.drawString(String.valueOf(codeChar),16*i+random.nextInt(7),height-random.nextInt(6));
        }
        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(5*60);
        session.setAttribute("validation_code",validationCode.toString().toLowerCase());
        g.dispose();
        OutputStream os=response.getOutputStream();
        ImageIO.write(image,"JPEG",os);
    }
}
