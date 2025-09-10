package ru.sklyosov.learning.posgresql.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.sklyosov.learning.posgresql.service.AuditService;
import ru.sklyosov.learning.postresql.api.AuditApi;
import ru.sklyosov.learning.postresql.model.PostAuditDto;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuditController implements AuditApi {

    private final AuditService auditService;

    @Override
    public ResponseEntity<List<PostAuditDto>> getPostsAudit(Integer postId) {
        return ResponseEntity.ok(auditService.getAuditLog(postId));
    }
}
