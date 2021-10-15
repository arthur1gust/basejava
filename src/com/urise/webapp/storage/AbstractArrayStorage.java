package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Превышено максимально возможное количество резюме - " + size);
        } else if (getIndex(r.getUuid()) == -1) {
            storage[size] = r;
            size++;
            System.out.println(r.getUuid() + " - резюме сохранено");
        } else {
            System.out.println(r.getUuid() + " - данный uuid уже существует, пожалуйста, введите новый");
        }
    }

    @Override
    public void update(Resume r) {
        int updateResume = getIndex(r.getUuid());
        if (updateResume == -1) {
            System.out.println(r.getUuid() + " - резюме не найдено");
        } else {
            storage[updateResume] = r;
            System.out.println(r.getUuid() + " - резюме обновлено");
        }
    }

    @Override
    public void delete(String uuid) {
        int deleteResume = getIndex(uuid);
        if (deleteResume == -1) {
            System.out.println(uuid + " - резюме не найдено");
        } else {
            storage[deleteResume] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println(uuid + " - резюме удалено");
        }
    }

    @Override
    public Resume get(String uuid) {
        int getResume = getIndex(uuid);
        if (getResume == -1) {
            System.out.println(uuid + " - резюме не найдено");
            return null;
        }
        return storage[getIndex(uuid)];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);
}
