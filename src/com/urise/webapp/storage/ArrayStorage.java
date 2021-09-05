package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 *
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    public void save(Resume r) {
        if (get(r.getUuid()) == null) {
            storage[size()] = r;
            size++;
        } else {
            System.out.println(r.getUuid() + " -already exist, please another uuid");
        }
    }

    public Resume get(String uuid) {
        for(int i = 0; i < size; i++) {
            if(uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int i = 0;
        boolean checkUuid = false;

        while (i < size) {
            if (storage[i].getUuid().equals(uuid)) {
                checkUuid = true;
            }

            if (checkUuid) {
                storage[i] = storage[i + 1];
            }
            i++;
        }
        if (checkUuid) {
            size--;
        }
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] tempStorage = new Resume[size];
        for (int i = 0; i < size; i++) {
            tempStorage[i] = storage[i];
        }
        return tempStorage;
    }

    public int size() {
        return size;
    }
}
