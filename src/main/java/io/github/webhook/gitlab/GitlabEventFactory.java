package io.github.webhook.gitlab;

import io.github.webhook.core.EventHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ObjectUtils;

/**
 * @author EalenXie created on 2023/4/14 12:57
 */
public class GitlabEventFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取事件处理器
     *
     * @param event 事件
     * @return 事件处理器
     */
    @SuppressWarnings("unchecked")
    public <D> EventHandler<D> getEventHandler(String event, String handlerType) {
        String hookName = event.replace(" ", "");
        String handleName;
        String regex = "_";
        if (handlerType.contains(regex)) {
            String[] s = handlerType.split(regex, 2);
            handleName = capitalize(s[0].toLowerCase());
        } else {
            handleName = handlerType;
        }
        String beanName = String.format("%s%sEventHandler", hookName, handleName);
        try {
            return (EventHandler<D>) applicationContext.getBean(beanName);
        } catch (BeansException e) {
            throw new UnsupportedOperationException(String.format("Can not get EventHandler[%s]", beanName));
        }
    }


    /**
     * 将字符串的首字母转换为大写
     *
     * @param str 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String capitalize(String str) {
        if (ObjectUtils.isEmpty(str)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toUpperCase(charArray[0]);
        return new String(charArray);
    }
}
