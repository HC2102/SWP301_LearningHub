package swp391.group2.learninghub.utils;

import java.util.Random;

public class DataUtils {
    public static String generateTempPwd(int length) {
        String numbers;
        numbers ="012345678";
        char[] otp =new char[length];
        Random r=new Random();
        for(int i=0;i<length;i++) {
            otp[i]=numbers.charAt(r.nextInt(numbers.length()));
        }
        String optCode="";
        for(int i=0;i<otp.length;i++) {
            optCode+=otp[i];
        }
        return optCode;
    }
}
