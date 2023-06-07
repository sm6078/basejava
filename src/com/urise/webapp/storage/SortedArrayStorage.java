package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    protected void insertElement(Resume r) {
        int index = -1 * (getIndex(r.getUuid()) + 1);
        Resume[] arrResumes = new Resume[this.size + 1];
        System.arraycopy(this.storage, 0, arrResumes, 0, index);
        arrResumes[index] = r;
        System.arraycopy(this.storage, index, arrResumes, index + 1, this.size - index);
        storage = arrResumes;
        this.size++;
    }

    @Override
    protected void deleteElement(int searchIndex) {
        Resume[] arrResumes = new Resume[this.size - 1];
        System.arraycopy(this.storage, 0, arrResumes, 0, searchIndex);
        System.arraycopy(this.storage, searchIndex + 1, arrResumes, searchIndex, this.size - searchIndex - 1);
        this.storage = arrResumes;
        this.size--;
    }

}
