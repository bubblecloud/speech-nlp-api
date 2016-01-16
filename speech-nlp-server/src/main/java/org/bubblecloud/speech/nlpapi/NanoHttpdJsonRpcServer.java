package org.bubblecloud.speech.nlpapi;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import fi.iki.elonen.NanoHTTPD;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class which implements NanoHttpd version of JsonRpcServer.
 *
 * @author Tommi S.E. Laukkanen
 */
public class NanoHttpdJsonRpcServer extends JsonRpcServer {
    /**
     * The logger.
     */
    final static Logger LOGGER = Logger.getLogger("org.bubble.cloud.speech");

    public NanoHttpdJsonRpcServer(Object handler, Class<?> remoteInterface) {
        super(handler, remoteInterface);
    }

    public NanoHttpdJsonRpcServerResponse handle(NanoHTTPD.IHTTPSession session) {
        final NanoHttpdJsonRpcServerResponse response = new NanoHttpdJsonRpcServerResponse();

        if(LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, "Handing HttpServletRequest " + session.getMethod());
        }

        try {
            final ByteArrayOutputStream output = new ByteArrayOutputStream();
            final Object input;
            if (session.getMethod() == NanoHTTPD.Method.POST) {
                input = session.getInputStream();
            } else {
                if (session.getMethod() != NanoHTTPD.Method.GET) {
                    throw new IOException("Invalid request method, only POST and GET is supported");
                }
                input = createInputStream(session.getParms().get("method"), session.getParms().get("id"), session.getParms().get("params"));
            }

            int result = this.handle((InputStream) input, output);
            response.setStatus(NanoHTTPD.Response.Status.OK);
            if (result != 0) {
                if (result == -32700 || result == -32602 || result == -32603 || result <= -32000 && result >= -32099) {
                    response.setStatus(NanoHTTPD.Response.Status.INTERNAL_ERROR);
                } else if (result == -32600) {
                    response.setStatus(NanoHTTPD.Response.Status.NOT_FOUND);
                } else if (result == -32601) {
                    response.setStatus(NanoHTTPD.Response.Status.FORBIDDEN);
                }
            }

            response.setMessage(new String(output.toByteArray(), Charset.forName("UTF-8")));
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Error starting processing JSON RPC request.", e);
            response.setStatus(NanoHTTPD.Response.Status.INTERNAL_ERROR);
            response.setMessage("");
        }

        return response;
    }
}
