package uz.pdp.app_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.app_warehouse.constants.ResponseConstants;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.entity.Attachment;
import uz.pdp.app_warehouse.entity.AttachmentContent;
import uz.pdp.app_warehouse.repository.AttachmentContentRepository;
import uz.pdp.app_warehouse.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static uz.pdp.app_warehouse.constants.ResponseConstants.NOT_FOUND;
import static uz.pdp.app_warehouse.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    public ApiResponse add(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        assert file != null;

        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setContentType(file.getContentType());
        attachment.setSize(file.getSize());
        Attachment saveAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(saveAttachment);

        attachmentContentRepository.save(attachmentContent);

        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList() {
        List<Attachment> attachments = attachmentRepository.findAll();
        return new ApiResponse(SUCCESS, true, attachments);
    }

    public ApiResponse get(Integer id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        return new ApiResponse(SUCCESS, true, optionalAttachment.get());
    }

    public ApiResponse delete(Integer id){
        attachmentRepository.deleteById(id);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse edit(Integer id, MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        assert file != null;

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty())
            return new ApiResponse(NOT_FOUND,false);

        Attachment attachment = optionalAttachment.get();

        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
        if (optionalAttachmentContent.isEmpty())
            return new ApiResponse(NOT_FOUND,false);

        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        attachmentContent.setAttachment(savedAttachment);
        attachmentContent.setBytes(file.getBytes());
        attachmentContentRepository.save(attachmentContent);

        return new ApiResponse(SUCCESS,true);
    }

}
