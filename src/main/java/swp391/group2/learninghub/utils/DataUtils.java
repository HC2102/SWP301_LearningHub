package swp391.group2.learninghub.utils;

import java.util.Random;

public class DataUtils {
    public static String generateTempPwd(int length) {
        String numbers;
        numbers ="012345678";
        char otp[]=new char[length];
        Random r=new Random();
        int z = 0;
//        while(i<length){
//            otp[i]=numbers.charAt(r.nextInt(numbers.length()));
//            i++;
//        }
        for(int i=0;i<length;i++) {
            otp[i]=numbers.charAt(r.nextInt(numbers.length()));
        }

        StringBuilder bld = new StringBuilder();
        for(int i=0;i<otp.length;i++) {
            bld.append(otp[i]);
        }
        String optCode = bld.toString();
        return optCode;
    }
}
