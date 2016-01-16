package org.bubblecloud.speech.nlpapi;

import org.bubblecloud.speech.nlpapi.SpeechNlpApi;
import org.bubblecloud.speech.nlpapi.model.UtteranceAnalysisResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Speech NLP API JSON RPC TCP client.
 *
 * @author Tommi S.E. Laukkanen
 */
public class SpeechNlpTcpClient implements SpeechNlpApi {

    /**
     * The speech NLP API JSON RPC client.
     */
    private final SpeechNlpApi speechNlpApi;

    /**
     * Configures the speech NLP API JSON RPC client.
     * @param address the speech NLP server address
     * @param port the speech NLP server port
     */
    public SpeechNlpTcpClient(final String address, final int port) {
        JsonRpcClient jsonRpcClient = new JsonRpcClient(new ObjectMapper());
        try {
            speechNlpApi = ProxyUtil.createClientProxy(
                    SpeechNlpApi.class.getClassLoader(),
                    SpeechNlpApi.class, jsonRpcClient,
                    new Socket(InetAddress.getByName(address), port));
        } catch (IOException e) {
            throw new RuntimeException("Error connecting to Speech NLP Server at :" + address + ":" + port, e);
        }
    }

    @Override
    public UtteranceAnalysisResult analyseUtterance(final String locale, String utterance) {
        return speechNlpApi.analyseUtterance(locale, utterance);
    }
}
