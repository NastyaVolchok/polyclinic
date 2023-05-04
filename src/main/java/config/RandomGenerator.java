package config;

import java.security.SecureRandom;

public class RandomGenerator {
    private static RandomGenerator randomGenerator=new RandomGenerator();
    public static RandomGenerator getRandomGenerato(){return randomGenerator;}
    public String genaratedPassowrd(){
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < 8; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
    }
    public String genaratedLogin(){
        int a = (int) ( 1000+Math.random() * 9999 );
        String log=Integer.toString(a);
        return log;
    }
}
