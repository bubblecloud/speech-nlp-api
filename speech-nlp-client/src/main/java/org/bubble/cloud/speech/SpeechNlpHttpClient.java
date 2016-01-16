package org.bubble.cloud.speech;

import bubble.cloud.speech.nlpapi.SpeechNlpApi;
import bubble.cloud.speech.nlpapi.model.UtteranceAnalysisResult;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Speech NLP API JSON RPC HTTP client.
 *
 * @author Tommi S.E. Laukkanen
 */
public class SpeechNlpHttpClient implements SpeechNlpApi {

    /**
     * The speech NLP API JSON RPC client.
     */
    private final SpeechNlpApi speechNlpApi;

    /**
     * Configures the speech NLP API JSON RPC client.
     * @param url the speech NLP JSON RPC HTTP server URL
     */
    public SpeechNlpHttpClient(final String url) {
        JsonRpcHttpClient  jsonRpcClient = null;
        try {
            jsonRpcClient = new JsonRpcHttpClient(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();            throw new RuntimeException("Malformed URL: " + url, e);
        }
        speechNlpApi = ProxyUtil.createClientProxy(
                getClass().getClassLoader(),
                SpeechNlpApi.class,
                jsonRpcClient);

    }

    @Override
    public UtteranceAnalysisResult analyseUtterance(String utterance) {
        return speechNlpApi.analyseUtterance(utterance);
    }
}
