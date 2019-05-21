package com.yangzhongli.sp.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ImgUtils {

    //图片合成的背景图片
    private static final String BACK_GROUND_PATH = "/usr/local/application/new-beauty-8087/img/back.png";
    private static final String BACK_GROUND2_PATH = "/usr/local/application/new-beauty-8087/img/back2.png";
    private static final String BACK_GROUND3_PATH = "/usr/local/application/new-beauty-8087/img/back3.png";
    //输出在服务器的地址
    private static final String OUT_PUT_PATH = "/usr/local/application/new-beauty-8087/img/";

    /**
     * 小程序获生成小程序码
     */
    public static final String WX_QR_CODE =
            "https://api.weixin.qq.com/wxa/getwxacode";
    /**
     * 单张图片编辑（添加文字）
     *
     * @param gender
     * @param userName
     * @return
     */
    public static String generateCode(String gender, String userName) {
        log.info("generateCode   ========================" + userName);
        String ossPath = "";
        Resource resource = new ClassPathResource("img/1-14.png");
        //存储新图片地址
        //本地电脑路径：/Users/ydm12/ideaProjects/codeImg/   服务器路径：/usr/local/application/mrc/codeImg/
        String imgName = "/usr/local/application/mrc/codeImg/" + new Date().getTime() + ".png";
        System.out.println(imgName);
        InputStream inputStream = null;
        try {
//            File  file = resource.getFile();//获取模版图片,获取不到服务器路径，只能获取本地路径
            inputStream = resource.getInputStream();

            if (inputStream != null) {

//            BufferedImage imageLocal = ImageIO.read(file);;//获取模版图片,获取不到服务器路径，只能获取本地路径
                BufferedImage imageLocal = ImageIO.read(inputStream);
                // 以本地图片为模板
                Graphics2D g = imageLocal.createGraphics();
                g.setColor(Color.white);
                Font font = new Font("微软雅黑", Font.PLAIN, 30);// 添加字体的属性设置
                // 设置文本样式
                g.setFont(font);
                // 添加用户性别
                //g.drawString(gender, 574, 324);

                if (!StringUtils.isEmpty(gender)) {
                  /*  int y = 317;
                    int x = 575;*/
                    int y = 556;
                    int x = 1090;
                    for (int i = 0; i < gender.length(); i++) {
                        String subStr = gender.substring(i, i + 1);
                        y += 35;
                        g.drawString(subStr, x, y);
                    }
                }

                Font font2 = new Font("微软雅黑", Font.PLAIN, 40);// 添加字体的属性设置
                // 设置文本样式
                g.setFont(font2);
                // 添加用户名称
                if (!StringUtils.isEmpty(userName)) {
                    int y = 556;
                    int x = 1140;
                    for (int i = 0; i < userName.length(); i++) {
                        String subStr = userName.substring(i, i + 1);
                        y += 45;
                        g.drawString(subStr, x, y);
                    }
                }

                // 完成模板修改
//            g.dispose();
                // 获取新文件的地址
                File outputfile = new File(imgName);
                // 生成新的合成过的用户二维码并写入新图片
                ImageIO.write(imageLocal, "png", outputfile);
                // 完成模板修改
                g.dispose();
                ossPath = AliOssUtil.upload("reading/png/" + new Date().getTime() + ".png", outputfile);//生成oss路径
                System.out.println("==========oss路径" + ossPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return ossPath;
    }

    /**
     * 下载网络图片
     *
     * @param urlPath 图片路径
     * @return
     * @throws Exception
     */
    public static String getPicture(String urlPath) throws Exception {
        //new一个URL对象
        URL url = new URL(urlPath);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        String newPath = "/usr/local/application/rhlike-8086/img/goods" + new Date().getTime() + ".png";//服务器路径
        File imageFile = new File(newPath);
        //创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();
        return newPath;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }


    /**
     * 多张图片编辑
     *
     * @param goodsPath  商品图片地址
     * @param qrCodePath 需要插入的图片
     * @param message01  编辑文字
     * @param message02
     * @return
     */
    public static String overlapImage(String goodsPath, String qrCodePath, String message01, String message02) {
        //合成后图片所在服务器地址
        String outPutPath = OUT_PUT_PATH + new Date().getTime() + ".png";
        try {
            //设置图片大小
//            BufferedImage background = resizeImage(618,1000, ImageIO.read(new File("这里是背景图片的路径！")));
            BufferedImage backgroundPicture = resizeImage(551, 949, ImageIO.read(new File(BACK_GROUND_PATH)));
            BufferedImage goodsPicture = resizeImage(460, 360, ImageIO.read(new File(goodsPath)));
            BufferedImage backgroundPicture2 = resizeImage(75, 32, ImageIO.read(new File(BACK_GROUND2_PATH)));
            BufferedImage backgroundPicture3 = resizeImage(160, 31, ImageIO.read(new File(BACK_GROUND3_PATH)));
            //在背景图片上添加二维码图片
            BufferedImage codePicture = resizeImage(90, 90, ImageIO.read(new File(qrCodePath)));//二维码
            //在背景图片中添加入需要写入的信息，例如：扫描下方二维码！
            //String message = "扫描下方二维码！";
            Graphics2D g = backgroundPicture.createGraphics();

            //--------- 绘制图片边框开始
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //绘制一个矩形
            g.drawRect(35, 410, 480, 380);//矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
            // 填充一个矩形
            g.fillRect(35, 410, 480, 380);
            g.setColor(Color.white);//画笔颜色
            g.setStroke(new BasicStroke(10));
            //-----------绘制图片边框介绍

            //图片所在背景图的位置
            g.drawImage(goodsPicture, 45, 420, goodsPicture.getWidth(), goodsPicture.getHeight(), null);
            g.drawImage(backgroundPicture2, 75, 685, backgroundPicture2.getWidth(), backgroundPicture2.getHeight(), null);
            g.drawImage(backgroundPicture3, 75, 725, backgroundPicture3.getWidth(), backgroundPicture3.getHeight(), null);
            g.drawImage(codePicture, 45, 810, codePicture.getWidth(), codePicture.getHeight(), null);
            setStrFont(g, Color.white, "微软雅黑", Font.PLAIN, 25, message01, 78, 710);
            setStrFont(g, Color.white, "微软雅黑", Font.PLAIN, 25, "我是"+message02, 78, 750);
            // 获取新文件的地址
            File outputfile = new File(outPutPath);
            // 完成模板修改
            log.info("==========生成路径：" + outPutPath);
//            ImageIO.write(background, "jpg", new File("这里是一个输出图片的路径"));
            ImageIO.write(backgroundPicture, "png", new File(outPutPath));

            String ossPath = AliOssUtil.upload("rhlike/" + new Date().getTime() + ".png", outputfile);//生成oss路径
            log.info("==========oss路径" + ossPath);
            AliOssUtil.deleteServerFile(outPutPath);
            AliOssUtil.deleteServerFile(qrCodePath);
            g.dispose();
            return ossPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片写入
     *
     * @param x
     * @param y
     * @param bfi
     * @return
     */
    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(
                bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        log.info("图片合成中===============：" + bufferedImage);
        return bufferedImage;
    }

    /**
     * 设置文字
     *
     * @param g     编辑的背景
     * @param name  字体的名称（微软雅黑）
     * @param style 文字风格 PLAIN（普通样式）、BOLD（粗体样式）、ITALIC（斜体样式）、ITALIC|BOLD（斜体组合粗体样式，中间是竖线符号）
     * @param size  大小
     * @param str
     * @param x
     * @param y
     */
    public static Graphics2D setStrFont(Graphics2D g, Color color, String name, int style, int size, String str, int x, int y) {
        g.setColor(color);
        g.setFont(new Font(name, style, size));
        g.drawString(str, x, y);
        return g;
    }


    /**
     * 将指定图片在指定 位置生成缩略图
     */
    public static String changSmall(File uploadFile){
        String path = uploadFile.getPath();
        try {
            BufferedImage input = ImageIO.read(uploadFile);
            BufferedImage inputbig = new BufferedImage(460, 360, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = (Graphics2D) inputbig.getGraphics();
            g.drawImage(input, 0, 0,460,360,null); //画图的大小
            g.dispose();
            String fname = path.substring(0, path.lastIndexOf("."))+"_small.png";//新名字
            //String parent = uploadFile.getParent();
            ImageIO.write(inputbig, "png", new File( fname )); //将其保存在服务器
            log.info("changSmall()"+fname);
            return fname;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }




    /**
     * 生成对应商品的跳转路径
     * @param path
     * @return
     */
    public static String QRCode(String path){
        String text = path; // 商品id
        log.info("商品id： " + text);
        int width = 225; // 二维码图片的宽
        int height = 225; // 二维码图片的高
        String format = "jpg"; // 二维码图片的格式
        try {
            // 生成二维码图片，并返回图片路径
            String pathName = QRCodeUtil.generateQRCode(text, width, height, format, "/usr/local/application/kitchen-8084/img/"+ new Date().getTime() + ".jpg");
            log.info("生成二维码的图片路径： " + pathName);
            return pathName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



//********************************************微信生成二维码*********************************************************//


    /**
     * 获取小程序二维码(有次数限制100 000)
     *
     * @param :WX_QR_CODE          官方获取二维码地址
     * @param width        二维码的宽度 int类型 默认 430
     * @param access_token
     * @param path
     * @return
     */
    public static InputStream createwxaqrcode( String access_token, String path, String width) {
        String url = WX_QR_CODE + "?access_token=" + access_token;
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("path", path);
            jsonParam.put("width", width);
            InputStream instreams = doWXPost(url, jsonParam);
            if (null == instreams) {
                System.out.println("出错获取二维码失败！");
            }
            return instreams;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 带参数无限个数小程序码接口------测试未通过************************
     *
     * @param    :url = WX_QR_CODE
     * @param access_token
     * @param path
     * @param width
     * @return
     */
    public static InputStream getwxacodeunlimit(String access_token, String path, String width) {
        String[] str = path.split("[?]");
        path = str[0];
        String scene = str[1];
        String  url = WX_QR_CODE + "?access_token=" + access_token; // 接收参数json列表
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("scene", scene);
            jsonParam.put("page", path);
            jsonParam.put("width", Integer.parseInt(width));
            jsonParam.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            jsonParam.put("line_color", line_color);
            InputStream instreams = doWXPost(url, jsonParam);
            if (null == instreams) {
                System.out.println("出错获取二维码失败！");
            }
            return instreams;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 请求
     *
     * @param url
     * @param jsonParam
     * @return
     */
    public static InputStream doWXPost(String url, JSONObject jsonParam) {
        InputStream instreams = null;
        HttpPost httpRequst = new HttpPost(url);
        // 创建HttpPost对象
        try {
            StringEntity se = new StringEntity(jsonParam.toString(), "utf-8");
            se.setContentType("application/json");
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "UTF-8"));
            httpRequst.setEntity(se);
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = httpResponse.getEntity();
                if (httpEntity != null) {
                    instreams = httpEntity.getContent();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instreams;
    }

    /** @param instreams 二进制流
     * @param imagePath 图片的保存路径
     * @param fileName 图片的名称
     * @return str 图片保存地址
     */
    public static String saveToImgByInputStream(InputStream instreams, String imagePath, String fileName) {
        String str = "";
//        String path = "QRimage" + getWFileDirName();
//        String linuxPath = path.replaceAll("//", File.separator);
        if (instreams != null) {
            boolean b = uploadImages(instreams, imagePath , fileName);
            if (b) {
                str = imagePath + fileName;
            }
        }
        return str;
    }

    /**
     * IO流保存图片
     *
     * @param instreams
     * @param imagePath
     * @param fileName
     * @return
     */
    public static boolean uploadImages(InputStream instreams, String imagePath, String fileName) {
        File f = new File(imagePath);
        f.setWritable(true, false);
        boolean flag = false;
        try { // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File file = new File(imagePath, fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                try { // 创建新文件
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("创建新文件时出现了错误。。。");
                    e.printStackTrace();
                }
            }
            OutputStream os = new FileOutputStream(imagePath + File.separator + fileName);
            // 开始读取
            while ((len = instreams.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            instreams.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


}
