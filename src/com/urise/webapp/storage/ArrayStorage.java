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
        int finalIndex = this.size > 0 ? this.size - 1 : 0;
        Arrays.fill(this.storage, 0, finalIndex, null);
        this.size = 0;
    }

    public void save(Resume r) {
        if (this.size >= this.storage.length) {
            System.out.println("Сохранение невозможно, превышен объем storage, в кол-ве: " + this.storage.length + " шт");
        } else if (searchByUuid(r.getUuid()) < 0) {
            int index = this.size > 0 ? this.size : 0;
            storage[index] = r;
            this.size++;
        }
    }

    public Resume get(String uuid) {
        int index = searchByUuid(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = searchByUuid(uuid);
        if (index >= 0) {
            storage[index] = storage[this.size - 1];
            storage[this.size - 1] = null;
            this.size--;
        }
    }

    public void update(Resume resume) {
        int index = searchByUuid(resume.getUuid());
        if (index >= 0) {
            this.storage[index] = resume;
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

    private int searchByUuid(String uuid) {
        for (int i = 0; i < this.size; i++) {
            if (this.storage[i].getUuid().equals(uuid)) {
                System.out.println("Резюме с uuid: " + uuid + " найдено");
                return i;
            }
        }
        System.out.println("Резюме с uuid: " + uuid + " не найдено");
        return -1;

    }
}
