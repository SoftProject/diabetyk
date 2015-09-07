package pl.com.softproject.diabetyk.core.service;

import java.util.Map;

/**
 * Interface MailTemplateService
 *
 * @author Marcin Jasinski {@literal <mkjasinski@gmail.com>}
 */
public interface MailTemplateService {

    String mergeTemplate(String templateName, Map<String, Object> map);
}
