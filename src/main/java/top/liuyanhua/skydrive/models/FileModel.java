package top.liuyanhua.skydrive.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value = "top.liuyanhua.skydrive.models.FileModel",description = "文件信息")
@TableName(value = "file")
public class FileModel implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "文件id")
    private String id;

    @TableField(value = "md5")
    @ApiModelProperty(value = "文件md5值")
    private String md5;

    @TableField(value = "file_name")
    @ApiModelProperty(value = "文件名字")
    private String fileName;

    @TableField(value = "file_size")
    @ApiModelProperty(value = "文件大小")
    private Long fileSize;

    @TableField(value = "storage_mode")
    @ApiModelProperty(value = "文件存储方式")
    private Integer storageMode;

    @TableField(value = "second_upload")
    @ApiModelProperty(value = "文件上传方式，是否秒传")
    private Integer secondUpload;

    @TableField(value = "status")
    @ApiModelProperty(value = "文件状态,0:正常，1：删除")
    private Integer status;

    @TableField(value = "file_type")
    @ApiModelProperty(value = "文件类型:.zip,.jpg")
    private String fileType;

    @TableField(value = "download_url")
    @ApiModelProperty(value = "文件下载地址")
    private String downloadUrl;

    private static final long serialVersionUID = 1L;

    public static final String COL_MD5 = "md5";

    public static final String COL_FILE_NAME = "file_name";

    public static final String COL_FILE_SIZE = "file_size";

    public static final String COL_STORAGE_MODE = "storage_mode";

    public static final String COL_SECOND_UPLOAD = "second_upload";

    public static final String COL_STATUS = "status";

    public static final String COL_FILE_TYPE = "file_type";

    public static final String COL_DOWNLOAD_URL = "download_url";

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return md5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * @param md5
     */
    public void setMd5(String md5) {
        this.md5 = md5 == null ? null : md5.trim();
    }

    /**
     * @return file_name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * @return file_size
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return storage_mode
     */
    public Integer getStorageMode() {
        return storageMode;
    }

    /**
     * @param storageMode
     */
    public void setStorageMode(Integer storageMode) {
        this.storageMode = storageMode;
    }

    /**
     * @return second_upload
     */
    public Integer getSecondUpload() {
        return secondUpload;
    }

    /**
     * @param secondUpload
     */
    public void setSecondUpload(Integer secondUpload) {
        this.secondUpload = secondUpload;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return file_type
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * @return download_url
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * @param downloadUrl
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }
}