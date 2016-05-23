package com.zombie.util;

import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zombie on 16/1/2.
 */
public class ProtobufReturnValueHandler extends RequestResponseBodyMethodProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ProtobufReturnValueHandler.class);

    private static final Logger response_logger = LoggerFactory.getLogger("protobuf_interactive");

    private static final List<HttpMessageConverter<?>> messageConvertersList = new ArrayList<HttpMessageConverter<?>>();

    static {
        messageConvertersList.add(new ProtobufHttpMessageConverter());
    }

    /*
        public ProtobufReturnValueHandler(){
            super(messageConvertersList);
        }

        public ProtobufReturnValueHandler(final List<HttpMessageConverter<?>> messageConverters) {
            super(messageConverters);
        }

        public ProtobufReturnValueHandler(final List<HttpMessageConverter<?>> messageConverters, final ContentNegotiationManager contentNegotiationManager) {
            super(messageConverters, contentNegotiationManager);
        }
        */
    public ProtobufReturnValueHandler(final ContentNegotiationManager contentNegotiationManager) {
        super(messageConvertersList, contentNegotiationManager);
    }


    @Override
    public boolean supportsReturnType(final MethodParameter returnType) {
        return returnType.getMethodAnnotation(ProtoResponseBody.class) != null;
    }

    @Override
    public void handleReturnValue(final Object returnValue, final MethodParameter returnType, final ModelAndViewContainer mavContainer, final NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException {

        if (ParameterTool.TURNON_DEBUG_MODE.equals(webRequest.getHeader(ParameterTool.DEBUG_MODE))) {

            if (returnValue != null) {
                try {
                    logger.info("{}, response:[{}]", ParameterTool.DEBUG_MODE, JsonFormat.printToString((Message) returnValue));
                } catch (RuntimeException ex) {
                    logger.warn("pring debug error.", ex);
                }
            } else {
                logger.info("{}, response is null", ParameterTool.TURNON_DEBUG_MODE);
            }
        }

        if (Config.isTestEnv) {
            if (returnValue == null) {
                response_logger.info("response is null");
            } else {
                response_logger.info("response:{}", JsonFormat.printToString((Message) returnValue));
            }
        }
        super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}
