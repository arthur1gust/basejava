package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        //searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveResume(int index, Resume r) {
        int srcIndex = -index - 1;
        System.arraycopy(storage, srcIndex, storage, srcIndex + 1, size - srcIndex);
        storage[srcIndex] = r;
    }
}