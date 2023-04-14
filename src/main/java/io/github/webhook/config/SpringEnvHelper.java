package io.github.webhook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * @author EalenXie create on 2020/7/24 10:24
 */
public class SpringEnvHelper implements ApplicationContextAware {


    /**
     * 本机真实Ip
     */
    private static String localhostIp;

    private static final String UNKNOWN = "unknown";

    private static Integer port;

    @Override
    public void setApplicationContext(@Autowired @NonNull ApplicationContext applicationContext) {
        setPort(applicationContext);
    }


    public static Integer getPort() {
        return port;
    }


    /**
     * 获取本机真实IP
     */
    public static String getLocalhostIp() {
        if (localhostIp == null) {
            try {
                localhostIp = getLocalIpAddress0();
            } catch (IOException e) {
                e.printStackTrace();
                localhostIp = UNKNOWN.toUpperCase();
            }
        }
        return localhostIp;
    }

    /**
     * 设置程序profile
     */
    private static void setPort(ApplicationContext applicationContext) {
        Environment environment = applicationContext.getEnvironment();
        String portString = environment.getProperty("server.port");
        port = Integer.parseInt(StringUtils.isEmpty(portString) ? "8080" : portString);

    }

    /**
     * 判断非虚拟网卡,非环回地址,包含有效InetAddresses
     */
    public static boolean isLoopNetwork(NetworkInterface ni) {
        if (!ni.getInetAddresses().hasMoreElements()) {
            return true;
        }
        Pattern compile = Pattern.compile("docker|lo|VMware|veth|flannel|cni");
        if (!compile.matcher(ni.getName()).find() && ni.getDisplayName() != null) {
            return compile.matcher(ni.getDisplayName()).find();
        }
        return true;
    }

    /**
     * 判断非IPV6地址
     */
    public static boolean isNotIpV6(InetAddress address) {
        Pattern compile = Pattern.compile("::|0:0:|fe80");
        String ip = address.getHostAddress();
        return !compile.matcher(ip).find();
    }

    /**
     * 获取本机网卡第一个IPv4 非 docker 网卡/非环回地址
     *
     * @return Get the first IPv4 of the native network card
     * @throws IOException Resolving native Ip may throw UnknownHostException
     */
    public static String getLocalIpAddress0() throws IOException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            if (!isLoopNetwork(ni)) {
                Enumeration<InetAddress> ipAddresses = ni.getInetAddresses();
                while (ipAddresses.hasMoreElements()) {
                    InetAddress address = ipAddresses.nextElement();
                    if (isNotIpV6(address)) {
                        return address.getHostAddress();
                    }
                }
            }
        }
        throw new UnsupportedOperationException(new UnknownHostException());
    }


}
