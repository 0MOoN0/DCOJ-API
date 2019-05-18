package com.dcoj.service.impl;

import com.dcoj.dao.AttachmentMapper;
import com.dcoj.entity.AttachmentEntity;
import com.dcoj.service.AttachmentService;
import com.dcoj.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AttachmentService 实现类，提交Attachement业务相关服务
 *
 * @author Leon
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 根据附件(Attachment)获取Attachment信息
     *
     * @param aid 附件ID
     * @return
     */
    @Override
    public AttachmentEntity getByAid(int aid) {
        return attachmentMapper.selectByPrimaryKey(aid);
    }

    /**
     * 根据附件ID获取URL
     *
     * @param aid 附件ID
     * @return
     @Override public String getAttachmentURLByAid(int aid) {
     AttachmentEntity attachmentEntity = attachmentMapper.selectByPrimaryKey(aid);
     WebUtil.assertNotNull(attachmentEntity, "文件不存在，无法获取文件地址");
     return attachmentEntity.getUrl();
     }
     */

}
