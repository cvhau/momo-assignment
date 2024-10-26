package com.cvhau.assignment.service;

import com.cvhau.assignment.model.Provider;

import java.util.Optional;

public interface ProviderService {
    /**
     * Create a {@link Provider} by given provider name.
     * @param name Provider name
     * @return Created {@link Provider} object.
     */
    Provider createProvider(String name);
}
