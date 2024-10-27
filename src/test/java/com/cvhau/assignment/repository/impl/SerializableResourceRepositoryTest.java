package com.cvhau.assignment.repository.impl;

import com.cvhau.assignment.entity.Entity;
import com.cvhau.assignment.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SerializableResourceRepositoryTest {
    private TestSerializableResourceRepository repository;

    @BeforeEach
    void setUp() {
        repository = new TestSerializableResourceRepository();
        repository.clearData();
    }

    @Test
    void loadData_GivenEmptyResourceName_ThenThrowException() {
        assertThrows(
                ResourceNotFoundException.class,
                () -> repository.loadData("")
        );
    }

    @Test
    void loadData_GivenNullResourceName_ThenThrowException() {
        assertThrows(
                NullPointerException.class,
                () -> repository.loadData(null)
        );
    }

    @Test
    void loadData_GivenResourceFileWithEmptyContent_ThenGetEmptyMap() {
        repository.loadData("data/empty_repository.dat");
        Map<Long, TestEntity> data = repository.getData();
        assertTrue(data.isEmpty());
    }

    @Test
    void loadData_GivenResourceFileWithMalformedContent_ThenGetEmptyMap() {
        repository.loadData("data/malformed_data_repository.dat");
        Map<Long, TestEntity> data = repository.getData();
        assertTrue(data.isEmpty());
    }

    @Test
    void commitData_GivenEmptyResourceName_ThenThrowException() {
        assertThrows(
                ResourceNotFoundException.class,
                () -> repository.commitData("")
        );
    }

    @Test
    void commitData_GivenNullResourceName_ThenThrowException() {
        assertThrows(
                NullPointerException.class,
                () -> repository.commitData(null)
        );
    }

    @Test
    void commitData_SaveAnEmptyMap_ThenSuccessful() {
        repository.getData().clear();
        assertTrue(repository.getData().isEmpty());
        assertDoesNotThrow(() -> repository.commitData());
        assertDoesNotThrow(() -> repository.loadData());
        assertTrue(repository.getData().isEmpty());
    }

    @Test
    void commitData_SaveNonEmptyMap_ThenSuccessful() {
        Map<Long, TestEntity> data = repository.getData();
        data.clear();
        data.put(1L, new TestEntity(1L, "TestEntity 1"));
        data.put(2L, new TestEntity(2L, "TestEntity 2"));
        data.put(3L, new TestEntity(3L, "TestEntity 3"));
        assertDoesNotThrow(() -> repository.commitData());
        repository.getData().clear();
        assertTrue(repository.getData().isEmpty());
        assertDoesNotThrow(() -> repository.loadData());
        assertEquals(3, repository.getData().size());
    }

    @Test
    void save_GivenNullValue_ThenThrowException() {
        assertThrows(
                NullPointerException.class,
                () -> repository.save(null)
        );
    }

    @Test
    void save_GivenValidValue_ThenSuccessful() {
        TestEntity testEntity = new TestEntity(10L, "TestEntity 10");
        repository.save(testEntity);
        repository.loadData(); // Reload data from storage again.
        Map<Long, TestEntity> data = repository.getData();
        assertTrue(data.containsKey(testEntity.getId()));
        assertEquals(10L, testEntity.getId());
    }

    @Test
    void save_UpdateAnExistingEntity_ThenSuccessful() {
        TestEntity entity = new TestEntity(1L, "TestEntity 1");
        repository.save(entity);
        entity.setName("TestEntity Changed Name");
        repository.save(entity);
        repository.loadData(); // Reload data from storage again.
        Optional<TestEntity> foundEntity = repository.findById(entity.getId());
        assertTrue(foundEntity.isPresent());
        assertEquals(entity.getId(), foundEntity.get().getId());
        assertEquals("TestEntity Changed Name", foundEntity.get().getName());
    }

    @Test
    void findById_GivenNonExistId_ThenGetEmptyOptional() {
        Optional<TestEntity> optional = repository.findById(10L);
        assertTrue(optional.isEmpty());
    }

    @Test
    void findById_GivenExistId_ThenGetAnEntity() {
        TestEntity testEntity = new TestEntity(10L, "TestEntity");
        repository.save(testEntity);
        repository.loadData(); // Reload data from storage again.
        Optional<TestEntity> optional = repository.findById(10L);
        assertTrue(optional.isPresent());
        assertEquals(testEntity.getId(), optional.get().getId());
    }

    @Test
    void findAllById_GivenNonExitIDs_ThenGetEmptyCollection() {
        Collection<TestEntity> entities = repository.findAllById(List.of(1L, 2L, 3L));
        assertTrue(entities.isEmpty());
    }

    @Test
    void findAllById_GivenExitIDs_ThenGetNonEmptyCollection() {
        List<TestEntity> entities = new ArrayList<>();
        entities.add(new TestEntity(1L, "TestEntity 1"));
        entities.add(new TestEntity(2L, "TestEntity 2"));
        entities.add(new TestEntity(3L, "TestEntity 3"));
        entities.add(new TestEntity(4L, "TestEntity 4"));
        repository.saveAll(entities);
        repository.loadData(); // Reload data from storage again.
        Collection<TestEntity> foundEntities = repository.findAllById(List.of(1L, 2L, 3L));
        assertEquals(3, foundEntities.size());
    }

    public static class TestEntity implements Entity<Long>, Serializable {

        private Long id;
        private String name;

        public TestEntity(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public Long getId() {
            return id;
        }

        @Override
        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class TestSerializableResourceRepository extends SerializableResourceRepository<TestEntity, Long> {

        @Override
        public String resourceFilename() {
            return "data/repository.dat";
        }

        @Override
        public Long getNextId() {
            return getLastId().map(id -> id + 1).orElse(1L);
        }

        public void clearData() {
            data.clear();
            commitData();
        }
    }
}
