package info.yywang.file.enums;


import org.springframework.util.StringUtils;

/**
 * 文件类型
 */
public enum FileType {

    /**
     * 图片
     */
    Image(10),

    /**
     * 压缩包
     */
    ZipFile(20),

    /**
     * 音频
     */
    Video(30),

    /**
     * 其他
     */
    OTHER(40);

    /**
     * 文件类型
     */
    private int value;

    FileType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FileType getByValue(int value) {
        if (value == 10) {
            return FileType.Image;
        } else if (value == 20) {
            return FileType.ZipFile;
        } else if (value == 30) {
            return FileType.Video;
        } else if (value == 40) {
            return FileType.OTHER;
        } else {
            return null;
        }
    }

    public static FileType getByUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return OTHER;
        }
        String fileTypeStr = url.substring(url.lastIndexOf(".") + 1, url.length());
        String[] imgTypeArr = new String[]{"jpeg", "jpg", "png", "tif", "bmp", "gif", "ico"};
        for (String imageType : imgTypeArr) {
            if (imageType.toLowerCase().trim().equals(fileTypeStr)) {
                return Image;
            }
        }
        String[] videoTypeArr = new String[]{"mp3", "mp4", "wav", "avi", "mkv"};
        for (String videoType : videoTypeArr) {
            if (videoType.trim().toLowerCase().equals(fileTypeStr)) {
                return Video;
            }
        }
        String[] zipTypeArr = new String[]{"zip", "rar"};
        for (String zipType : zipTypeArr) {
            if (zipType.trim().toLowerCase().equals(zipType)) {
                return ZipFile;
            }
        }
        return OTHER;
    }
}
