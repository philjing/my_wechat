/**
 * FileName: MaterialUtil
 * Author:   Phil
 * Date:     11/20/2018 9:13 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.material.util;

import com.phil.modules.constant.SystemConstant;
import com.phil.modules.util.HttpUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/20/2018 9:13 PM
 * @since 1.0
 */
public class MaterialUtil extends HttpUtil {

    /**
     * 上传媒体文件(本地)
     *
     * @param api       api的路径
     * @param param     api参数
     * @param mediaPath 待上传的image/music 的path
     * @return
     * @throws Exception
     */
    public static String uploadMediaFile(String api, Map<String, String> param, String mediaPath)
            throws Exception {
        URL url = new URL(setParmas(api, param, StringUtils.EMPTY));
        try {
            File file = new File(mediaPath);
            if (!file.isFile() || !file.exists()) {
                throw new IOException("file is not exist");
            }
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestMethod(POST_METHOD);
            // 设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", String.valueOf(StandardCharsets.UTF_8));
            // 设置边界
            String boundary = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            // 请求正文信息
            // 第一部分
            OutputStream output = new DataOutputStream(con.getOutputStream());
            IOUtils.write(("--" + boundary + "\r\n").getBytes(), output);
            IOUtils.write(("Content-Disposition: form-data;name=\"media\"; filename=\"" + file.getName() + "\"\r\n")
                    .getBytes(), output);
            IOUtils.write(
                    "Content-Type:application/octet-stream\r\n\r\n".getBytes(),
                    output);
            // IOUtils.write(("Content-Type: "+ fileExt + "\r\n\r\n").getBytes(), output);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
            IOUtils.copy(inputStream, output);
            // 结尾部分
            IOUtils.write(("\r\n--" + boundary + "--\r\n").getBytes(), output);
            output.flush();
            return IOUtils.toString(con.getInputStream());
        } catch (IOException e) {
            throw new IOException("read data error");
        }
    }

    /**
     * 上传媒体文件(不能本地)
     *
     * @param api       api的路径
     * @param param     api参数
     * @param mediaPath 待上传的image/music 的path
     * @return
     * @throws Exception
     */
    public static String uploadMedia(String api, Map<String, String> param, String mediaPath) throws Exception {
        URL url = new URL(setParmas(api, param, StringUtils.EMPTY));
        try {
            String boundary = "----";
            HttpURLConnection conn = getConnection(url, POST_METHOD);
            conn.setConnectTimeout(SystemConstant.HTTP_DEFAULT_CONNTIME);
            conn.setReadTimeout(SystemConstant.HTTP_DEFAULT_READTIME);
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            OutputStream output = conn.getOutputStream();
            URL mediaUrl = new URL(mediaPath);
            HttpURLConnection mediaConn = (HttpURLConnection) mediaUrl.openConnection();
            mediaConn.setDoOutput(true);
            mediaConn.setUseCaches(false);
            mediaConn.setRequestMethod(POST_METHOD);
            mediaConn.setConnectTimeout(SystemConstant.HTTP_DEFAULT_CONNTIME);
            mediaConn.setReadTimeout(SystemConstant.HTTP_DEFAULT_READTIME);
            String connType = mediaConn.getContentType();
            // 获得文件扩展
            String fileExt = getFileExt(connType);
            IOUtils.write(("--" + boundary + "\r\n").getBytes(), output);
            IOUtils.write(("Content-Disposition: form-data; name=\"media\"; filename=\"" + getFileName(mediaPath)
                    + "\"\r\n").getBytes(), output);
            IOUtils.write(("Content-Type: " + fileExt + "\r\n\r\n").getBytes(), output);
            BufferedInputStream inputStream = new BufferedInputStream(mediaConn.getInputStream());
            IOUtils.copy(inputStream, output);
            IOUtils.write(("\r\n----" + boundary + "--\r\n").getBytes(), output);
            mediaConn.disconnect();
            // 获取输入流
            return IOUtils.toString(conn.getInputStream());
        } catch (IOException e) {
            throw new IOException("read data error");
        }
    }

