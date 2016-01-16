package org.bubble.cloud.speech;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bubble.cloud.speech.nlpapi.SpeechNlpApi;
import bubble.cloud.speech.nlpapi.SpeechNlpApiImpl;
import fi.iki.elonen.NanoHTTPD;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Speech NPL JSON RPC HTTP server.
 *
 * @author Tommi S.E. Laukkanen
 */
public class SpeechNlpHttpServerMain extends NanoHTTPD {
    /**
     * The logger.
     */
    final static Logger LOGGER = Logger.getLogger("org.bubble.cloud.speech");
    private final NanoHttpdJsonRpcServer jsonRpcServer;

    public static void main(String[] args) {
        try {
            DOMConfigurator.configure("log4j.xml");
            new SpeechNlpHttpServerMain();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error starting Speech NLP JSON RPC HTTP server.", e);
        }
    }

    public SpeechNlpHttpServerMain() throws IOException {
        super(8089);
        final SpeechNlpApi api = new SpeechNlpApiImpl();
        jsonRpcServer = new NanoHttpdJsonRpcServer(api, SpeechNlpApi.class);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        LOGGER.info("Speech NLP JSON RPC HTTP server started.");
    }

    @Override
    public Response serve(IHTTPSession session) {
        final NanoHttpdJsonRpcServerResponse response = jsonRpcServer.handle(session);
        return newFixedLengthResponse(response.getStatus(), "application/json-rpc", response.getMessage());
    }

}