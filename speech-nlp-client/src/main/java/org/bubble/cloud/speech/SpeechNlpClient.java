package org.bubble.cloud.speech;

import bubble.cloud.speech.nlpapi.SpeechNlpApi;
import bubble.cloud.speech.nlpapi.model.UtteranceAnalysisResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcClient;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

/**
 * Speech NLP API client.
 */
public class SpeechNlpClient implements SpeechNlpApi {

    /**
     * The speech NLP API JSON RPC client.
     */
    private final SpeechNlpApi speechNlpApi;

    /**
     * Configures the speech NLP API JSON RPC client.
     * @param address the speech NLP server address
     * @param port the speech NLP server port
     */
    public SpeechNlpClient(final String address, final int port) {
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
    public UtteranceAnalysisResult analyseUtterance(String utterance) {
        return speechNlpApi.analyseUtterance(utterance);
    }
}
