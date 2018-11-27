///**
// * FileName: MaterialHandler
// * Author:   Phil
// * Date:     8/7/2018 14:00
// * Description: 媒体Util
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.phil.wechat.material.handler;
//
//import com.phil.modules.config.WechatConfig;
//import com.phil.modules.util.HttpUtil;
//import com.phil.modules.util.JsonUtil;
//import com.phil.wechat.material.model.request.NewsEntity;
//import com.phil.wechat.material.model.response.MaterialResult;
//import com.phil.wechat.material.util.MaterialUtil;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.List;
//import java.util.TreeMap;
//
///**
// * 〈一句话功能简述〉
// * 〈媒体Util〉
// *
// * @author Phil
// * @create 8/7/2018 14:00
// * @since 1.0
// */
//@Slf4j
//public class MaterialHandler {
//
//    /**
//     * 上传临时素材(本地)
//     *
//     * @param accessToken
//     * @param type        媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
//     * @param path        图片路径
//     * @return
//     */
//    public static MaterialResult uploadTempMediaFile(String accessToken, String type, String path) {
//        MaterialResult result = null;
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("access_token", accessToken);
//        params.put("type", type);
//        try {
//            String json = MaterialUtil.uploadMediaFile(WechatConfig.UPLOAD_TEMP_MEDIA_TYPE_URL,
//                    params, path);
//            result = JsonUtil.fromJson(json, MaterialResult.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 上传临时素材(网络)
//     *
//     * @param accessToken
//     * @param type        媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
//     * @param path        图片路径
//     * @return
//     */
//    public static MaterialResult uploadTempMedia(String accessToken, String type, String path) {
//        MaterialResult result = null;
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("access_token", accessToken);
//        params.put("type", type);
//        try {
//            String json = MaterialUtil.uploadMedia(WechatConfig.UPLOAD_TEMP_MEDIA_TYPE_URL, params,
//                    path);
//            result = JsonUtil.fromJson(json, MaterialResult.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 上传永久素材(本地)
//     *
//     * @param accessToken
//     * @param type        媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
//     * @param path        路径
//     * @return
//     */
//    public static MaterialResult uploadForeverMediaFile(String accessToken, String type, String path) {
//        MaterialResult result = null;
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("access_token", accessToken);
//        params.put("type", type);
//        try {
//            String json = MaterialUtil.uploadMediaFile(WechatConfig.UPLOAD_FOREVER_MEDIA_TYPE_URL,
//                    params, path);
//            result = JsonUtil.fromJson(json, MaterialResult.class);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//        return result;
//    }
//
//    /**
//     * 上传永久素材(网络)
//     *
//     * @param accessToken
//     * @param type        媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
//     * @param path
//     * @return
//     */
//    public static MaterialResult uploadForeverMedia(String accessToken, String type, String path) {
//        MaterialResult result = null;
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("access_token", accessToken);
//        params.put("type", type);
//        try {
//            String json = MaterialUtil.uploadMedia(WechatConfig.UPLOAD_FOREVER_MEDIA_TYPE_URL, params,
//                    path);
//            result = JsonUtil.fromJson(json, MaterialResult.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    /**
//     * 上传永久素材(video)
//     *
//     * @param accessToken
//     * @param title
//     * @param introduction
//     * @param path
//     * @return
//     */
//    public static String uploadForeverMediaFile(String accessToken, String title, String introduction, String path) {
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("access_token", accessToken);
//        params.put("type", "video");
//        try {
//            String json = MaterialUtil.uploadVideoMediaFile(
//                    WechatConfig.UPLOAD_FOREVER_MEDIA_TYPE_URL, params, path, title, introduction);
//            MaterialResult result = JsonUtil.fromJson(json, MaterialResult.class);
//            if (StringUtils.isNotEmpty(result.getMediaId())) {
//                return result.getMediaId();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    /**
//     * 上传永久素材(video,网络)
//     *
//     * @param accessToken
//     * @param title
//     * @param introduction
//     * @param path
//     * @return
//     */
//    public static String uploadForeverMedia(String accessToken, String title, String introduction, String path) {
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("access_token", accessToken);
//        params.put("type", "video");
//        try {
//            String json = MaterialUtil.uploadVideoMedia(WechatConfig.UPLOAD_FOREVER_MEDIA_TYPE_URL,
//                    params, path, title, introduction, 0, 0);
//            MaterialResult result = JsonUtil.fromJson(json, MaterialResult.class);
//            if (StringUtils.isNotEmpty(result.getMediaId())) {
//                return result.getMediaId();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 上传图文消息内的图片获取URL(本地)
//     * 其中url就是上传图片的URL，可放置图文消息中使用
//     *
//     * @param accessToken
//     * @param path
//     * @return
//     */
//    public static String uploadImgMediaFile(String accessToken, String path) {
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("access_token", accessToken);
//        try {
//            String json = MaterialUtil.uploadMediaFile(WechatConfig.UPLOAD_IMG_MEDIA_URL, params,
//                    path);
//            return JsonUtil.fromJson(json, MediaUrl.class).getUrl();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 上传图文消息内的图片获取URL(网络)
//     * 其中url就是上传图片的URL，可放置图文消息中使用
//     *
//     * @param accessToken
//     * @param path
//     * @return
//     */
//    public static String uploadImgMedia(String accessToken, String path) {
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("access_token", accessToken);
//        try {
//            String json = MaterialUtil.uploadMedia(WechatConfig.UPLOAD_IMG_MEDIA_URL, params, path);
//            return JsonUtil.fromJson(json, MediaUrl.class).getUrl();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 上传永久图文消息的素材
//     *
//     * @param accessToken 授权token
//     * @param entity      图文消息对象
//     * @return
//     */
//    public static MaterialResult uploadNewsMedia(String accessToken, List<NewsEntity> entity) {
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("access_token", accessToken);
//        // post 提交的参数
//        TreeMap<String, List<NewsEntity>> dataParams = new TreeMap<>();
//        dataParams.put("articles", entity);
//        String data = JsonUtil.toJson(dataParams);
//        String json = HttpUtil.doPost(
//                WechatConfig.UPLOAD_FOREVER_NEWS_MEDIA_URL, params, data);
//        return JsonUtil.fromJson(json, MaterialResult.class);
//    }
//
//    /**
//     * 获取群发视频post中的media_id
//     *
//     * @param accessToken
//     * @param massMpVideoData
//     * @return
//     */
//    public static MaterialResult uploadMediaVideo(String accessToken, MassMpVideoType massMpVideoData) {
//        TreeMap<String, String> params = new TreeMap<>();
//        params.put("access_token", accessToken);
//        // post 提交的参数
//        String data = JsonUtil.toJson(massMpVideoData);
//        String json = HttpUtil.doPost(WechatConfig.UPLOAD_VIDEO_MEDIA_URL,
//                params, data);
//        return JsonUtil.fromJson(json, MaterialResult.class);
//    }
//
//    @Getter
//    @Setter
//    private class MediaUrl {
//        private String url;
//    }
//
//    public static void main(String[] args) {
////        String token = HttpReqUtil.getAccessToken("wx35e3a2348c558fc3", "4093ae2d888f96a8f5dc220faa5f8b14");
////        System.out.println(uploadForeverMedia(token, "image", "http://img5.imgtn.bdimg.com/it/u=415293130,2419074865&fm=27&gp=0.jpg"));
//        String json = "{\n" +
//                "    \"url\":  \"http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0\"\n" +
//                "}";
//        System.out.println(JsonUtil.fromJson(json, MediaUrl.class));
//
//    }
//}
