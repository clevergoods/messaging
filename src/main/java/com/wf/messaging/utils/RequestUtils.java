package com.wf.messaging.utils;

import com.wf.messaging.domain.Author;
import com.wf.messaging.domain.Message;
import com.wf.messaging.domain.MessageType;
import com.wf.messaging.domain.Metainformation;
import com.wf.messaging.model.MessageRequest;
import com.wf.messaging.repository.AuthorRepository;
import com.wf.messaging.repository.MessageTypeRepository;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
public class RequestUtils {

    @Value("${author_from_message_request_error}")
    private String authorFromMessageRequestError;

    @Value("${type_from_message_request_error}")
    private String typeFromMessageRequestError;

    @Autowired
    private MessageTypeRepository messageTypeRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Message createMessageFromRequest(MessageRequest messageRequest, Metainformation metaInformation) throws NoSuchMethodException,
            MethodArgumentNotValidException {
        Author author = findAuthor(messageRequest);
        MessageType type = findType(messageRequest);
        String uuid = UUID.randomUUID().toString();
        return Message.builder().author(author).organization(messageRequest.getOrganization())
                .data(messageRequest.getData()).type(type).uuid(uuid).metainformation(metaInformation).build();
    }

    public Metainformation generateMetainformation(HttpServletRequest request) {
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String clientOs = userAgent.getOperatingSystem().getName();
        String browserName = userAgent.getBrowser().getName();
        String browserVersion = userAgent.getBrowserVersion().getVersion();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        long contentLength = request.getContentLengthLong();

        return Metainformation.builder().ipAddress(ipAddress).osName(clientOs).browserName(browserName)
                .browserVersion(browserVersion).contentLength(contentLength).build();
    }

    private Author findAuthor(MessageRequest messageRequest)
            throws MethodArgumentNotValidException, NoSuchMethodException, SecurityException {
        Author author = authorRepository.findByAuthorName(messageRequest.getAuthor());
        if (author == null) {
            BeanPropertyBindingResult errors = new BeanPropertyBindingResult(messageRequest, "messageRequest");
            errors.addError(new ObjectError("MessageRequest", authorFromMessageRequestError));
            throw new MethodArgumentNotValidException(
                    new MethodParameter(this.getClass().getDeclaredMethod("findAuthor", MessageRequest.class), 0),
                    errors);
        }
        return author;
    }

    private MessageType findType(MessageRequest messageRequest)
            throws MethodArgumentNotValidException, NoSuchMethodException, SecurityException {
        MessageType type = messageTypeRepository.findByTypeName(messageRequest.getType());
        if (type == null) {
            BeanPropertyBindingResult errors = new BeanPropertyBindingResult(messageRequest, "messageRequest");
            errors.addError(new ObjectError("MessageRequest", typeFromMessageRequestError));
            throw new MethodArgumentNotValidException(
                    new MethodParameter(this.getClass().getDeclaredMethod("findType", MessageRequest.class), 0),
                    errors);
        }
        return type;
    }

}
