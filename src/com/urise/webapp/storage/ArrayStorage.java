package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Превышено максимально возможное количество резюме - " + size);
        } else if (findIndex(r.getUuid()) == -1) {
            storage[size] = r;
            size++;
            System.out.println(r.getUuid() + " - резюме сохранено");
        } else {
            System.out.println(r.getUuid() + " - данный uuid уже существует, пожалуйста, введите новый");
        }
    }

    public void update(Resume r) {
        int updateResume = findIndex(r.getUuid());
        if (updateResume == -1) {
            System.out.println(r.getUuid() + " - резюме не найдено");
        } else {
            storage[updateResume] = r;
            System.out.println(r.getUuid() + " - резюме обновлено");
        }
    }

    public Resume get(String uuid) {
        int getResume = findIndex(uuid);
        if (getResume == -1) {
            System.out.println(uuid + " - резюме не найдено");
            return null;
        }
        return storage[findIndex(uuid)];
    }

    public void delete(String uuid) {
        int deleteResume = findIndex(uuid);
        if (deleteResume == -1) {
            System.out.println(uuid + " - резюме не найдено");
        } else {
            storage[deleteResume] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            System.out.println(uuid + " - резюме удалено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        System.arraycopy(storage, 0, allResume, 0, size);
        return allResume;
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                index = i;
                break;
            }
        }
        return index;
    }
}