    /**
     * 上传Video媒体文件(本地)
     *
     * @param api          api的路径
     * @param param        api参数
     * @param mediaPath    待上传的voide 的path
     * @param title        视频标题
     * @param introduction 视频描述
     * @return
     * @throws Exception
     */
    public static String uploadVideoMediaFile(String api, Map<String, String> param,
                                              String mediaPath, String title, String introduction) throws Exception {
        try {
            File file = new File(mediaPath);
            if (!file.isFile() || !file.exists()) {
                throw new IOException("file is not exist");
            }
            URL url = new URL(setParmas(api, param, null));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(POST_METHOD);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Cache-Control", "no-cache");
            String boundary = "-----------------------------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            OutputStream output = conn.getOutputStream();
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", file.getName()).getBytes());
            output.write("Content-Type: video/mp4 \r\n\r\n".getBytes());
            FileInputStream input = new FileInputStream(file);
            IOUtils.copy(input, output);
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
            output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", title, introduction).getBytes());
            output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
            output.flush();
            return IOUtils.toString(conn.getInputStream());
        } catch (IOException e) {
            throw new IOException("read data error");
        }
    }

    /**
     * 上传Video媒体文件(网络)
     *
     * @param api          api的路径
     * @param param        api参数
     * @param mediaPath    待上传的voide 的path
     * @param title        视频标题
     * @param introduction 视频描述
     * @param connTime     连接时间 默认为5000
     * @param readTime     读取时间 默认为5000
     * @return
     * @throws Exception
     */
    public static String uploadVideoMedia(String api,  Map<String, String> param, String mediaPath,
                                          String title, String introduction, int connTime, int readTime) throws Exception {
        URL url = new URL(setParmas(api, param, null));
        try {
            String boundary = "----";
            HttpURLConnection conn = getConnection(url, POST_METHOD);
            conn.setConnectTimeout(connTime == 0 ? SystemConstant.HTTP_DEFAULT_CONNTIME : connTime);
            conn.setReadTimeout(readTime == 0 ? SystemConstant.HTTP_DEFAULT_READTIME : readTime);
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            OutputStream output = conn.getOutputStream();
            URL mediaUrl = new URL(mediaPath);
            HttpURLConnection mediaConn = (HttpURLConnection) mediaUrl.openConnection();
            mediaConn.setDoOutput(true);
            mediaConn.setUseCaches(false);
            mediaConn.setRequestMethod(POST_METHOD);
            mediaConn.setConnectTimeout(connTime == 0 ? SystemConstant.HTTP_DEFAULT_CONNTIME : connTime);
            mediaConn.setReadTimeout(readTime == 0 ? SystemConstant.HTTP_DEFAULT_READTIME : readTime);
            IOUtils.write(("--" + boundary + "\r\n").getBytes(), output);
            IOUtils.write(("Content-Disposition: form-data; name=\"media\"; filename=\"" + getFileName(mediaPath)
                    + "\"\r\n").getBytes(), output);
            IOUtils.write("Content-Type: video/mp4 \r\n\r\n".getBytes(), output);
            BufferedInputStream inputStream = new BufferedInputStream(mediaConn.getInputStream());
            IOUtils.copy(inputStream, output);
            // 结尾部分
//                IOUtils.write(("--" + boundary + "\r\n").getBytes(SystemConstant.DEFAULT_ENCODING), output);
//                IOUtils.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n"
//                        .getBytes(SystemConstant.DEFAULT_ENCODING), output);
//                IOUtils.write(("{\"title\":\"" + title + "\",\"introduction\":\"" + introduction + "\"}")
//                        .getBytes(SystemConstant.DEFAULT_ENCODING), output);
//                IOUtils.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes(SystemConstant.DEFAULT_ENCODING),
//                        output);
            IOUtils.write(("--" + boundary + "\r\n").getBytes(), output);
            IOUtils.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes(), output);
            IOUtils.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", title, introduction).getBytes(), output);
            IOUtils.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes(), output);
            mediaConn.disconnect();
            // 获取输入流
            return IOUtils.toString(conn.getInputStream());
        } catch (IOException e) {
            throw new IOException("read data error");
        }
    }

}
