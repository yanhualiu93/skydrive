package top.liuyanhua.skydrive.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created with IDEA
 *
 * @author: liuyanhua
 * @date:2019-06-10 16:22
 * @description //TODO $
 */
@ApiModel("缓存数据")
public class FileCacheInfo {
    //文件md5值
    @ApiModelProperty("文件md5值")
    private String md5;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getCurIndex() {
        return curIndex;
    }

    public void setCurIndex(Integer curIndex) {
        this.curIndex = curIndex;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Long chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Integer getChunks() {
        return chunks;
    }

    public void setChunks(Integer chunks) {
        this.chunks = chunks;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    //当前第几片
    @ApiModelProperty("当前下标")
    private Integer curIndex;
    //文件名字
    @ApiModelProperty("文件名字")
    private String  fileName;
    //分片大小
    @ApiModelProperty("分块大小")
    private Long chunkSize;
    //分片总数
    @ApiModelProperty("分片总数")
    private Integer chunks;
    //上传时间
    @ApiModelProperty("上传时间")
    private Date uploadDate;
}
