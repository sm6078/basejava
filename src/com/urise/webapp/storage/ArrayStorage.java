package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < this.size; i++) {
            if (this.storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(Resume resume) {
            this.storage[this.size] = resume;
            this.size++;
    }

    @Override
    void deleteElement(int searchIndex) {
            storage[searchIndex] = storage[this.size - 1];
            storage[this.size - 1] = null;
            this.size--;
    }
}
