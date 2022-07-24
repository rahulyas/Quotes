package com.example.quotes;

import java.util.List;

public interface QuoteResponseListener {
    void didFetch(List<QutoeResponse> responses, String message);
    void didError(String message);
}
