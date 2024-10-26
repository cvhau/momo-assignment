package com.cvhau.assignment.repository.impl;

import com.cvhau.assignment.exception.ResourceNotFoundException;
import com.cvhau.assignment.exception.ResourcePermissionException;
import com.cvhau.assignment.exception.ResourceRepositoryException;
import com.cvhau.assignment.repository.Repository;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public abstract class SerializableResourceRepository<T, ID extends Comparable<?>> implements Repository<T, ID> {
    protected final Map<ID, T> data;

    public SerializableResourceRepository() {
        this.data = new TreeMap<>();
    }

    public Map<ID, T> getData() {
        return data;
    }

    public abstract String resourceFilename();

    /**
     * Load data from the default resource file.
     *
     * @throws ResourceRepositoryException If resource file reading is failed.
     */
    public void loadData() throws ResourceRepositoryException {
        loadData(resourceFilename());
    }

    /**
     * Load data from a resource file
     *
     * @throws ResourceRepositoryException If resource file reading is failed.
     */
    @SuppressWarnings("unchecked")
    public void loadData(String resourceFilename) throws ResourceRepositoryException {
        Objects.requireNonNull(resourceFilename, "Resource filename cannot be null");
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceFilename)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            // TODO Check if data file is empty.
            Map<ID, T> _data = (Map<ID, T>) objectInputStream.readObject();
            data.clear();
            data.putAll(_data);
        } catch (IOException | ClassNotFoundException exception) {
            String message = "An error occurred while loading data from resource at %s"
                    .formatted(resourceFilename);
            throw new ResourceRepositoryException(message, exception);
        }
    }

    /**
     * Commit/write data to the default resource file.
     *
     * @throws ResourceRepositoryException If resource file writing is failed.
     */
    public void commitData() throws ResourceRepositoryException {
        commitData(resourceFilename());
    }

    /**
     * Commit/write data to a resource file.
     *
     * @throws ResourceRepositoryException If resource file writing is failed.
     */
    public void commitData(String resourceFilename) throws ResourceRepositoryException {
        Objects.requireNonNull(resourceFilename, "Resource filename cannot be null");
        URL resourceUrl = getClass().getClassLoader().getResource(resourceFilename);
        if (resourceUrl == null || resourceUrl.getFile().isEmpty()) {
            throw new ResourceNotFoundException("Cannot identify the resource of given name '%s'"
                    .formatted(resourceFilename));
        }
        File resourceFile = new File(resourceUrl.getFile());
        if (!resourceFile.canWrite()) {
            throw new ResourcePermissionException(("Can not write data to the resource file at %s. Please verify the " +
                    "file permission.").formatted(resourceFilename));
        }
        try (OutputStream outputStream = new FileOutputStream(resourceFile)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
        } catch (IOException ioException) {
            throw new ResourceRepositoryException(
                    "An error occurred while writing data to resource at %s".formatted(resourceFilename),
                    ioException);
        }
    }
}
