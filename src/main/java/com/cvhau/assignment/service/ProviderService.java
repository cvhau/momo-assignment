package com.cvhau.assignment.service;

import com.cvhau.assignment.entity.Provider;

public interface ProviderService {
    /**
     * Create a {@link Provider} by given provider name.
     * @param name Provider name
     * @return Created {@link Provider} object.
     */
    Provider createProvider(String name);
}
