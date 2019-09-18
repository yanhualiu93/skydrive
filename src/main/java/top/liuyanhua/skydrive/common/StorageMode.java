package top.liuyanhua.skydrive.common;

/**
 * Created with IDEA
 *
 * @author: liuyanhua
 * @date:2019-06-10 17:55
 * @description //TODO $
 */
public enum StorageMode {
    Local(0),Aliyunoss(1),Outher(2);
    private int type;

    StorageMode(int i) {
        this.type = i;
    }
}
