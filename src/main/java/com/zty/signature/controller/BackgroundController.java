package com.zty.signature.controller;

import com.zty.signature.dao.BackgroundDao;
import com.zty.signature.entity.Background;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(description = "图片")
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class BackgroundController {

    @Autowired
    private BackgroundDao backgroundDao;

    @ApiOperation(value = "base64", notes = "测试数据:")
    @PostMapping("/base64_img")
    public boolean GenerateImage(@RequestBody Map map) {// 对字节数组字符串进行Base64解码并生成图片
        System.out.println(map.get("imgStr"));
        String imgStr1 = (String) map.get("imgStr");
        String imgStr = imgStr1.replaceAll(" ", "+");
        System.out.println("asd:" + imgStr);
        String dataPrix = ""; //base64格式前头
        String data = "";//实体部分数据

        //图像二
        String imgStr3 = (String) map.get("imgStr1");
        String imgStr2 = imgStr3.replaceAll(" ", "+");
        System.out.println("asd:" + imgStr2);
        String dataPrix1 = ""; //base64格式前头
        String data1 = "";//实体部分数据
        //String imgFilePath="D:\\wangyc.png";
        if (imgStr == null) {// 图像数据为空
            return false;
        } else {
            String[] d = imgStr.split("base64,");//将字符串分成数组
            if (d != null && d.length == 2) {
                dataPrix = d[0];
                data = d[1];
            } else {
                return false;
            }
        }

        //图像二
        if (imgStr2 == null) {// 图像数据为空
            return false;
        } else {
            String[] d1 = imgStr2.split("base64,");//将字符串分成数组
            if (d1 != null && d1.length == 2) {
                dataPrix1 = d1[0];
                data1 = d1[1];
            } else {
                return false;
            }
        }
        String suffix = "";//图片后缀，用以识别哪种格式数据
        //data:image/jpeg;base64,base64编码的jpeg图片数据
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
            suffix = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix)) {
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        } else {
            return false;
        }

        //图像二
        String suffix1 = "";//图片后缀，用以识别哪种格式数据
        //data:image/jpeg;base64,base64编码的jpeg图片数据
        if ("data:image/jpeg;".equalsIgnoreCase(dataPrix1)) {
            suffix1 = ".jpg";
        } else if ("data:image/x-icon;".equalsIgnoreCase(dataPrix1)) {
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix1 = ".ico";
        } else if ("data:image/gif;".equalsIgnoreCase(dataPrix1)) {
            //data:image/gif;base64,base64编码的gif图片数据
            suffix1 = ".gif";
        } else if ("data:image/png;".equalsIgnoreCase(dataPrix1)) {
            //data:image/png;base64,base64编码的png图片数据
            suffix1 = ".png";
        } else {
            return false;
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String tempFileName = uuid + suffix;
        String imgFilePath = "D:\\Images\\" + tempFileName;//新生成的图片地址
        //String imgFilePath = "/img/wisdom_site/"+tempFileName;//新生成图片的服务器地址
        System.out.println(tempFileName);
        System.out.println(imgFilePath);

        //图像二
        //BASE64Decoder decoder = new BASE64Decoder();
        String uuid1 = UUID.randomUUID().toString().replaceAll("-", "");
        String tempFileName1 = uuid1 + suffix1;
        String imgFilePath1 = "D:\\Images\\" + tempFileName1;//新生成的图片地址
        //String imgFilePath = "/img/wisdom_site/"+tempFileName;//新生成图片的服务器地址
        System.out.println(tempFileName1);
        System.out.println(imgFilePath1);
        //BASE64Decoder decoder = new BASE64Decoder();

        //增加到数据库
        map.put("imgurl",tempFileName);
        map.put("img2url",tempFileName1);



        try {
            // Base64解码
            byte[] bytes = Base64.decodeBase64(imgStr.replace("data:image/png;base64,", ""));// decoder.decodeBuffer(imgStr);
            imgStr = imgStr.replace("base64,", "");
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }

            // Base64解码
            byte[] bytes1 = Base64.decodeBase64(imgStr2.replace("data:image/png;base64,", ""));// decoder.decodeBuffer(imgStr);
            imgStr2 = imgStr2.replace("base64,", "");
            for (int i = 0; i < bytes1.length; ++i) {
                if (bytes1[i] < 0) {// 调整异常数据
                    bytes1[i] += 256;
                }
            }

            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            OutputStream out1 = new FileOutputStream(imgFilePath1);
            out1.write(bytes1);
            backgroundDao.add_background(map);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @ApiOperation(value = "查找员工照片", notes = "测试数据:{\"name\":\"安全行为之星系统.pdf\"}")
    @GetMapping("/find_img")
    public void find_img(@RequestParam String img_url, HttpServletResponse response) {
        try {
            BufferedInputStream bis =
                    new BufferedInputStream(
                            new FileInputStream(
                                    new File("D:\\Images\\" + img_url)));///root/img/
            int num;
            byte[] b = new byte[1024];

            while ((num = bis.read(b)) != -1) {
                response.getOutputStream().write(b, 0, num);
            }
            response.getOutputStream().flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "查找全部签名照",notes = "")
    @PostMapping("/find")
    public List<Background> find(){
        return backgroundDao.find();
    }

}
