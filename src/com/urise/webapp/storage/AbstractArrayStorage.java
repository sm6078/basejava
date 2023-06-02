package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        //Arrays.setAll(this.storage, i -> null);
        Arrays.fill(this.storage, 0, this.size, null);
        this.size = 0;
    }

    public void save(Resume r) {
        if (this.size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow  max count Resume: " + this.storage.length);
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else {
            storage[this.size] = r;
            this.size++;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume " + resume.getUuid() + " not exist");
        } else {
            this.storage[index] = resume;
        }
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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return this.storage[index];
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(this.storage, 0, this.size);
    }

    public int size() {
        return this.size;
    }

    abstract int getIndex(String uuid);
}
