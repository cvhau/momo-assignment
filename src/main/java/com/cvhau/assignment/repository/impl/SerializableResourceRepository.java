package com.cvhau.assignment.repository.impl;

import com.cvhau.assignment.entity.Entity;
import com.cvhau.assignment.exception.ResourceNotFoundException;
import com.cvhau.assignment.exception.ResourcePermissionException;
import com.cvhau.assignment.exception.ResourceRepositoryException;
import com.cvhau.assignment.repository.Repository;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class SerializableResourceRepository<T extends Entity<ID>, ID extends Comparable<?>>
        implements Repository<T, ID> {
    protected final TreeMap<ID, T> data;

    public SerializableResourceRepository() {
        this(true);
    }

    public SerializableResourceRepository(boolean forceLoadData) {
        this.data = new TreeMap<>();
        if (forceLoadData) {
            loadData();
        }
    }

    public Map<ID, T> getData() {
        return data;
    }

    public abstract String resourceFilename();

    /**
     * Generate next ID automatically.
     *
     * @return The next ID. Cannot be null.
     */
    public abstract ID getNextId();

    /**
     * Get last (highest) ID/Key from the data map.
     * @return Last (highest) ID/Key in the data map.
     */
    public Optional<ID> getLastId() {
        if (data.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(data.lastKey());
    }

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
        URL resourceUrl = getResource(resourceFilename);
        File resourceFile = new File(resourceUrl.getFile());
        if (!resourceFile.canRead()) {
            throw new ResourcePermissionException(("Can not read data from the resource file at %s. " +
                    "Please verify the file permission.").formatted(resourceFilename));
        }
        try (InputStream inputStream = new FileInputStream(resourceFile)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Map<ID, T> _data = (Map<ID, T>) objectInputStream.readObject();
            data.clear();
            data.putAll(_data);
        } catch (EOFException|ObjectStreamException e) {
            // Resource file maybe empty or contain malformed data.
            // So we can ignore it and the file content will
            // be re-written on data commit process later.
            data.clear();
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
        URL resourceUrl = getResource(resourceFilename);
        File resourceFile = new File(resourceUrl.getFile());
        if (!resourceFile.canWrite()) {
            throw new ResourcePermissionException(("Can not write data to the resource file at %s. Please verify the " +
                    "file permission.").formatted(resourceFilename));
        }
        try (OutputStream outputStream = new FileOutputStream(resourceFile, false)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
        } catch (IOException ioException) {
            throw new ResourceRepositoryException(
                    "An error occurred while writing data to resource at %s".formatted(resourceFilename),
                    ioException);
        }
    }

    private URL getResource(String resourceFilename) throws ResourceNotFoundException {
        Objects.requireNonNull(resourceFilename, "Resource filename cannot be null");
        if (resourceFilename.isEmpty()) {
            throw new ResourceNotFoundException("Resource filename cannot be empty");
        }
        URL resourceUrl = getClass().getClassLoader().getResource(resourceFilename);
        if (resourceUrl == null || resourceUrl.getFile().isEmpty()) {
            throw new ResourceNotFoundException("Cannot identify the resource of given name '%s'"
                    .formatted(resourceFilename));
        }
        return resourceUrl;
    }

    @Override
    public T save(T entity) {
        return save(entity, true);
    }

    private T save(T entity, boolean forceCommit) {
        Objects.requireNonNull(entity, "Entity cannot be null");
        if (entity.getId() == null) {
            ID nextId = getNextId();
            Objects.requireNonNull(nextId, "Entity must have a unique ID");
            entity.setId(nextId);
            data.put(nextId, entity);
        } else {
            // Old entity data will be replaced by new data if present.
            data.put(entity.getId(), entity);
        }
        // Maybe commit all changed data.
        if (forceCommit) {
            commitData();
        }
        return entity;
    }

    @Override
    public Collection<T> saveAll(Collection<T> entities) {
        Objects.requireNonNull(entities, "Entities collection cannot be null");
        List<T> _entities = entities
                .stream()
                .filter(Objects::nonNull)
                .toList();
        _entities.forEach(entity -> save(entity, false));
        // Commit all changed data after all.
        commitData();
        return _entities;
    }

    @Override
    public Optional<T> findById(ID id) {
        Objects.requireNonNull(id, "ID cannot be null");
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public Collection<T> findAllById(Collection<ID> ids) {
        Objects.requireNonNull(ids, "IDs collection cannot be null");
        return data
                .values()
                .stream()
                .filter(entity -> ids.contains(entity.getId()))
                .toList();
    }
}
