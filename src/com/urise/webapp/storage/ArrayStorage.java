package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 *
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    public void save(Resume r) {
        if (size >= storage.length) {
            System.out.println("Превышено максимально возможное количество резюме - " + size);
        } else {if (get(r.getUuid()) == null) {
                storage[size()] = r;
                size++;
            } else {
                System.out.println(r.getUuid() + " - данный uuid уже существует, пожалуйста, введите новый");
            }
        }
    }

    public void update(Resume r) {
        if (get(r.getUuid()) != null) {
            System.out.println(r.getUuid() + " - резюме обновлено");
            r.setUuid(r.getUuid());
        }
    }

    public Resume get(String uuid) {
        if (searchResume(uuid) == 0) {
            System.out.println(uuid + " - резюме не найдено");
            return null;
        }
        return storage[searchResume(uuid)];
    }

    public void delete(String uuid) {
        if (searchResume(uuid) == 0) {
            System.out.println(uuid + " - резюме не найдено");
        } else {
            storage[searchResume(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }
    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        for (int i = 0; i < size; i++) {
            allResume[i] = storage[i];
        }
        return allResume;
    }

    public int size() {
        return size;
    }

    private int searchResume(String uuid) {
        int search = 0;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                search = i;
                break;
            }
        }
        return search;
    }
}
