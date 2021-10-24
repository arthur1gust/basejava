package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
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
        int indexForSave = getIndex(r.getUuid());
        //if (size >= STORAGE_LIMIT) {
        //    System.out.println("Превышено максимально возможное количество резюме - " + size);
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (indexForSave >= 0) {
            //System.out.println("Resume " + r.getUuid() + " - данный uuid уже существует, пожалуйста, введите новый");
            throw new ExistStorageException(r.getUuid());
        } else {
            saveResume(indexForSave, r);
            size++;
            System.out.println(r.getUuid() + " - резюме сохранено");
        }
    }

    @Override
    public void update(Resume r) {
        int indexForUpdate = getIndex(r.getUuid());
        if (indexForUpdate < 0) {
            //System.out.println(r.getUuid() + " - резюме не найдено");
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[indexForUpdate] = r;
            System.out.println(r.getUuid() + " - резюме обновлено");
        }
    }

    @Override
    public void delete(String uuid) {
        int indexForDelete = getIndex(uuid);
        if (indexForDelete < 0) {
            //System.out.println(uuid + " - резюме не найдено");
            throw new NotExistStorageException(uuid);
        } else {
            storage[indexForDelete] = null;
            if (size - (indexForDelete + 1) >= 0) {
                System.arraycopy(storage, indexForDelete + 1, storage, indexForDelete, size - indexForDelete - 1);
            }
            storage[size - 1] = null;
            size--;
            System.out.println(uuid + " - резюме удалено");
        }
    }

    @Override
    public Resume get(String uuid) {
        int indexForGet = getIndex(uuid);
        if (indexForGet < 0) {
            //System.out.println(uuid + " - резюме не найдено");
            //return null;
            throw new NotExistStorageException(uuid);
        }
        return storage[indexForGet];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveResume(int index, Resume r);
}
