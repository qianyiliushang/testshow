package com.zombie.util;

import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.IOException;
import java.util.List;

/**
 * Created by zombie on 16/1/10.
 */
public class APIProtoBufMessageConverter extends ProtobufHttpMessageConverter{
    private static final Logger logger = LoggerFactory.getLogger(APIProtoBufMessageConverter.class);
    private static final Logger request_logger = LoggerFactory.getLogger("protobuf_interactive");
    @Override
    protected Message readInternal(Class<? extends Message> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        Message message = super.readInternal(clazz, inputMessage);
        List<String> debugMode = inputMessage.getHeaders().get(ParameterTool.DEBUG_MODE);
        if ( (debugMode != null && !debugMode.isEmpty()
                && ParameterTool.TURNON_DEBUG_MODE.equals(debugMode.get(0))) || Config.isTestEnv){
            logger.info("{}, request:[{}]", ParameterTool.DEBUG_MODE, JsonFormat.printToString(message));
        }

        if(Config.isTestEnv){
            request_logger.info("request:{}", JsonFormat.printToString(message));
        }
        return message;
    }
}
