package com.dcoj.service;

import com.dcoj.dao.AttachmentMapper;
import com.dcoj.entity.AttachmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Attachment服务类
 *
 * @author Leon
 */
public interface AttachmentService {

    /**
     * 根据附件(Attachment)获取Attachment信息
     * @param aid   附件ID
     * @return
     */
    AttachmentEntity getByAid(int aid);

    /**
     * 根据附件ID获取URL
     * @param aid   附件ID
     * @return

//    String getAttachmentURLByAid(int aid);
     */
}
