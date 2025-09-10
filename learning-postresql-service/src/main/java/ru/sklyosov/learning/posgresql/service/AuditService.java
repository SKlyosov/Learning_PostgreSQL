package ru.sklyosov.learning.posgresql.service;

import ru.sklyosov.learning.postresql.model.PostAuditDto;

import java.util.List;

public interface AuditService {

    List<PostAuditDto> getAuditLog(Integer postId);

}
