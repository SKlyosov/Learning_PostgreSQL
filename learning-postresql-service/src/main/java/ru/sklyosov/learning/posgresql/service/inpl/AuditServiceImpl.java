package ru.sklyosov.learning.posgresql.service.inpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sklyosov.learning.posgresql.persistence.AuditRepository;
import ru.sklyosov.learning.posgresql.service.AuditService;
import ru.sklyosov.learning.postresql.model.PostAuditDto;

import java.util.List;

@Service
@AllArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    public List<PostAuditDto> getAuditLog(Integer postId) {

        return auditRepository.findAllByPostId(postId).stream()
                .map(it ->
                    new PostAuditDto()
                            .id(it.getId())
                            .postId(it.getPostId())
                            .newTitle(it.getNewTitle())
                            .oldTitle(it.getOldTitle())
                            .operation(it.getOperation())
                            .processedAt(it.getProcessedAt())
                ).toList();
    }
}
