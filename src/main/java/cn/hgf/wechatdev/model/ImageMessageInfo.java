package cn.hgf.wechatdev.model;

public class ImageMessageInfo extends BaseMessageInfo{
    /** 图片链接（由系统生成）*/
    String PicUrl;
    Image Image;

    public static class Image{
        /** 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。*/
        String MediaId;

        public Image(String mediaId) {
            MediaId = mediaId;
        }

        public String getMediaId() {
            return MediaId;
        }

        public void setMediaId(String mediaId) {
            MediaId = mediaId;
        }

    }

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        this.Image = image;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
