package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    protected static final int STORAGE_LIMIT = 10000;

    public Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int getResume = findIndex(uuid);
        if (getResume == -1) {
            System.out.println(uuid + " - резюме не найдено");
            return null;
        }
        return storage[findIndex(uuid)];
    }

    protected abstract int findIndex(String uuid);
}
