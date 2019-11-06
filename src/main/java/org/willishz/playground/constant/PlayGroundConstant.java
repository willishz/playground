package org.willishz.playground.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by luzongwei on 2018/8/16 0016.
 */
@Component
public class PlayGroundConstant {

    @Value("#{systemProperties['user.language']}")
    private String language;

    public String getLanguage() {
        return language;
    }
}
