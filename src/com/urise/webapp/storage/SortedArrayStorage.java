package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveResume(int fromIndex, Resume r) {
        int toIndex = -fromIndex - 1;
        System.arraycopy(storage, toIndex, storage, toIndex + 1, size - toIndex);
        storage[toIndex] = r;
    }
}