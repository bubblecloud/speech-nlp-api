package org.bubble.cloud.speech;

import fi.iki.elonen.NanoHTTPD;

/**
 * NanoHttpd JSON RPC server response.
 *
 * @author Tommi S.E. Laukkanen
 */
public class NanoHttpdJsonRpcServerResponse {
    /**
     * The response status.
     */
    private NanoHTTPD.Response.Status status;
    /**
     * The response message.
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NanoHTTPD.Response.Status getStatus() {
        return status;
    }

    public void setStatus(NanoHTTPD.Response.Status status) {
        this.status = status;
    }
}
