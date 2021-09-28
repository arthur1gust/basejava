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
        if (size >= 10_000) {
            System.out.println("Превышено максимально возможное количество резюме - " + size + " - максимально допустимое количество 10000");
        } else {
            if (get(r.getUuid()) == null) {
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
        Resume checkResume = null;
        for(int i = 0; i < size; i++) {
            if(storage[i].getUuid().equals(uuid)) {
                checkResume = storage[i];
                break;
            }
        }
        if (checkResume != null) {
            System.out.println("Ваше резюме - " + uuid);
        } else {
            System.out.println(uuid + " - резюме не найдено, введите другой uuid");
        }
        return checkResume;
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
            System.out.println(uuid + " - данное резюме удалено");
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
}
