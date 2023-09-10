package com.jwlioahd.util;

import net.mamoe.mirai.utils.MiraiLoggerPlatformBase;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mylog extends MiraiLoggerPlatformBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(Mylog.class);

    @Nullable
    @Override
    public String getIdentity() {
        return Mylog.class.toString();
    }

    @Override
    protected void debug0(@Nullable String s, @Nullable Throwable throwable) {
        LOGGER.debug(s,throwable);
    }

    @Override
    protected void error0(@Nullable String s, @Nullable Throwable throwable) {
        LOGGER.error(s,throwable);
    }

    @Override
    protected void info0(@Nullable String s, @Nullable Throwable throwable) {
        LOGGER.info(s,throwable);
    }

    @Override
    protected void verbose0(@Nullable String s, @Nullable Throwable throwable) {
        LOGGER.info(s,throwable);
    }

    @Override
    protected void warning0(@Nullable String s, @Nullable Throwable throwable) {
        LOGGER.warn(s,throwable);
    }
}
