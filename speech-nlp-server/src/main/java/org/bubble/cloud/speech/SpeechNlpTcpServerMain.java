package org.bubble.cloud.speech;

import bubble.cloud.speech.nlpapi.SpeechNlpApi;
import bubble.cloud.speech.nlpapi.SpeechNlpApiImpl;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.googlecode.jsonrpc4j.StreamServer;
import org.apache.log4j.xml.DOMConfigurator;

import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Speech NPL JSON RPC TCP server.
 *
 * @author Tommi S.E. Laukkanen
 */
public class SpeechNlpTcpServerMain {
    /**
     * The logger.
     */
    final static Logger LOGGER = Logger.getLogger("org.bubble.cloud.speech");

    public static void main(final String[] args) {
        try {
            DOMConfigurator.configure("log4j.xml");

            final SpeechNlpApi api = new SpeechNlpApiImpl();
            final JsonRpcServer jsonRpcServer = new JsonRpcServer(api, SpeechNlpApi.class);

            final String address = "0.0.0.0";
            final int port = 8089;
            final int maxThreads = 50;
            final int backlog = 50;
            final InetAddress bindAddress = InetAddress.getByName(address);
            final StreamServer streamServer = new StreamServer(jsonRpcServer, maxThreads, port, backlog, bindAddress);

            streamServer.start();

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        streamServer.stop();
                    } catch (InterruptedException ex) {
                        LOGGER.log(Level.WARNING, "Exception while stopping JSON RPC streaming server.", ex);
                    }
                    LOGGER.info("Speech NLP Server exited.");
                }
            });

            LOGGER.info("Speech NLP Server started.");
        } catch (final Exception e) {
            LOGGER.log(Level.SEVERE, "Error starting JSON RPC streaming server.", e);
        }
    }
}
