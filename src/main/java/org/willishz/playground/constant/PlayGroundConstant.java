package org.willishz.playground.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量类
 */
@Component
public class PlayGroundConstant {

    @Value("#{systemProperties['user.language']}")
    private String language;

    public String getLanguage() {
        return language;
    }
}
