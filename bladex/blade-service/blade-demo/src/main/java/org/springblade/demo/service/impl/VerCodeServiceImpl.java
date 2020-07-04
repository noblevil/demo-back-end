package org.springblade.demo.service.impl;

import org.springblade.demo.service.VerCodeService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VerCodeServiceImpl implements VerCodeService {
    private static int length=6;  //验证码长度

//    @Override
    public  String makeVerCode() {
        StringBuffer verCode = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {  //首先用随机数确定每一位是数字还是字符类型
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 字符
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;  // 取得大写字母还是小写字母
                verCode.append((char) (choice + random.nextInt(26)));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                // 数字
                verCode.append(String.valueOf(random.nextInt(10)));  //数字转字符
            }
        }
        System.out.println("验证码："+verCode);
        return verCode.toString();
    }

}
