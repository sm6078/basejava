package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;


    public void clear() {
        //Arrays.setAll(this.storage, i -> null);
        Arrays.fill(this.storage, 0, this.size, null);
        this.size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume " + resume.getUuid() + " not exist");
        } else {
            this.storage[index] = resume;
        }
    }

    public void save(Resume r) {
        if (this.size >= this.storage.length) {
            System.out.println("Storage overflow  max count Resume: " + this.storage.length);
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else {
            storage[this.size] = r;
            this.size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return this.storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            storage[index] = storage[this.size - 1];
            storage[this.size - 1] = null;
            this.size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumeTwo = Arrays.copyOfRange(this.storage, 0, this.size);
        return resumeTwo;
    }

    public int size() {
        return this.size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < this.size; i++) {
            if (this.storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
