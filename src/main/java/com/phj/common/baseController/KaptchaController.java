package com.phj.common.baseController;

import com.google.code.kaptcha.Producer;
import com.phj.common.util.IpAddressUtil;
import com.phj.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @program: food-pairing
 * @description: 验证码
 * @author: Mr.Pan
 * @create: 2021-08-29 14:54
 **/

@RequestMapping("/app/kaptcha")
@Controller
public class KaptchaController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Producer producer;


    @RequestMapping("number.jpg")
    public void number(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();

        //个位数字相加
        String s1 = text.substring(0, 1);
        String s2 = text.substring(1, 2);
        int count = Integer.valueOf(s1).intValue() + Integer.valueOf(s2).intValue();

        //生成图片验证码
        BufferedImage image = producer.createImage(s1 + "+" + s2 + "=?");
        String ipAddress = IpAddressUtil.getIpAddress(request);
        //保存 redis key 自己设置
        redisUtil.set(ipAddress, count,60*5);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
}
