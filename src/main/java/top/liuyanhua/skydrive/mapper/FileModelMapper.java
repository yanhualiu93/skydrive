package top.liuyanhua.skydrive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.liuyanhua.skydrive.models.FileModel;
import top.liuyanhua.skydrive.models.FileModelExample;

public interface FileModelMapper extends BaseMapper<FileModel> {
    long countByExample(FileModelExample example);

    int deleteByExample(FileModelExample example);

    List<FileModel> selectByExample(FileModelExample example);

    int updateByExampleSelective(@Param("record") FileModel record, @Param("example") FileModelExample example);

    int updateByExample(@Param("record") FileModel record, @Param("example") FileModelExample example);
}